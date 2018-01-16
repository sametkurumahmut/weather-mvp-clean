package io.sametkurumahmut.weather.device.mobile.android.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.support.annotation.RequiresPermission
import android.support.v4.content.ContextCompat
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.sametkurumahmut.weather.common.schedulers.UiSchedulerProvider
import io.sametkurumahmut.weather.device.mobile.android.R
import io.sametkurumahmut.weather.device.mobile.android.permission.PermissionRequiredException
import io.sametkurumahmut.weather.domain.device.location.Location
import io.sametkurumahmut.weather.domain.device.location.LocationGateway
import javax.inject.Inject

class LocationGatewayImpl @Inject constructor(
        private val context: Context,
        private val uiScheduler: UiSchedulerProvider) : LocationGateway {

    //region Properties
    private val manager by lazy {
        this.context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private val providers by lazy {
        arrayOf(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER)
    }

    private val enabledProviders
        get() = this.providers.filter { this.manager.isProviderEnabled(it) }
    //endregion

    //region LocationGateway Methods
    @RequiresPermission(anyOf = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
    override fun getBestLastLocation(): Observable<Location> = Observable.create<Location> {
        val location = this.internalGetBestLastLocation()
        if (location != null) {
            it.onNext(this.createLocation(location))
        }

        it.onComplete()
    }

    @RequiresPermission(anyOf = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
    override fun getLocation(): Observable<Location> = Observable
            .merge(this.getBestLastLocation(), this.createGetUpdatedLocation())
            .observeOn(this.uiScheduler.ui)

    @RequiresPermission(anyOf = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
    override fun getUpdatedLocation(): Observable<Location>
            = this.createGetUpdatedLocation().observeOn(this.uiScheduler.ui)
    //endregion

    //region Methods
    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this.context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            throw PermissionRequiredException(
                    this.context.getString(R.string.error_permission_required_location))
        }
    }

    private fun createGetUpdatedLocation() = Observable.create<Location> {
        this.checkLocationPermission()

        val listener = LocationListenerImpl(it)

        for (provider in this.enabledProviders) {
            this.manager.requestLocationUpdates(
                    provider,
                    0L,
                    0f,
                    listener,
                    Looper.getMainLooper())
        }

        it.setCancellable {
            this.manager.removeUpdates(listener)
        }
    }

    private fun createLocation(location: android.location.Location) = object : Location {
        override var latitude = location.latitude
        override var longitude = location.longitude
    }

    private fun internalGetBestLastLocation(): android.location.Location? {
        this.checkLocationPermission()

        var bestLocation: android.location.Location? = null

        for (provider in this.enabledProviders) {
            this.manager.getLastKnownLocation(provider)?.let {
                if (it.isBetterThan(bestLocation)) {
                    bestLocation = it
                }
            }
        }

        return bestLocation
    }
    //endregion

    //region android.location.Location Extension Methods
    private fun android.location.Location.isBetterThan(previousLocation: android.location.Location?)
            = previousLocation == null || this.accuracy < previousLocation.accuracy
    //endregion

    //region Inner Types
    inner class LocationListenerImpl(private val emitter: ObservableEmitter<Location>)
        : LocationListener {

        private var bestLocation: android.location.Location? = null

        override fun onLocationChanged(location: android.location.Location) {
            if (location.isBetterThan(this.bestLocation)) {
                this.emitter.onNext(createLocation(location))

                this.bestLocation = location
            }
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String?) {}
    }
    //endregion
}

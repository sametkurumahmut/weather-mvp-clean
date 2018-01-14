package io.sametkurumahmut.weather.app.mobile.android.ui.activity

import android.Manifest
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import dagger.android.AndroidInjection
import io.sametkurumahmut.weather.app.mobile.android.R
import io.sametkurumahmut.weather.app.mobile.android.ui.extensions.anko.indefiniteSnackbar
import io.sametkurumahmut.weather.app.mobile.android.ui.glide.module.GlideApp
import io.sametkurumahmut.weather.app.mobile.android.ui.model.CityWeatherViewModel
import io.sametkurumahmut.weather.common.mapper.MapperTo
import io.sametkurumahmut.weather.device.mobile.android.permission.PermissionClient
import io.sametkurumahmut.weather.device.mobile.android.permission.PermissionListener
import io.sametkurumahmut.weather.device.mobile.android.permission.PermissionRequest
import io.sametkurumahmut.weather.device.mobile.android.permission.PermissionRequiredException
import io.sametkurumahmut.weather.presentation.main.MainContract
import io.sametkurumahmut.weather.presentation.model.CityWeatherView
import kotlinx.android.synthetic.main.act_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    //region MainContract.View Properties
    override var isRefreshingWeather
        get() = this.lytSwipeRefresh.isRefreshing
        set(value) {
            this.lytSwipeRefresh.isRefreshing = value
        }
    //endregion

    //region Public Properties
    @Inject lateinit var mapper: MapperTo<CityWeatherViewModel, @JvmSuppressWildcards CityWeatherView>

    @Inject lateinit var presenter: MainContract.Presenter

    @Inject lateinit var permissionClient: PermissionClient
    //endregion

    //region Private Properties
    private val locationPermissionRequiredInfo: String
        get() = this.getString(R.string.info_permission_required_location)

    private val locationPermissionRequest = LocationPermissionRequest()

    private var isOpenedAppSettings = false

    private var snbWeatherInfo: Snackbar? = null
    //endregion

    //region Activity Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        this.setContentView(R.layout.act_main)
        this.presenter.start()

        this.registerListeners()

        this.presenter.loadCityWeatherManually()
    }

    override fun onDestroy() {
        this.presenter.stop()
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray) {
        this.permissionClient.runIfVerifiedOrShowRationaleOnMatchRequestCode(
                this.locationPermissionRequest,
                requestCode,
                grantResults)
    }

    override fun onStart() {
        super.onStart()

        if (this.isOpenedAppSettings) {
            this.permissionClient.checkAndRunPermissions(this.locationPermissionRequest)
            this.isOpenedAppSettings = false
        }
    }

    //endregion

    //region MainContract.View Methods
    //region BaseView Methods
    override fun dispatchUnhandledError(error: Throwable) {
        when (error) {
            is PermissionRequiredException -> {
                this.showLocationPermissionRequiredError()
            }
            else -> {
                throw error
            }
        }
    }
    //endregion

    override fun hideWeatherInfoNotAvailableError() {
        this.snbWeatherInfo?.let {
            it.dismiss()
            this.snbWeatherInfo = null
        }
    }

    override fun hideHotCityWeatherLayout() {
        this.icCloudMainCityWeatherInfo.visibility = View.GONE
        this.tvCityWeatherInfoDescription.visibility = View.GONE
        this.icTempMax.visibility = View.GONE
        this.tvTempMax.visibility = View.GONE
        this.icTempMin.visibility = View.GONE
        this.tvTempMin.visibility = View.GONE
        this.tvTemp.visibility = View.GONE
    }

    override fun showHotCityWeatherLayout() {
        this.icCloudMainCityWeatherInfo.visibility = View.VISIBLE
        this.tvCityWeatherInfoDescription.visibility = View.VISIBLE
        this.icTempMax.visibility = View.VISIBLE
        this.tvTempMax.visibility = View.VISIBLE
        this.icTempMin.visibility = View.VISIBLE
        this.tvTempMin.visibility = View.VISIBLE
        this.tvTemp.visibility = View.VISIBLE
    }

    override fun showCityWeather(cityWeatherView: CityWeatherView) {
        val cityWeatherViewModel = this.mapper.to(cityWeatherView)

        this.tvCity.text = cityWeatherViewModel.name
        this.tvTemp.text = this.getString(
                R.string.format_temp,
                cityWeatherViewModel.weatherDetails.temp)
        this.tvTempMax.text = this.getString(
                R.string.format_temp,
                cityWeatherViewModel.weatherDetails.maxTemp)
        this.tvTempMin.text = this.getString(
                R.string.format_temp,
                cityWeatherViewModel.weatherDetails.minTemp)
        this.tvCityWeatherInfoDescription.text = cityWeatherViewModel.weatherInfo.description
        GlideApp
                .with(this)
                .load(cityWeatherViewModel.weatherInfo.iconUri)
                .error(R.drawable.ic_cloud_error_weather_info)
                .fallback(R.drawable.ic_cloud_none_weather_info)
                .transition(withCrossFade())
                .skipMemoryCache(true)
                .into(this.icCloudMainCityWeatherInfo)
    }

    override fun showNetworkUnavailableError() {
        toast(R.string.network_unavailable)
    }

    override fun showWeatherInfoNotAvailableError() {
        this.snbWeatherInfo = indefiniteSnackbar(
                this.getString(R.string.error_weather_info_not_available))
    }
    //endregion

    //region Methods
    //region Initialize
    private fun registerListeners() {
        this.lytSwipeRefreshOnRefreshRegistration()
    }
    //endregion

    //region Event Listener Registrations
    private fun lytSwipeRefreshOnRefreshRegistration() {
        this.lytSwipeRefresh.setOnRefreshListener(this.presenter::loadCityWeatherOnNetConnected)
    }
    //endregion

    private fun showLocationPermissionRequiredError() {
        indefiniteSnackbar(
                this.locationPermissionRequiredInfo,
                this.getString(R.string.allow),
                { this.permissionClient.requestPermissions(this.locationPermissionRequest) })
                .also {
                    it.view
                            .findViewById<TextView>(android.support.design.R.id.snackbar_text)
                            .apply {
                                maxLines = 3
                            }
                }
    }
    //endregion

    //region Inner Types
    inner class LocationPermissionRequest : PermissionRequest {

        override val requestCode: Int
            get() = 0

        override val permissions: Array<String>
            get() = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)

        override val listener: PermissionListener
            get() = LocationPermissionListener()
    }

    inner class LocationPermissionListener : PermissionListener {

        override fun onPermissionsDenied(
                permissionClient: PermissionClient,
                permissionRequest: PermissionRequest,
                permissions: Array<String>) {
            alert(locationPermissionRequiredInfo) {
                positiveButton(getString(android.R.string.ok)) {
                    permissionClient.requestPermissions(permissionRequest)
                }
                cancelButton { showLocationPermissionRequiredError() }
                onCancelled { showLocationPermissionRequiredError() }
            }.show()
        }

        override fun onPermissionsNeverAskAgain(
                permissionClient: PermissionClient,
                permissionRequest: PermissionRequest,
                permissions: Array<String>) {
            alert(
                    locationPermissionRequiredInfo
                            + getString(R.string.msg_rationale_location_go_app_settings)) {
                positiveButton(getString(R.string.go_app_settings)) {
                    presenter.openAppSettings()
                    isOpenedAppSettings = true
                }
                cancelButton { showLocationPermissionRequiredError() }
                onCancelled { showLocationPermissionRequiredError() }
            }.show()
        }

        override fun onRun(
                permissionClient: PermissionClient,
                permissionRequest: PermissionRequest,
                permissions: Array<String>) {
            presenter.loadCityWeatherManually()
        }

        override fun onShowRationale(
                permissionClient: PermissionClient,
                permissionRequest: PermissionRequest,
                permissions: Array<String>) {
            alert(locationPermissionRequiredInfo) {
                positiveButton(getString(android.R.string.ok)) {
                    permissionClient.requestPermissions(permissionRequest)
                }
                cancelButton {
                    onPermissionsDenied(permissionClient, permissionRequest, permissions)
                }
                onCancelled {
                    onPermissionsDenied(permissionClient, permissionRequest, permissions)
                }
            }.show()
        }
    }
    //endregion
}

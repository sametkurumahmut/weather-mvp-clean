package io.sametkurumahmut.weather.domain.interactor.location

import io.sametkurumahmut.weather.domain.device.location.Location
import io.sametkurumahmut.weather.domain.device.location.LocationGateway
import io.sametkurumahmut.weather.domain.interactor.ObservableUseCase
import javax.inject.Inject

open class GetLocation @Inject constructor(private val locationGateway: LocationGateway)
    : ObservableUseCase<Location, Nothing>() {

    override fun build(params: Nothing?) = this.locationGateway.getLocation()
}

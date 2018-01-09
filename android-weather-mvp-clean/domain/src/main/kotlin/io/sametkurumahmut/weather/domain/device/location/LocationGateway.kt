package io.sametkurumahmut.weather.domain.device.location

import io.reactivex.Observable

interface LocationGateway {

    fun getBestLastLocation(): Observable<Location>

    fun getLocation(): Observable<Location>

    fun getUpdatedLocation(): Observable<Location>
}

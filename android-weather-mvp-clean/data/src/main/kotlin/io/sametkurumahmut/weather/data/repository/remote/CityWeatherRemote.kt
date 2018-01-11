package io.sametkurumahmut.weather.data.repository.remote

import io.reactivex.Single
import io.sametkurumahmut.weather.data.model.CityWeatherEntity

interface CityWeatherRemote {

    fun getCityWeatherByLocation(latitude: Double, longitude: Double): Single<CityWeatherEntity>
}

package io.sametkurumahmut.weather.data.source

import io.reactivex.Completable
import io.reactivex.Single
import io.sametkurumahmut.weather.data.model.CityWeatherEntity

interface CityWeatherDataSource {

    fun clearCurrentLocationWeather(): Completable

    fun getCurrentLocationWeather(): Single<CityWeatherEntity>

    fun getCityWeatherByLocation(latitude: Double, longitude: Double): Single<CityWeatherEntity>

    fun saveCityWeather(cityWeather: CityWeatherEntity): Completable
}

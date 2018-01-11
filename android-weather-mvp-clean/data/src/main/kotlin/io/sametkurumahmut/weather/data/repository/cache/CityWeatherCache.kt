package io.sametkurumahmut.weather.data.repository.cache

import io.reactivex.Completable
import io.reactivex.Single
import io.sametkurumahmut.weather.data.model.CityWeatherEntity

interface CityWeatherCache {

    fun clearCurrentLocationWeather(): Completable

    fun getCurrentLocationWeather(): Single<CityWeatherEntity>

    fun isCached(): Single<Boolean>

    fun isExpired(): Boolean

    fun setLastCacheTime(time: Long)

    fun saveCityWeather(cityWeather: CityWeatherEntity): Completable
}

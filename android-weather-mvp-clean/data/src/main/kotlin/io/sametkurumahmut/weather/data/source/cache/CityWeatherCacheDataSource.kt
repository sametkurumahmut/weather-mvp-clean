package io.sametkurumahmut.weather.data.source.cache

import io.reactivex.Completable
import io.reactivex.Single
import io.sametkurumahmut.weather.data.model.CityWeatherEntity
import io.sametkurumahmut.weather.data.repository.cache.CityWeatherCache
import io.sametkurumahmut.weather.data.source.CityWeatherDataSource
import javax.inject.Inject

class CityWeatherCacheDataSource @Inject constructor(
        private val cityWeatherCache: CityWeatherCache) : CityWeatherDataSource {

    override fun clearCurrentLocationWeather() = this.cityWeatherCache.clearCurrentLocationWeather()

    override fun getCurrentLocationWeather() = this.cityWeatherCache.getCurrentLocationWeather()

    override fun getCityWeatherByLocation(latitude: Double, longitude: Double)
            : Single<CityWeatherEntity> {
        throw UnsupportedOperationException()
    }

    override fun saveCityWeather(cityWeather: CityWeatherEntity): Completable =
            this.cityWeatherCache.saveCityWeather(cityWeather).doOnComplete {
                this.cityWeatherCache.setLastCacheTime(System.currentTimeMillis())
            }
}

package io.sametkurumahmut.weather.cache.mobile.android.repository

import io.reactivex.Completable
import io.reactivex.Single
import io.sametkurumahmut.weather.cache.mobile.android.model.CachedCityWeather
import io.sametkurumahmut.weather.cache.mobile.android.prefs.Prefs
import io.sametkurumahmut.weather.cache.mobile.android.room.WeatherDao
import io.sametkurumahmut.weather.common.mapper.Mapper
import io.sametkurumahmut.weather.data.model.CityWeatherEntity
import io.sametkurumahmut.weather.data.repository.cache.CityWeatherCache
import javax.inject.Inject

class CityWeatherCacheImpl @Inject constructor(
        private val weatherDao: WeatherDao,
        private val prefs: Prefs,
        private val mapper: Mapper<CachedCityWeather, CityWeatherEntity>) : CityWeatherCache {

    //region Properties
    private val expirationTime = (2 * 60 * 60 * 1000).toLong()
    //endregion

    //region CityWeatherCache Methods
    override fun clearCurrentLocationWeather(): Completable = Completable.fromAction {
        this.weatherDao.deleteCurrentLocationWeather()
    }

    override fun getCurrentLocationWeather(): Single<CityWeatherEntity> =
            this.weatherDao.getCurrentLocationWeather()
                    .map {
                        this.mapper.from(it)
                    }

    override fun isCached(): Single<Boolean> = this.weatherDao.getCurrentLocationWeather()
            .flatMap {
                Single.just(true)
            }
            .onErrorResumeNext {
                Single.just(false)
            }

    override fun isExpired()
            = System.currentTimeMillis() - this.prefs.lastCacheTime > this.expirationTime

    override fun setLastCacheTime(time: Long) {
        this.prefs.lastCacheTime = time
    }

    override fun saveCityWeather(cityWeather: CityWeatherEntity): Completable =
            Completable.fromAction {
                this.weatherDao.insertCityWeather(this.mapper.to(cityWeather))
            }
    //endregion
}

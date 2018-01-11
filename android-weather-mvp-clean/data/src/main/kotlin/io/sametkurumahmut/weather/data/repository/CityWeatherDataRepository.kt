package io.sametkurumahmut.weather.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import io.sametkurumahmut.weather.common.mapper.Mapper
import io.sametkurumahmut.weather.common.schedulers.rx.RxSchedulerProvider
import io.sametkurumahmut.weather.data.model.CityWeatherEntity
import io.sametkurumahmut.weather.data.repository.cache.CityWeatherCache
import io.sametkurumahmut.weather.data.source.CityWeatherDataSource
import io.sametkurumahmut.weather.domain.WeatherInfoNotAvailableException
import io.sametkurumahmut.weather.domain.data.repository.CityWeatherRepository
import io.sametkurumahmut.weather.domain.device.location.Location
import io.sametkurumahmut.weather.domain.device.net.NetInfo
import io.sametkurumahmut.weather.domain.interactor.CompletableUseCase
import io.sametkurumahmut.weather.domain.interactor.ObservableUseCase
import io.sametkurumahmut.weather.domain.model.CityWeather
import javax.inject.Inject

class CityWeatherDataRepository @Inject constructor(
        private val cityWeatherCache: CityWeatherCache,
        private val cityWeatherCacheDataSource: CityWeatherDataSource,
        private val cityWeatherRemoteDataSource: CityWeatherDataSource,
        private val getLocationUseCase: ObservableUseCase<Location, Nothing>,
        private val onNetConnectedUseCase: CompletableUseCase<Nothing>,
        private val netInfo: NetInfo,
        private val mapper: Mapper<CityWeatherEntity, CityWeather>,
        private val rxSchedulers: RxSchedulerProvider) : CityWeatherRepository {

    //region CityWeatherRepository Methods
    override fun clearCurrentLocationWeather() = this.cityWeatherCache.clearCurrentLocationWeather()

    override fun getCurrentLocationWeather(): Observable<CityWeather> {
        val observableCityWeatherEntity = if (this.netInfo.isConnected()) {
            this.getRemoteCityWeather()
        } else {
            Observable.concat(
                    this.cityWeatherCache.isCached()
                            .flatMap {
                                if (it && !this.cityWeatherCache.isExpired()) {
                                    this.cityWeatherCacheDataSource.getCurrentLocationWeather()
                                } else {
                                    Single.error(
                                            WeatherInfoNotAvailableException(
                                                    if (!it)
                                                        "Cache doesn't exist"
                                                    else
                                                        "Cache is expired"))
                                }
                            }
                            .toObservable(),
                    this.onNetConnectedUseCase.execute().toObservable(),
                    this.getRemoteCityWeather())
        }

        return observableCityWeatherEntity
                .map {
                    this.mapper.from(it)
                }
    }

    override fun saveCityWeather(cityWeather: CityWeather)
            = this.cityWeatherCache.saveCityWeather(this.mapper.to(cityWeather))
    //endregion

    //region Methods
    private fun getRemoteCityWeather() = this.getLocationUseCase.execute()
            .observeOn(this.rxSchedulers.io)
            .flatMap {
                this.cityWeatherRemoteDataSource.getCityWeatherByLocation(it.latitude, it.longitude)
                        .flatMap {
                            this.cityWeatherCacheDataSource.saveCityWeather(it).toSingle { it }
                        }
                        .toObservable()
            }
    //endregion
}

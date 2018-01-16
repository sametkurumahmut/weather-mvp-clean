package io.sametkurumahmut.weather.data.source.remote

import io.reactivex.Completable
import io.reactivex.Single
import io.sametkurumahmut.weather.data.model.CityWeatherEntity
import io.sametkurumahmut.weather.data.repository.remote.CityWeatherRemote
import io.sametkurumahmut.weather.data.source.CityWeatherDataSource
import javax.inject.Inject

class CityWeatherRemoteDataSource @Inject constructor(
        private val cityWeatherRemote: CityWeatherRemote) : CityWeatherDataSource {

    override fun clearCurrentLocationWeather(): Completable {
        throw UnsupportedOperationException()
    }

    override fun getCurrentLocationWeather(): Single<CityWeatherEntity> {
        throw UnsupportedOperationException()
    }

    override fun getCityWeatherByLocation(latitude: Double, longitude: Double)
            = this.cityWeatherRemote.getCityWeatherByLocation(latitude, longitude)

    override fun saveCityWeather(cityWeather: CityWeatherEntity): Completable {
        throw UnsupportedOperationException()
    }
}

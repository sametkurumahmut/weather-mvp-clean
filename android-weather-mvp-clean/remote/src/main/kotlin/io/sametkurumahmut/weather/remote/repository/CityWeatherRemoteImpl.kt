package io.sametkurumahmut.weather.remote.repository

import io.reactivex.Single
import io.sametkurumahmut.weather.common.mapper.MapperFrom
import io.sametkurumahmut.weather.data.model.CityWeatherEntity
import io.sametkurumahmut.weather.data.repository.remote.CityWeatherRemote
import io.sametkurumahmut.weather.domain.WeatherInfoNotAvailableException
import io.sametkurumahmut.weather.remote.model.RemoteCityWeather
import io.sametkurumahmut.weather.remote.service.WeatherService
import javax.inject.Inject

class CityWeatherRemoteImpl @Inject constructor(
        private val weatherService: WeatherService,
        private val mapper: MapperFrom<RemoteCityWeather, CityWeatherEntity>) : CityWeatherRemote {

    override fun getCityWeatherByLocation(latitude: Double, longitude: Double)
            : Single<CityWeatherEntity> =
            this.weatherService.getByLocation(latitude, longitude)
                    .onErrorResumeNext {
                        Single.error(WeatherInfoNotAvailableException(it))
                    }
                    .map {
                        this.mapper.from(it)
                    }
}

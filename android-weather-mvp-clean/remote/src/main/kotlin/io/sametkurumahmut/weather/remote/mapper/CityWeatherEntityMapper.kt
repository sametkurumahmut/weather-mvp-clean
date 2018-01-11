package io.sametkurumahmut.weather.remote.mapper

import io.sametkurumahmut.weather.common.mapper.MapperFrom
import io.sametkurumahmut.weather.data.model.CityWeatherEntity
import io.sametkurumahmut.weather.remote.model.RemoteCityWeather
import javax.inject.Inject

open class CityWeatherEntityMapper @Inject constructor(
        private val coordinatesEntityMapper: CoordinatesEntityMapper,
        private val weatherInfoEntityMapper: WeatherInfoEntityMapper,
        private val weatherDetailsEntityMapper: WeatherDetailsEntityMapper)
    : MapperFrom<RemoteCityWeather, CityWeatherEntity> {

    override fun from(type: RemoteCityWeather) = CityWeatherEntity(
            type.name,
            this.coordinatesEntityMapper.from(type.coordinates),
            this.weatherInfoEntityMapper.from(type.weatherInfo[0]),
            this.weatherDetailsEntityMapper.from(type.weatherDetails))
}

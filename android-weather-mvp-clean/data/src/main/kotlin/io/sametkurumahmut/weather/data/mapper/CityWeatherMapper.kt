package io.sametkurumahmut.weather.data.mapper

import io.sametkurumahmut.weather.common.mapper.Mapper
import io.sametkurumahmut.weather.data.model.CityWeatherEntity
import io.sametkurumahmut.weather.domain.model.CityWeather
import javax.inject.Inject

open class CityWeatherMapper @Inject constructor(
        private val coordinatesMapper: CoordinatesMapper,
        private val weatherInfoMapper: WeatherInfoMapper,
        private val weatherDetailsMapper: WeatherDetailsMapper)
    : Mapper<CityWeatherEntity, CityWeather> {

    override fun from(type: CityWeatherEntity) = CityWeather(
            type.name,
            this.coordinatesMapper.from(type.coordinates),
            this.weatherInfoMapper.from(type.weatherInfo),
            this.weatherDetailsMapper.from(type.weatherDetails))

    override fun to(type: CityWeather) = CityWeatherEntity(
            type.name,
            this.coordinatesMapper.to(type.coordinates),
            this.weatherInfoMapper.to(type.weatherInfo),
            this.weatherDetailsMapper.to(type.weatherDetails))
}

package io.sametkurumahmut.weather.cache.mobile.android.mapper

import io.sametkurumahmut.weather.cache.mobile.android.model.CachedCityWeather
import io.sametkurumahmut.weather.common.mapper.Mapper
import io.sametkurumahmut.weather.data.model.CityWeatherEntity
import javax.inject.Inject

open class CityWeatherEntityMapper @Inject constructor(
        private val coordinatesEntityMapper: CoordinatesEntityMapper,
        private val weatherInfoEntityMapper: WeatherInfoEntityMapper,
        private val weatherDetailsEntityMapper: WeatherDetailsEntityMapper)
    : Mapper<CachedCityWeather, CityWeatherEntity> {

    override fun from(type: CachedCityWeather) = CityWeatherEntity(
            type.name,
            this.coordinatesEntityMapper.from(type.coordinates),
            this.weatherInfoEntityMapper.from(type.weatherInfo),
            this.weatherDetailsEntityMapper.from(type.weatherDetails))

    override fun to(type: CityWeatherEntity) = CachedCityWeather(
            type.name,
            this.coordinatesEntityMapper.to(type.coordinates),
            this.weatherInfoEntityMapper.to(type.weatherInfo),
            this.weatherDetailsEntityMapper.to(type.weatherDetails))
}

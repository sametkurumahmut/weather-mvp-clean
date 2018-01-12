package io.sametkurumahmut.weather.presentation.mapper

import io.sametkurumahmut.weather.common.mapper.MapperTo
import io.sametkurumahmut.weather.domain.model.CityWeather
import io.sametkurumahmut.weather.presentation.model.CityWeatherView
import javax.inject.Inject

open class CityWeatherMapper @Inject constructor(
        private val coordinatesMapper: CoordinatesMapper,
        private val weatherInfoMapper: WeatherInfoMapper,
        private val weatherDetailsMapper: WeatherDetailsMapper)
    : MapperTo<CityWeatherView, CityWeather> {

    override fun to(type: CityWeather) = CityWeatherView(
            type.name,
            this.coordinatesMapper.to(type.coordinates),
            this.weatherInfoMapper.to(type.weatherInfo),
            this.weatherDetailsMapper.to(type.weatherDetails))
}

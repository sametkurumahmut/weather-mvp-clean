package io.sametkurumahmut.weather.domain.model

data class CityWeather(
        val name: String,
        val coordinates: Coordinates,
        val weatherInfo: WeatherInfo,
        val weatherDetails: WeatherDetails)

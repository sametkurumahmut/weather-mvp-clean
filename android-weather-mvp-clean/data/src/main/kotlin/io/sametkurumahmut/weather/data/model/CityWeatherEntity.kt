package io.sametkurumahmut.weather.data.model

data class CityWeatherEntity(
        val name: String,
        val coordinates: CoordinatesEntity,
        val weatherInfo: WeatherInfoEntity,
        val weatherDetails: WeatherDetailsEntity)

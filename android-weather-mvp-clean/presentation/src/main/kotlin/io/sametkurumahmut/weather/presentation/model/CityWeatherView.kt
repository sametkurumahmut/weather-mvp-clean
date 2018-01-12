package io.sametkurumahmut.weather.presentation.model

data class CityWeatherView(
        val name: String,
        val coordinates: CoordinatesView,
        val weatherInfo: WeatherInfoView,
        val weatherDetails: WeatherDetailsView)

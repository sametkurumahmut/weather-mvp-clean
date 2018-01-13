package io.sametkurumahmut.weather.app.mobile.android.ui.model

data class CityWeatherViewModel(
        val name: String,
        val coordinates: CoordinatesViewModel,
        val weatherInfo: WeatherInfoViewModel,
        val weatherDetails: WeatherDetailsViewModel)

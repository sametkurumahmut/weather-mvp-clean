package io.sametkurumahmut.weather.remote.model

import com.google.gson.annotations.SerializedName

/**
 * @property name City name
 * @property coordinates City geo location
 * @property weatherInfo More info weather condition codes
 * @property weatherDetails
 */
data class RemoteCityWeather(
        val name: String,
        @SerializedName("coord") val coordinates: RemoteCoordinates,
        @SerializedName("weather") val weatherInfo: List<RemoteWeatherInfo>,
        @SerializedName("main") val weatherDetails: RemoteWeatherDetails)

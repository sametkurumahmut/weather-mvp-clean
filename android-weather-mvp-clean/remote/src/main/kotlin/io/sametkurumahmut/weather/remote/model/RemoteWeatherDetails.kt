package io.sametkurumahmut.weather.remote.model

import com.google.gson.annotations.SerializedName

/**
 * @property temp Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 * @property maxTemp Maximum temperature at the moment. This is deviation from current temp that
 * is possible for large cities and megalopolises geographically expanded
 * (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 * @property minTemp Minimum temperature at the moment. This is deviation from current temp that
 * is possible for large cities and megalopolises geographically expanded
 * (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 */
data class RemoteWeatherDetails(
        val temp: Double,
        @SerializedName("temp_max") val maxTemp: Double,
        @SerializedName("temp_min") val minTemp: Double)

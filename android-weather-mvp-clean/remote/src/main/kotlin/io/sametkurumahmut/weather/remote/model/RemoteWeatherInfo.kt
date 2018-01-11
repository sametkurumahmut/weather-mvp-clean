package io.sametkurumahmut.weather.remote.model

import com.google.gson.annotations.SerializedName

/**
 * More info weather condition codes.
 *
 * @property state Group of weather parameters (Rain, Snow, Extreme etc.)
 * @property description Weather condition within the group
 * @property icon Weather icon id
 */
data class RemoteWeatherInfo(
        @SerializedName("main") val state: String,
        val description: String,
        val icon: String)

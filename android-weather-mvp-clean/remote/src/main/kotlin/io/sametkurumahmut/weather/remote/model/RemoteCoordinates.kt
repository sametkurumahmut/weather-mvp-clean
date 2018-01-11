package io.sametkurumahmut.weather.remote.model

import com.google.gson.annotations.SerializedName

/**
 * @property latitude City geo location, latitude
 * @property longitude City geo location, longitude
 */
data class RemoteCoordinates(
        @SerializedName("lat") val latitude: Double,
        @SerializedName("lon") val longitude: Double)

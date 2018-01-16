package io.sametkurumahmut.weather.domain.device.net

interface NetInfo {

    fun isConnected(): Boolean

    fun isNotConnected(): Boolean
}

package io.sametkurumahmut.weather.remote.extensions

import io.sametkurumahmut.weather.remote.model.RemoteWeatherInfo
import io.sametkurumahmut.weather.remote.service.response.WeatherAPI

val RemoteWeatherInfo.iconUri
    get() = String.format(WeatherAPI.IMG_ICON_FORMAT, this.icon)

package io.sametkurumahmut.weather.remote.extensions

import io.sametkurumahmut.weather.remote.model.RemoteWeatherInfo

val RemoteWeatherInfo.iconUri
    get() = "https://openweathermap.org/img/w/${this.icon}.png"

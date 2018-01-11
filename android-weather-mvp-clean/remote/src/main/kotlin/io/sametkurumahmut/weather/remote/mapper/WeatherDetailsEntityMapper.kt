package io.sametkurumahmut.weather.remote.mapper

import io.sametkurumahmut.weather.common.mapper.MapperFrom
import io.sametkurumahmut.weather.data.model.WeatherDetailsEntity
import io.sametkurumahmut.weather.remote.model.RemoteWeatherDetails
import javax.inject.Inject

open class WeatherDetailsEntityMapper @Inject constructor()
    : MapperFrom<RemoteWeatherDetails, WeatherDetailsEntity> {

    override fun from(type: RemoteWeatherDetails) = WeatherDetailsEntity(
            type.temp,
            type.maxTemp,
            type.minTemp)
}

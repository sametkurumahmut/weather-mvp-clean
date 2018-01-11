package io.sametkurumahmut.weather.remote.mapper

import io.sametkurumahmut.weather.common.mapper.MapperFrom
import io.sametkurumahmut.weather.data.model.WeatherInfoEntity
import io.sametkurumahmut.weather.remote.extensions.iconUri
import io.sametkurumahmut.weather.remote.model.RemoteWeatherInfo
import javax.inject.Inject

open class WeatherInfoEntityMapper @Inject constructor()
    : MapperFrom<RemoteWeatherInfo, WeatherInfoEntity> {

    override fun from(type: RemoteWeatherInfo) = WeatherInfoEntity(
            type.state,
            type.description,
            type.iconUri)
}

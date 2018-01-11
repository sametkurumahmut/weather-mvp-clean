package io.sametkurumahmut.weather.data.mapper

import io.sametkurumahmut.weather.common.mapper.Mapper
import io.sametkurumahmut.weather.data.model.WeatherDetailsEntity
import io.sametkurumahmut.weather.domain.model.WeatherDetails
import javax.inject.Inject

open class WeatherDetailsMapper @Inject constructor()
    : Mapper<WeatherDetailsEntity, WeatherDetails> {

    override fun from(type: WeatherDetailsEntity) = WeatherDetails(
            type.temp,
            type.maxTemp,
            type.minTemp)

    override fun to(type: WeatherDetails) = WeatherDetailsEntity(
            type.temp,
            type.maxTemp,
            type.minTemp)
}

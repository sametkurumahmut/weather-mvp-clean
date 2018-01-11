package io.sametkurumahmut.weather.data.mapper

import io.sametkurumahmut.weather.common.mapper.Mapper
import io.sametkurumahmut.weather.data.model.WeatherInfoEntity
import io.sametkurumahmut.weather.domain.model.WeatherInfo
import javax.inject.Inject

open class WeatherInfoMapper @Inject constructor() : Mapper<WeatherInfoEntity, WeatherInfo> {

    override fun from(type: WeatherInfoEntity) = WeatherInfo(
            type.state,
            type.description,
            type.iconUri)

    override fun to(type: WeatherInfo) = WeatherInfoEntity(
            type.state,
            type.description,
            type.iconUri)
}

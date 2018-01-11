package io.sametkurumahmut.weather.cache.mobile.android.mapper

import io.sametkurumahmut.weather.cache.mobile.android.model.CachedWeatherInfo
import io.sametkurumahmut.weather.common.mapper.Mapper
import io.sametkurumahmut.weather.data.model.WeatherInfoEntity
import javax.inject.Inject

open class WeatherInfoEntityMapper @Inject constructor()
    : Mapper<CachedWeatherInfo, WeatherInfoEntity> {

    override fun from(type: CachedWeatherInfo) = WeatherInfoEntity(
            type.state,
            type.description,
            type.iconUri)

    override fun to(type: WeatherInfoEntity) = CachedWeatherInfo(
            type.state,
            type.description,
            type.iconUri)
}

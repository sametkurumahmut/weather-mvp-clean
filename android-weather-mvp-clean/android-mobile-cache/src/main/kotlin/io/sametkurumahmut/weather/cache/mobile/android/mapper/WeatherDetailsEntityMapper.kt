package io.sametkurumahmut.weather.cache.mobile.android.mapper

import io.sametkurumahmut.weather.cache.mobile.android.model.CachedWeatherDetails
import io.sametkurumahmut.weather.common.mapper.Mapper
import io.sametkurumahmut.weather.data.model.WeatherDetailsEntity
import javax.inject.Inject

open class WeatherDetailsEntityMapper @Inject constructor()
    : Mapper<CachedWeatherDetails, WeatherDetailsEntity> {

    override fun from(type: CachedWeatherDetails) = WeatherDetailsEntity(
            type.temp,
            type.maxTemp,
            type.minTemp)

    override fun to(type: WeatherDetailsEntity) = CachedWeatherDetails(
            type.temp,
            type.maxTemp,
            type.minTemp)
}

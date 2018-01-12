package io.sametkurumahmut.weather.presentation.mapper

import io.sametkurumahmut.weather.common.mapper.MapperTo
import io.sametkurumahmut.weather.domain.model.WeatherDetails
import io.sametkurumahmut.weather.presentation.model.WeatherDetailsView
import javax.inject.Inject

open class WeatherDetailsMapper @Inject constructor()
    : MapperTo<WeatherDetailsView, WeatherDetails> {

    override fun to(type: WeatherDetails) = WeatherDetailsView(
            type.temp,
            type.maxTemp,
            type.minTemp)
}

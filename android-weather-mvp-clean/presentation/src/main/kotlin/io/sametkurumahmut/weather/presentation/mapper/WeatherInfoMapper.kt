package io.sametkurumahmut.weather.presentation.mapper

import io.sametkurumahmut.weather.common.mapper.MapperTo
import io.sametkurumahmut.weather.domain.model.WeatherInfo
import io.sametkurumahmut.weather.presentation.model.WeatherInfoView
import javax.inject.Inject

open class WeatherInfoMapper @Inject constructor() : MapperTo<WeatherInfoView, WeatherInfo> {

    override fun to(type: WeatherInfo) = WeatherInfoView(type.state, type.description, type.iconUri)
}

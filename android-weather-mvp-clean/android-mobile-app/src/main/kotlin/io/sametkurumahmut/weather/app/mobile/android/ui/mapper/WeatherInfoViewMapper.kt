package io.sametkurumahmut.weather.app.mobile.android.ui.mapper

import io.sametkurumahmut.weather.app.mobile.android.ui.model.WeatherInfoViewModel
import io.sametkurumahmut.weather.common.mapper.MapperTo
import io.sametkurumahmut.weather.presentation.model.WeatherInfoView
import javax.inject.Inject

open class WeatherInfoViewMapper @Inject constructor()
    : MapperTo<WeatherInfoViewModel, WeatherInfoView> {

    override fun to(type: WeatherInfoView) = WeatherInfoViewModel(
            type.state.capitalize(),
            type.description.capitalize(),
            type.iconUri)
}

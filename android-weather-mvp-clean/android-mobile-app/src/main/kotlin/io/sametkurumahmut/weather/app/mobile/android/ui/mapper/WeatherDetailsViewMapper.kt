package io.sametkurumahmut.weather.app.mobile.android.ui.mapper

import io.sametkurumahmut.weather.app.mobile.android.ui.model.WeatherDetailsViewModel
import io.sametkurumahmut.weather.common.mapper.MapperTo
import io.sametkurumahmut.weather.presentation.model.WeatherDetailsView
import javax.inject.Inject
import kotlin.math.roundToInt

open class WeatherDetailsViewMapper @Inject constructor()
    : MapperTo<WeatherDetailsViewModel, WeatherDetailsView> {

    override fun to(type: WeatherDetailsView) = WeatherDetailsViewModel(
            type.temp.roundToInt(),
            type.maxTemp.roundToInt(),
            type.minTemp.roundToInt())
}

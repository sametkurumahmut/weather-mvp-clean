package io.sametkurumahmut.weather.app.mobile.android.ui.mapper

import io.sametkurumahmut.weather.app.mobile.android.ui.model.CoordinatesViewModel
import io.sametkurumahmut.weather.common.mapper.MapperTo
import io.sametkurumahmut.weather.presentation.model.CoordinatesView
import javax.inject.Inject
import kotlin.math.roundToInt

open class CoordinatesViewMapper @Inject constructor()
    : MapperTo<CoordinatesViewModel, CoordinatesView> {

    override fun to(type: CoordinatesView) = CoordinatesViewModel(
            type.latitude.roundToInt(),
            type.longitude.roundToInt())
}

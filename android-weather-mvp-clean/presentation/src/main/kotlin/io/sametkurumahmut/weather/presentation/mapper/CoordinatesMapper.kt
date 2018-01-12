package io.sametkurumahmut.weather.presentation.mapper

import io.sametkurumahmut.weather.common.mapper.MapperTo
import io.sametkurumahmut.weather.domain.model.Coordinates
import io.sametkurumahmut.weather.presentation.model.CoordinatesView
import javax.inject.Inject

open class CoordinatesMapper @Inject constructor() : MapperTo<CoordinatesView, Coordinates> {

    override fun to(type: Coordinates) = CoordinatesView(type.latitude, type.longitude)
}

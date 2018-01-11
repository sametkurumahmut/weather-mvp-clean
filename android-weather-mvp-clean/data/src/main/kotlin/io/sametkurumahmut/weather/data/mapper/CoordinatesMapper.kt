package io.sametkurumahmut.weather.data.mapper

import io.sametkurumahmut.weather.common.mapper.Mapper
import io.sametkurumahmut.weather.data.model.CoordinatesEntity
import io.sametkurumahmut.weather.domain.model.Coordinates
import javax.inject.Inject

open class CoordinatesMapper @Inject constructor() : Mapper<CoordinatesEntity, Coordinates> {

    override fun from(type: CoordinatesEntity) = Coordinates(type.latitude, type.longitude)

    override fun to(type: Coordinates) = CoordinatesEntity(type.latitude, type.longitude)
}

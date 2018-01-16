package io.sametkurumahmut.weather.remote.mapper

import io.sametkurumahmut.weather.common.mapper.MapperFrom
import io.sametkurumahmut.weather.data.model.CoordinatesEntity
import io.sametkurumahmut.weather.remote.model.RemoteCoordinates
import javax.inject.Inject

open class CoordinatesEntityMapper @Inject constructor()
    : MapperFrom<RemoteCoordinates, CoordinatesEntity> {

    override fun from(type: RemoteCoordinates) = CoordinatesEntity(type.latitude, type.longitude)
}

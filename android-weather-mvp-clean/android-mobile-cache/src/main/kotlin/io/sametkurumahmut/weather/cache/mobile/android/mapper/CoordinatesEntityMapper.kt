package io.sametkurumahmut.weather.cache.mobile.android.mapper

import io.sametkurumahmut.weather.cache.mobile.android.model.CachedCoordinates
import io.sametkurumahmut.weather.common.mapper.Mapper
import io.sametkurumahmut.weather.data.model.CoordinatesEntity
import javax.inject.Inject

open class CoordinatesEntityMapper @Inject constructor()
    : Mapper<CachedCoordinates, CoordinatesEntity> {

    override fun from(type: CachedCoordinates) = CoordinatesEntity(type.latitude, type.longitude)

    override fun to(type: CoordinatesEntity) = CachedCoordinates(type.latitude, type.longitude)
}

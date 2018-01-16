package io.sametkurumahmut.weather.common.mapper

interface MapperTo<out From, in To> {

    fun to(type: To): From
}

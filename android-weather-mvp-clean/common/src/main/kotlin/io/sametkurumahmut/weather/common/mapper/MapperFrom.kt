package io.sametkurumahmut.weather.common.mapper

interface MapperFrom<in From, out To> {

    fun from(type: From): To
}

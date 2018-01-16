package io.sametkurumahmut.weather.domain.interactor

interface UseCase<out T, in Params> {

    fun execute(params: Params? = null): T
}

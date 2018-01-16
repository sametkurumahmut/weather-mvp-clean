package io.sametkurumahmut.weather.presentation

interface BaseView {

    fun dispatchUnhandledError(error: Throwable) {
        throw error
    }
}

package io.sametkurumahmut.weather.domain.device.net

import io.reactivex.Completable

interface NetStateChangeListener {

    fun onNetConnected(): Completable
}

package io.sametkurumahmut.weather.domain.interactor.net

import io.sametkurumahmut.weather.domain.device.net.NetStateChangeListener
import io.sametkurumahmut.weather.domain.interactor.CompletableUseCase
import javax.inject.Inject

open class OnNetConnected @Inject constructor(
        private val netStateChangeListener: NetStateChangeListener)
    : CompletableUseCase<Nothing>() {

    override fun build(params: Nothing?) = this.netStateChangeListener.onNetConnected()
}

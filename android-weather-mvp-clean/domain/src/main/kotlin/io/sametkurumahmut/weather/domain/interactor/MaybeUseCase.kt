package io.sametkurumahmut.weather.domain.interactor

import io.reactivex.Maybe

abstract class MaybeUseCase<T, in Params> : DisposableUseCase<Maybe<T>, Params>() {

    final override fun execute(params: Params?): Maybe<T> = this.build(params).doOnSubscribe {
        this.disposables.add(it)
    }
}

package io.sametkurumahmut.weather.domain.interactor

import io.reactivex.Single

abstract class SingleUseCase<T, in Params> : DisposableUseCase<Single<T>, Params>() {

    final override fun execute(params: Params?): Single<T> = this.build(params).doOnSubscribe {
        this.disposables.add(it)
    }
}

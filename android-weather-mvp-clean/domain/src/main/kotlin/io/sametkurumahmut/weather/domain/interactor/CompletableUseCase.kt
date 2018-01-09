package io.sametkurumahmut.weather.domain.interactor

import io.reactivex.Completable

abstract class CompletableUseCase<in Params> : DisposableUseCase<Completable, Params>() {

    final override fun execute(params: Params?): Completable = this.build(params).doOnSubscribe {
        this.disposables.add(it)
    }
}

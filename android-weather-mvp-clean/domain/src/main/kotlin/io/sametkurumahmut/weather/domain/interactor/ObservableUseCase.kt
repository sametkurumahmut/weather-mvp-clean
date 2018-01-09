package io.sametkurumahmut.weather.domain.interactor

import io.reactivex.Observable

abstract class ObservableUseCase<T, in Params> : DisposableUseCase<Observable<T>, Params>() {

    final override fun execute(params: Params?): Observable<T> = this.build(params).doOnSubscribe {
        this.disposables.add(it)
    }
}

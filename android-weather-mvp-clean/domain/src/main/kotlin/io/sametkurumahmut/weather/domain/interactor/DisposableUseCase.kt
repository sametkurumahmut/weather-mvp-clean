package io.sametkurumahmut.weather.domain.interactor

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class DisposableUseCase<out T, in Params> : UseCase<T, Params>, Disposable {

    //region Protected Properties
    protected val disposables = CompositeDisposable()
    //endregion

    //region Public Methods
    fun clear() {
        this.disposables.clear()
    }

    //region UseCase Methods
    override fun execute(params: Params?): T = this.build(params)
    //endregion

    //region Disposable Methods
    override fun dispose() {
        this.disposables.dispose()
    }

    override fun isDisposed() = this.disposables.isDisposed
    //endregion
    //endregion

    //region Protected Abstract Methods
    protected abstract fun build(params: Params?): T
    //endregion
}

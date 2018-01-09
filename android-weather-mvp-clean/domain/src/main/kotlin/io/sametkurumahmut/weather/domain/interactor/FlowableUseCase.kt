package io.sametkurumahmut.weather.domain.interactor

import io.reactivex.Flowable

interface FlowableUseCase<T, in Params> : UseCase<Flowable<T>, Params>

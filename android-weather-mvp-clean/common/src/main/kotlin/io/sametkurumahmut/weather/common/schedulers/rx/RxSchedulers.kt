package io.sametkurumahmut.weather.common.schedulers.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

object RxSchedulers : RxSchedulerProvider {

    override val computation: Scheduler
        get() = Schedulers.computation()

    override val io: Scheduler
        get() = Schedulers.io()

    override val newThread: Scheduler
        get() = Schedulers.newThread()

    override val single: Scheduler
        get() = Schedulers.single()

    override val trampoline: Scheduler
        get() = Schedulers.trampoline()
}

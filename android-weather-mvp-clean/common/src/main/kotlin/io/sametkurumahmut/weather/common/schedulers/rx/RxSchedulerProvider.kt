package io.sametkurumahmut.weather.common.schedulers.rx

import io.reactivex.Scheduler

interface RxSchedulerProvider {

    val computation: Scheduler

    val io: Scheduler

    val newThread: Scheduler

    val single: Scheduler

    val trampoline: Scheduler
}

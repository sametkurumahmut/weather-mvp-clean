package io.sametkurumahmut.weather.app.mobile.android.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.sametkurumahmut.weather.common.schedulers.AppSchedulerProvider
import io.sametkurumahmut.weather.common.schedulers.rx.RxSchedulers

object AppSchedulers : AppSchedulerProvider {

    //region UiSchedulerProvider Properties
    override val ui: Scheduler
        get() = AndroidSchedulers.mainThread()
    //endregion

    //region RxSchedulerProvider Properties
    override val computation: Scheduler
        get() = RxSchedulers.computation

    override val io: Scheduler
        get() = RxSchedulers.io

    override val newThread: Scheduler
        get() = RxSchedulers.newThread

    override val single: Scheduler
        get() = RxSchedulers.single

    override val trampoline: Scheduler
        get() = RxSchedulers.trampoline
    //endregion
}

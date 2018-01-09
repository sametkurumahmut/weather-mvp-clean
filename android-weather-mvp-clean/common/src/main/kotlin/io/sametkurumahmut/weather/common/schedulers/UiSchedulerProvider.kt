package io.sametkurumahmut.weather.common.schedulers

import io.reactivex.Scheduler

interface UiSchedulerProvider {

    val ui: Scheduler
}

package io.sametkurumahmut.weather.common.schedulers

import io.sametkurumahmut.weather.common.schedulers.rx.RxSchedulerProvider

interface AppSchedulerProvider : UiSchedulerProvider, RxSchedulerProvider

package io.sametkurumahmut.weather.app.mobile.android.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.sametkurumahmut.weather.app.mobile.android.injection.scope.PerActivity
import io.sametkurumahmut.weather.app.mobile.android.ui.activity.MainActivity

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}

package io.sametkurumahmut.weather.app.mobile.android.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import io.sametkurumahmut.weather.app.mobile.android.App
import io.sametkurumahmut.weather.app.mobile.android.injection.module.AppModule
import io.sametkurumahmut.weather.app.mobile.android.injection.scope.PerApplication

/**
 * When [AppComponent] is build with its modules,
 * we have a graph with all provided instances.
 * This component is root of our Dagger graph.
 *
 * @PerApplication  This component is responsible for application scope instances.
 */
@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        /**
         * Binds [Application] instance to [AppComponent].
         */
        @BindsInstance
        fun application(application: Application): Builder

        /**
         * A [Builder] interface has a [build] method which returns the [AppComponent].
         */
        fun build(): AppComponent
    }

    /**
     * Inject all known dependencies into
     * @param app that is the target class.
     */
    fun inject(app: App)
}

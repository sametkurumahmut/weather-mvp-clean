package io.sametkurumahmut.weather.app.mobile.android.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.sametkurumahmut.weather.app.mobile.android.BuildConfig
import io.sametkurumahmut.weather.app.mobile.android.injection.scope.PerApplication
import io.sametkurumahmut.weather.app.mobile.android.schedulers.AppSchedulers
import io.sametkurumahmut.weather.cache.mobile.android.prefs.Prefs
import io.sametkurumahmut.weather.cache.mobile.android.prefs.PrefsImpl
import io.sametkurumahmut.weather.cache.mobile.android.repository.CityWeatherCacheImpl
import io.sametkurumahmut.weather.cache.mobile.android.room.WeatherDb
import io.sametkurumahmut.weather.common.schedulers.AppSchedulerProvider
import io.sametkurumahmut.weather.common.schedulers.UiSchedulerProvider
import io.sametkurumahmut.weather.common.schedulers.rx.RxSchedulerProvider
import io.sametkurumahmut.weather.data.mapper.CityWeatherMapper
import io.sametkurumahmut.weather.data.repository.CityWeatherDataRepository
import io.sametkurumahmut.weather.data.repository.cache.CityWeatherCache
import io.sametkurumahmut.weather.data.repository.remote.CityWeatherRemote
import io.sametkurumahmut.weather.data.source.cache.CityWeatherCacheDataSource
import io.sametkurumahmut.weather.data.source.remote.CityWeatherRemoteDataSource
import io.sametkurumahmut.weather.device.mobile.android.location.LocationGatewayImpl
import io.sametkurumahmut.weather.device.mobile.android.net.NetInfoImpl
import io.sametkurumahmut.weather.device.mobile.android.net.NetStateChangeListenerImpl
import io.sametkurumahmut.weather.device.mobile.android.settings.AppSettingsImpl
import io.sametkurumahmut.weather.domain.data.repository.CityWeatherRepository
import io.sametkurumahmut.weather.domain.device.location.LocationGateway
import io.sametkurumahmut.weather.domain.device.net.NetInfo
import io.sametkurumahmut.weather.domain.device.net.NetStateChangeListener
import io.sametkurumahmut.weather.domain.device.settings.AppSettings
import io.sametkurumahmut.weather.domain.interactor.location.GetLocation
import io.sametkurumahmut.weather.domain.interactor.net.OnNetConnected
import io.sametkurumahmut.weather.remote.mapper.CityWeatherEntityMapper
import io.sametkurumahmut.weather.remote.repository.CityWeatherRemoteImpl
import io.sametkurumahmut.weather.remote.service.WeatherService
import io.sametkurumahmut.weather.remote.service.WeatherServiceFactory

typealias CityWeatherEntityCacheMapper = io.sametkurumahmut.weather.cache.mobile.android.mapper.CityWeatherEntityMapper

/**
 * Module used to provide dependencies at application-level.
 */
@Module
open class AppModule {

    @PerApplication
    @Provides
    fun provideAppSchedulerProvider(): AppSchedulerProvider = AppSchedulers

    @PerApplication
    @Provides
    fun provideAppSettings(context: Context): AppSettings = AppSettingsImpl(context)

    @PerApplication
    @Provides
    fun provideContext(application: Application): Context = application

    @PerApplication
    @Provides
    internal fun provideCityWeatherCache(
            context: Context,
            prefs: Prefs,
            cityWeatherEntityMapper: CityWeatherEntityCacheMapper): CityWeatherCache =
            CityWeatherCacheImpl(
                    WeatherDb.getInstance(context).weatherDao(),
                    prefs,
                    cityWeatherEntityMapper)

    @PerApplication
    @Provides
    internal fun provideCityWeatherRemote(
            weatherService: WeatherService,
            cityWeatherEntityMapper: CityWeatherEntityMapper): CityWeatherRemote
            = CityWeatherRemoteImpl(weatherService, cityWeatherEntityMapper)

    @PerApplication
    @Provides
    fun provideCityWeatherRepository(
            cityWeatherCache: CityWeatherCache,
            cityWeatherCacheDataSource: CityWeatherCacheDataSource,
            cityWeatherRemoteDataSource: CityWeatherRemoteDataSource,
            getLocationUseCase: GetLocation,
            onNetConnectedUseCase: OnNetConnected,
            netInfo: NetInfo,
            cityWeatherMapper: CityWeatherMapper,
            rxSchedulerProvider: RxSchedulerProvider): CityWeatherRepository =
            CityWeatherDataRepository(
                    cityWeatherCache,
                    cityWeatherCacheDataSource,
                    cityWeatherRemoteDataSource,
                    getLocationUseCase,
                    onNetConnectedUseCase,
                    netInfo,
                    cityWeatherMapper,
                    rxSchedulerProvider)

    @PerApplication
    @Provides
    fun provideLocationProvider(
            context: Context,
            uiScheduler: UiSchedulerProvider): LocationGateway
            = LocationGatewayImpl(context, uiScheduler)

    @PerApplication
    @Provides
    internal fun provideNetInfo(context: Context): NetInfo = NetInfoImpl(context)

    @PerApplication
    @Provides
    internal fun provideNetStateChangeListener(context: Context, netInfo: NetInfo)
            : NetStateChangeListener = NetStateChangeListenerImpl(context, netInfo)

    @PerApplication
    @Provides
    fun providePrefs(context: Context): Prefs = PrefsImpl(context)

    @PerApplication
    @Provides
    fun provideRxSchedulerProvider(): RxSchedulerProvider = AppSchedulers

    @PerApplication
    @Provides
    fun provideUiSchedulerProvider(): UiSchedulerProvider = AppSchedulers

    @PerApplication
    @Provides
    fun provideWeatherService(): WeatherService = WeatherServiceFactory.create(BuildConfig.DEBUG)
}

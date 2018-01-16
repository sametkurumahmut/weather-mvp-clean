package io.sametkurumahmut.weather.app.mobile.android.injection.module

import dagger.Module
import dagger.Provides
import io.sametkurumahmut.weather.app.mobile.android.injection.scope.PerActivity
import io.sametkurumahmut.weather.app.mobile.android.ui.activity.MainActivity
import io.sametkurumahmut.weather.app.mobile.android.ui.mapper.CityWeatherViewMapper
import io.sametkurumahmut.weather.app.mobile.android.ui.mapper.CoordinatesViewMapper
import io.sametkurumahmut.weather.app.mobile.android.ui.mapper.WeatherDetailsViewMapper
import io.sametkurumahmut.weather.app.mobile.android.ui.mapper.WeatherInfoViewMapper
import io.sametkurumahmut.weather.app.mobile.android.ui.model.CityWeatherViewModel
import io.sametkurumahmut.weather.common.mapper.MapperTo
import io.sametkurumahmut.weather.common.schedulers.AppSchedulerProvider
import io.sametkurumahmut.weather.device.mobile.android.permission.ActivityCompatPermissionClient
import io.sametkurumahmut.weather.device.mobile.android.permission.PermissionClient
import io.sametkurumahmut.weather.domain.device.net.NetInfo
import io.sametkurumahmut.weather.domain.device.settings.AppSettings
import io.sametkurumahmut.weather.domain.interactor.GetCurrentLocationWeather
import io.sametkurumahmut.weather.domain.interactor.net.OnNetConnected
import io.sametkurumahmut.weather.presentation.main.MainContract
import io.sametkurumahmut.weather.presentation.main.MainPresenter
import io.sametkurumahmut.weather.presentation.mapper.CityWeatherMapper
import io.sametkurumahmut.weather.presentation.model.CityWeatherView

/**
 * Module used to provide dependencies at activity-level.
 */
@Module
open class MainActivityModule {

    @PerActivity
    @Provides
    internal fun provideCityWeatherViewMapper(
            coordinatesMapper: CoordinatesViewMapper,
            weatherInfoMapper: WeatherInfoViewMapper,
            weatherDetailsMapper: WeatherDetailsViewMapper)
            : MapperTo<CityWeatherViewModel, CityWeatherView>
            = CityWeatherViewMapper(coordinatesMapper, weatherInfoMapper, weatherDetailsMapper)

    @PerActivity
    @Provides
    internal fun provideMainView(mainActivity: MainActivity): MainContract.View = mainActivity

    @PerActivity
    @Provides
    internal fun provideMainPresenter(
            view: MainContract.View,
            getCurrentLocationWeatherUseCase: GetCurrentLocationWeather,
            onNetConnectedUseCase: OnNetConnected,
            netInfo: NetInfo,
            appSettings: AppSettings,
            cityWeatherMapper: CityWeatherMapper,
            schedulers: AppSchedulerProvider): MainContract.Presenter =
            MainPresenter(
                    view,
                    getCurrentLocationWeatherUseCase,
                    onNetConnectedUseCase,
                    netInfo,
                    appSettings,
                    cityWeatherMapper,
                    schedulers)

    @PerActivity
    @Provides
    internal fun providePermissionClient(mainActivity: MainActivity): PermissionClient
            = ActivityCompatPermissionClient(mainActivity)
}

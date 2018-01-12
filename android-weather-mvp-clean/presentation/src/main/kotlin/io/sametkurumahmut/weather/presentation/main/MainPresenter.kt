package io.sametkurumahmut.weather.presentation.main

import io.sametkurumahmut.weather.common.mapper.MapperTo
import io.sametkurumahmut.weather.common.schedulers.AppSchedulerProvider
import io.sametkurumahmut.weather.domain.WeatherInfoNotAvailableException
import io.sametkurumahmut.weather.domain.device.net.NetInfo
import io.sametkurumahmut.weather.domain.device.settings.AppSettings
import io.sametkurumahmut.weather.domain.interactor.CompletableUseCase
import io.sametkurumahmut.weather.domain.interactor.ObservableUseCase
import io.sametkurumahmut.weather.domain.model.CityWeather
import io.sametkurumahmut.weather.presentation.model.CityWeatherView
import javax.inject.Inject

class MainPresenter @Inject constructor(
        private val view: MainContract.View,
        private val getCurrentLocationWeatherUseCase: ObservableUseCase<CityWeather, Nothing>,
        private val onNetConnectedUseCase: CompletableUseCase<Nothing>,
        private val netInfo: NetInfo,
        private val appSettings: AppSettings,
        private val mapper: MapperTo<CityWeatherView, CityWeather>,
        private val schedulers: AppSchedulerProvider) : MainContract.Presenter {

    //region Properties
    private var isFetchedCityWeatherOnce = false
    //endregion

    //region MainContract.Presenter Methods
    //region PresenterLifeCycle Methods
    override fun start() {
        this.view.hideHotCityWeatherLayout()
    }

    override fun stop() {
        this.getCurrentLocationWeatherUseCase.dispose()
        this.onNetConnectedUseCase.dispose()
    }
    //endregion

    override fun loadCityWeather() {
        this.getCurrentLocationWeather()
    }

    override fun loadCityWeatherManually() {
        this.getCurrentLocationWeather(true)
    }

    override fun loadCityWeatherOnNetConnected() {
        if (this.netInfo.isConnected()) {
            this.loadCityWeatherManually()

            return
        }

        this.view.showNetworkUnavailableError()
        this.view.isRefreshingWeather = false
    }

    override fun openAppSettings() {
        this.appSettings.newOpenAppSettingsBuilder()
                .openInNewScreen()
                .excludeFromRecentApps()
                .build()
                .open()
    }
    //endregion

    //region Methods
    private fun getCurrentLocationWeather(isManualRefresh: Boolean = false) {
        this.getCurrentLocationWeatherUseCase.clear()
        var isFetchedFirstCityWeather = false

        fun setIsRefreshingWeather(value: Boolean) {
            if (isManualRefresh && this.view.isRefreshingWeather != value) {
                this.view.isRefreshingWeather = value
            }
        }

        this.getCurrentLocationWeatherUseCase.execute()
                .subscribeOn(this.schedulers.io)
                .observeOn(this.schedulers.ui)
                .doOnSubscribe { setIsRefreshingWeather(true) }
                .doFinally { setIsRefreshingWeather(false) }
                .subscribe(
                        {
                            if (!isFetchedFirstCityWeather) {
                                setIsRefreshingWeather(false)

                                isFetchedFirstCityWeather = true
                                if (!this.isFetchedCityWeatherOnce) {
                                    this.view.showHotCityWeatherLayout()
                                    this.isFetchedCityWeatherOnce = true
                                }
                            }

                            this.view.showCityWeather(this.mapper.to(it))
                        },
                        {
                            when (it) {
                                is WeatherInfoNotAvailableException -> {
                                    if (!this.isFetchedCityWeatherOnce) {
                                        this.view.showWeatherInfoNotAvailableError()
                                    }

                                    if (this.netInfo.isNotConnected()) {
                                        this.onNetConnected()
                                        this.view.showNetworkUnavailableError()
                                    }
                                }
                                else -> {
                                    this.view.dispatchUnhandledError(it)
                                }
                            }
                        })
    }

    private fun onNetConnected() {
        this.onNetConnectedUseCase.clear()

        this.onNetConnectedUseCase.execute()
                .subscribeOn(this.schedulers.io)
                .observeOn(this.schedulers.ui)
                .subscribe {
                    this.view.hideWeatherInfoNotAvailableError()
                    this.loadCityWeather()
                }
    }
    //endregion
}

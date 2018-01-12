package io.sametkurumahmut.weather.presentation.main

import io.sametkurumahmut.weather.presentation.BasePresenter
import io.sametkurumahmut.weather.presentation.BaseView
import io.sametkurumahmut.weather.presentation.model.CityWeatherView

interface MainContract {

    interface View : BaseView {

        var isRefreshingWeather: Boolean

        fun hideWeatherInfoNotAvailableError()

        fun hideHotCityWeatherLayout()

        fun showCityWeather(cityWeatherView: CityWeatherView)

        fun showHotCityWeatherLayout()

        fun showNetworkUnavailableError()

        fun showWeatherInfoNotAvailableError()
    }

    interface Presenter : BasePresenter {

        fun loadCityWeather()

        fun loadCityWeatherManually()

        fun loadCityWeatherOnNetConnected()

        fun openAppSettings()
    }
}

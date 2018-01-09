package io.sametkurumahmut.weather.domain.interactor

import io.sametkurumahmut.weather.domain.data.repository.CityWeatherRepository
import io.sametkurumahmut.weather.domain.model.CityWeather
import javax.inject.Inject

open class GetCurrentLocationWeather @Inject constructor(
        private val cityWeatherRepository: CityWeatherRepository)
    : ObservableUseCase<CityWeather, Nothing>() {

    override fun build(params: Nothing?) = this.cityWeatherRepository.getCurrentLocationWeather()
}

package io.sametkurumahmut.weather.app.mobile.android.ui.mapper

import io.sametkurumahmut.weather.app.mobile.android.ui.model.CityWeatherViewModel
import io.sametkurumahmut.weather.app.mobile.android.ui.model.CoordinatesViewModel
import io.sametkurumahmut.weather.app.mobile.android.ui.model.WeatherDetailsViewModel
import io.sametkurumahmut.weather.app.mobile.android.ui.model.WeatherInfoViewModel
import io.sametkurumahmut.weather.common.mapper.MapperTo
import io.sametkurumahmut.weather.presentation.model.CityWeatherView
import io.sametkurumahmut.weather.presentation.model.CoordinatesView
import io.sametkurumahmut.weather.presentation.model.WeatherDetailsView
import io.sametkurumahmut.weather.presentation.model.WeatherInfoView
import javax.inject.Inject

open class CityWeatherViewMapper @Inject constructor(
        private val coordinatesMapper: MapperTo<CoordinatesViewModel, CoordinatesView>,
        private val weatherInfoMapper: MapperTo<WeatherInfoViewModel, WeatherInfoView>,
        private val weatherDetailsMapper: MapperTo<WeatherDetailsViewModel, WeatherDetailsView>)
    : MapperTo<CityWeatherViewModel, CityWeatherView> {

    override fun to(type: CityWeatherView) = CityWeatherViewModel(
            type.name.capitalize(),
            this.coordinatesMapper.to(type.coordinates),
            this.weatherInfoMapper.to(type.weatherInfo),
            this.weatherDetailsMapper.to(type.weatherDetails))
}

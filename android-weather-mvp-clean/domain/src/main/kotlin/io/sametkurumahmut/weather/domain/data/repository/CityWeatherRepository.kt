package io.sametkurumahmut.weather.domain.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.sametkurumahmut.weather.domain.model.CityWeather

interface CityWeatherRepository {

    fun clearCurrentLocationWeather(): Completable

    fun getCurrentLocationWeather(): Observable<CityWeather>

    fun saveCityWeather(cityWeather: CityWeather): Completable
}

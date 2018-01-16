package io.sametkurumahmut.weather.remote.service

import io.reactivex.Single
import io.sametkurumahmut.weather.remote.model.RemoteCityWeather
import io.sametkurumahmut.weather.remote.service.response.Units
import io.sametkurumahmut.weather.remote.service.response.WeatherAPI
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("${WeatherAPI.REQUEST_WEATHER}${WeatherAPI.FIELD_UNITS_ADD}${Units.Celsius}")
    fun getByLocation(
            @Query(WeatherAPI.FIELD_LAT) latitude: Double,
            @Query(WeatherAPI.FIELD_LON) longitude: Double): Single<RemoteCityWeather>

    @GET(WeatherAPI.REQUEST_WEATHER)
    fun getByLocation(
            @Query(WeatherAPI.FIELD_LAT) latitude: Double,
            @Query(WeatherAPI.FIELD_LON) longitude: Double,
            @Query(WeatherAPI.FIELD_UNITS) units: String): Single<RemoteCityWeather>
}

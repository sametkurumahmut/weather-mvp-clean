package io.sametkurumahmut.weather.remote.service

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.sametkurumahmut.weather.remote.service.response.WeatherAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object WeatherServiceFactory {

    fun create(isDebug: Boolean): WeatherService = this.create<WeatherService>(
            buildOkHttpClient(createLoggingInterceptor(isDebug)),
            createGson())

    private fun buildOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
            OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()

    private inline fun <reified T> create(okHttpClient: OkHttpClient, gson: Gson): T =
            Retrofit.Builder()
                    .baseUrl(WeatherAPI.BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(T::class.java)

    private fun createGson() = GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    private fun createLoggingInterceptor(isDebug: Boolean) = HttpLoggingInterceptor().also {
        it.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}

package io.sametkurumahmut.weather.cache.mobile.android.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.sametkurumahmut.weather.cache.mobile.android.room.Db

@Entity(tableName = Db.WeatherTable.TABLE_NAME)
data class CachedCityWeather(
        @ColumnInfo(name = Db.WeatherTable.COL_NAME) var name: String,
        @Embedded(prefix = "coords_") val coordinates: CachedCoordinates,
        @Embedded(prefix = "weatherInfo_") val weatherInfo: CachedWeatherInfo,
        @Embedded(prefix = "weatherDetails_") val weatherDetails: CachedWeatherDetails) {

    @PrimaryKey
    @ColumnInfo(name = Db.WeatherTable.COL_ID)
    var id: Int = -1
}

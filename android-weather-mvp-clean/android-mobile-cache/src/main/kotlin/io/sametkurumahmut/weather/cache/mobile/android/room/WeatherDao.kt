package io.sametkurumahmut.weather.cache.mobile.android.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single
import io.sametkurumahmut.weather.cache.mobile.android.model.CachedCityWeather

/**
 * Data Access Object for the [Db.WeatherTable.TABLE_NAME] table.
 */
@Dao
interface WeatherDao {

    /**
     * Delete current location weather.
     *
     * @return the number of current location weather deleted. This should always be 1.
     */
    @Query("DELETE FROM ${Db.WeatherTable.TABLE_NAME} WHERE ${Db.WeatherTable.COL_ID} = -1")
    fun deleteCurrentLocationWeather(): Int

    /**
     * Select current location weather from the [Db.WeatherTable.TABLE_NAME] table.
     *
     * @return current location weather.
     */
    @Query("SELECT * FROM ${Db.WeatherTable.TABLE_NAME} WHERE ${Db.WeatherTable.COL_ID} = -1 LIMIT 1")
    fun getCurrentLocationWeather(): Single<CachedCityWeather>

    /**
     * Insert a city weather in the database. If the city weather already exists, replace it.
     *
     * @param cityWeather the city weather to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityWeather(cityWeather: CachedCityWeather)
}

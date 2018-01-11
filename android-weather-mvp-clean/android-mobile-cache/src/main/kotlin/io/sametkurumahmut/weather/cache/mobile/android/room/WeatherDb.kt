package io.sametkurumahmut.weather.cache.mobile.android.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import io.sametkurumahmut.weather.cache.mobile.android.model.CachedCityWeather

/**
 * The Room Database that contains the [Db.WeatherTable.TABLE_NAME] table.
 */
@Database(entities = [CachedCityWeather::class], version = Db.DB_VERSION)
abstract class WeatherDb : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {

        @Volatile private var instance: WeatherDb? = null

        private val LOCK = Any()

        @JvmStatic
        fun getInstance(context: Context): WeatherDb {
            val i = this.instance
            if (i != null) {
                return i
            }

            return synchronized(this.LOCK) {
                val _i = this.instance
                if (_i != null) {
                    _i
                } else {
                    this.instance = Room
                            .databaseBuilder(
                                    context.applicationContext,
                                    WeatherDb::class.java, Db.DB_NAME)
                            .build()
                    this.instance!!
                }
            }
        }
    }
}

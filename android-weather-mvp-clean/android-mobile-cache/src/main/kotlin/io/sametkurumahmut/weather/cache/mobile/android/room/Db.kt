package io.sametkurumahmut.weather.cache.mobile.android.room

object Db {

    const val DB_NAME = "Weather.db"
    const val DB_VERSION = 1

    object WeatherTable {

        const val TABLE_NAME = "weather"

        const val COL_NAME = "name"
        const val COL_ID = "id"
    }
}

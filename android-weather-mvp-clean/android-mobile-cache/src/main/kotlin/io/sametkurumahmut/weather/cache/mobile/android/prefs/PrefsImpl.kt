package io.sametkurumahmut.weather.cache.mobile.android.prefs

import android.content.Context
import javax.inject.Inject

class PrefsImpl @Inject constructor(context: Context) : Prefs {

    private val prefs = context.getSharedPreferences(
            "${context.applicationContext.packageName}.preferences",
            Context.MODE_PRIVATE)

    override var lastCacheTime: Long
        get() = this.prefs.getLong(PREF_KEY_LAST_CACHE_TIME, 0)
        set(value) = this.prefs.edit().putLong(PREF_KEY_LAST_CACHE_TIME, value).apply()

    companion object {

        private const val PREF_KEY_LAST_CACHE_TIME = "last_cache_time"
    }
}

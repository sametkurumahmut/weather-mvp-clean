package io.sametkurumahmut.weather.device.mobile.android.util.os

import android.os.Build

object BuildVersionUtil {

    fun isMOrHigher() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
}

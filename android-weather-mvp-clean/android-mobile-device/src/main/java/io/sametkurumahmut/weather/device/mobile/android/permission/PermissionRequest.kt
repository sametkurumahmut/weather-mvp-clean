package io.sametkurumahmut.weather.device.mobile.android.permission

interface PermissionRequest {

    val requestCode: Int

    val permissions: Array<String>

    val listener: PermissionListener
}

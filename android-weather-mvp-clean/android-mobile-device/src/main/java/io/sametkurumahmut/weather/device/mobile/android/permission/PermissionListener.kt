package io.sametkurumahmut.weather.device.mobile.android.permission

interface PermissionListener {

    fun onPermissionsDenied(
            permissionClient: PermissionClient,
            permissionRequest: PermissionRequest,
            permissions: Array<String>)

    fun onPermissionsNeverAskAgain(
            permissionClient: PermissionClient,
            permissionRequest: PermissionRequest,
            permissions: Array<String>)

    fun onRun(
            permissionClient: PermissionClient,
            permissionRequest: PermissionRequest,
            permissions: Array<String>)

    fun onShowRationale(
            permissionClient: PermissionClient,
            permissionRequest: PermissionRequest,
            permissions: Array<String>)
}

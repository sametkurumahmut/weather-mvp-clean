package io.sametkurumahmut.weather.device.mobile.android.permission

interface PermissionClient {

    fun checkAndRunPermissions(
            requestCode: Int,
            permissions: Array<String>,
            onPermissionGranted: (Array<String>) -> Unit,
            onShowRationale: (Array<String>) -> Unit)

    fun checkAndRunPermissions(
            requestCode: Int,
            permission: String,
            onPermissionGranted: (String) -> Unit,
            onShowRationale: (String) -> Unit)

    fun checkAndRunPermissions(permissionRequest: PermissionRequest)

    fun hasPermissions(vararg permissions: String): Boolean

    fun hasPermissions(permissionRequest: PermissionRequest): Boolean

    fun verifyPermissions(vararg grantResults: Int): Boolean

    fun runIfVerifiedOrShowRationale(
            permissions: Array<String>,
            grantResults: IntArray,
            onPermissionsGranted: (Array<String>) -> Unit,
            onPermissionsDenied: (Array<String>) -> Unit,
            onPermissionsNeverAskAgain: (Array<String>) -> Unit)

    fun runIfVerifiedOrShowRationale(
            permission: String,
            grantResult: Int,
            onPermissionGranted: (String) -> Unit,
            onPermissionDenied: (String) -> Unit,
            onPermissionNeverAskAgain: (String) -> Unit)

    fun runIfVerifiedOrShowRationale(permissionRequest: PermissionRequest, grantResults: IntArray)

    fun runIfVerifiedOrShowRationaleOnMatchRequestCode(
            checkRequestCode: Int,
            permissionRequestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray,
            onPermissionsGranted: (Array<String>) -> Unit,
            onPermissionsDenied: (Array<String>) -> Unit,
            onPermissionsNeverAskAgain: (Array<String>) -> Unit)

    fun runIfVerifiedOrShowRationaleOnMatchRequestCode(
            checkRequestCode: Int,
            permissionRequestCode: Int,
            permission: String,
            grantResult: Int,
            onPermissionGranted: (String) -> Unit,
            onPermissionDenied: (String) -> Unit,
            onPermissionNeverAskAgain: (String) -> Unit)

    fun runIfVerifiedOrShowRationaleOnMatchRequestCode(
            permissionRequest: PermissionRequest,
            checkRequestCode: Int,
            grantResults: IntArray)

    fun requestPermissions(requestCode: Int, vararg permissions: String)

    fun requestPermissions(permissionRequest: PermissionRequest)

    fun runPermissions(
            requestCode: Int,
            permissions: Array<String>,
            onShowRationale: (Array<String>) -> Unit)

    fun runPermissions(
            requestCode: Int,
            permission: String,
            onShowRationale: (String) -> Unit)

    fun runPermissions(permissionRequest: PermissionRequest)

    fun shouldShowRequestPermissionRationale(vararg permissions: String): Boolean

    fun shouldShowRequestPermissionRationale(permissionRequest: PermissionRequest): Boolean
}

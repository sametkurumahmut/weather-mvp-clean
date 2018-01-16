package io.sametkurumahmut.weather.device.mobile.android.permission

import android.app.Activity
import android.content.pm.PackageManager
import io.sametkurumahmut.weather.device.mobile.android.util.os.BuildVersionUtil
import io.sametkurumahmut.weather.device.mobile.android.util.permission.PermissionClientUtil

import javax.inject.Inject

open class ActivityPermissionClient @Inject constructor(private val activity: Activity)
    : PermissionClient {

    override fun checkAndRunPermissions(
            requestCode: Int,
            permissions: Array<String>,
            onPermissionGranted: (Array<String>) -> Unit,
            onShowRationale: (Array<String>) -> Unit) {
        PermissionClientUtil.checkAndRunPermissions(
                this,
                requestCode,
                permissions,
                onPermissionGranted,
                onShowRationale)
    }

    override fun checkAndRunPermissions(permissionRequest: PermissionRequest) {
        this.checkAndRunPermissions(
                permissionRequest.requestCode,
                permissionRequest.permissions,
                { permissionRequest.listener.onRun(this, permissionRequest, it) },
                { permissionRequest.listener.onShowRationale(this, permissionRequest, it) })
    }

    override fun checkAndRunPermissions(
            requestCode: Int,
            permission: String,
            onPermissionGranted: (String) -> Unit,
            onShowRationale: (String) -> Unit) {
        PermissionClientUtil.checkAndRunPermissions(
                this,
                requestCode,
                permission,
                onPermissionGranted,
                onShowRationale)
    }

    override fun hasPermissions(vararg permissions: String) =
            if (BuildVersionUtil.isMOrHigher()) {
                // Marshmallow+
                permissions.all {
                    this.activity.checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
                }
            } else {
                // Pre-Marshmallow
                true
            }

    override fun hasPermissions(permissionRequest: PermissionRequest)
            = this.hasPermissions(*permissionRequest.permissions)

    override fun requestPermissions(requestCode: Int, vararg permissions: String) {
        if (BuildVersionUtil.isMOrHigher()) {
            this.activity.requestPermissions(permissions, requestCode)
        }
    }

    override fun requestPermissions(permissionRequest: PermissionRequest) {
        this.requestPermissions(permissionRequest.requestCode, *permissionRequest.permissions)
    }

    override fun runPermissions(
            requestCode: Int,
            permissions: Array<String>,
            onShowRationale: (Array<String>) -> Unit) {
        PermissionClientUtil.runPermissions(this, requestCode, permissions, onShowRationale)
    }

    override fun runPermissions(
            requestCode: Int,
            permission: String,
            onShowRationale: (String) -> Unit) {
        PermissionClientUtil.runPermissions(this, requestCode, permission, onShowRationale)
    }

    override fun runPermissions(permissionRequest: PermissionRequest) {
        this.runPermissions(
                permissionRequest.requestCode,
                permissionRequest.permissions,
                { permissionRequest.listener.onShowRationale(this, permissionRequest, it) })
    }

    override fun shouldShowRequestPermissionRationale(vararg permissions: String)
            = PermissionClientUtil.shouldShowRequestPermissionRationale(this.activity, *permissions)

    override fun shouldShowRequestPermissionRationale(permissionRequest: PermissionRequest) =
            this.shouldShowRequestPermissionRationale(*permissionRequest.permissions)

    override fun runIfVerifiedOrShowRationale(
            permissions: Array<String>,
            grantResults: IntArray,
            onPermissionsGranted: (Array<String>) -> Unit,
            onPermissionsDenied: (Array<String>) -> Unit,
            onPermissionsNeverAskAgain: (Array<String>) -> Unit) {
        PermissionClientUtil.runIfVerifiedOrShowRationale(
                this,
                permissions,
                grantResults,
                onPermissionsGranted,
                onPermissionsDenied,
                onPermissionsNeverAskAgain)
    }

    override fun runIfVerifiedOrShowRationale(
            permission: String,
            grantResult: Int,
            onPermissionGranted: (String) -> Unit,
            onPermissionDenied: (String) -> Unit,
            onPermissionNeverAskAgain: (String) -> Unit) {
        PermissionClientUtil.runIfVerifiedOrShowRationale(
                this,
                permission,
                grantResult,
                onPermissionGranted,
                onPermissionDenied,
                onPermissionNeverAskAgain)
    }

    override fun runIfVerifiedOrShowRationale(
            permissionRequest: PermissionRequest,
            grantResults: IntArray) {
        this.runIfVerifiedOrShowRationale(
                permissionRequest.permissions,
                grantResults,
                { permissionRequest.listener.onRun(this, permissionRequest, it) },
                { permissionRequest.listener.onPermissionsDenied(this, permissionRequest, it) },
                {
                    permissionRequest.listener.onPermissionsNeverAskAgain(
                            this,
                            permissionRequest,
                            it)
                })
    }

    override fun runIfVerifiedOrShowRationaleOnMatchRequestCode(
            checkRequestCode: Int,
            permissionRequestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray,
            onPermissionsGranted: (Array<String>) -> Unit,
            onPermissionsDenied: (Array<String>) -> Unit,
            onPermissionsNeverAskAgain: (Array<String>) -> Unit) {
        PermissionClientUtil.runIfVerifiedOrShowRationaleOnMatchRequestCode(
                this,
                checkRequestCode,
                permissionRequestCode,
                permissions,
                grantResults,
                onPermissionsGranted,
                onPermissionsDenied,
                onPermissionsNeverAskAgain)
    }

    override fun runIfVerifiedOrShowRationaleOnMatchRequestCode(
            checkRequestCode: Int,
            permissionRequestCode: Int,
            permission: String,
            grantResult: Int,
            onPermissionGranted: (String) -> Unit,
            onPermissionDenied: (String) -> Unit,
            onPermissionNeverAskAgain: (String) -> Unit) {
        PermissionClientUtil.runIfVerifiedOrShowRationaleOnMatchRequestCode(
                this,
                checkRequestCode,
                permissionRequestCode,
                permission,
                grantResult,
                onPermissionGranted,
                onPermissionDenied,
                onPermissionNeverAskAgain)
    }

    override fun runIfVerifiedOrShowRationaleOnMatchRequestCode(
            permissionRequest: PermissionRequest,
            checkRequestCode: Int,
            grantResults: IntArray) {
        this.runIfVerifiedOrShowRationaleOnMatchRequestCode(
                checkRequestCode,
                permissionRequest.requestCode,
                permissionRequest.permissions,
                grantResults,
                { permissionRequest.listener.onRun(this, permissionRequest, it) },
                { permissionRequest.listener.onPermissionsDenied(this, permissionRequest, it) },
                {
                    permissionRequest.listener.onPermissionsNeverAskAgain(
                            this,
                            permissionRequest,
                            it)
                })
    }

    override fun verifyPermissions(vararg grantResults: Int)
            = PermissionClientUtil.verifyPermissions(*grantResults)
}

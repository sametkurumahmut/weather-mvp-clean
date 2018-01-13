package io.sametkurumahmut.weather.device.mobile.android.util.permission

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import io.sametkurumahmut.weather.device.mobile.android.permission.PermissionClient
import io.sametkurumahmut.weather.device.mobile.android.util.os.BuildVersionUtil

object PermissionClientUtil {

    fun checkAndRunPermissions(
            client: PermissionClient,
            requestCode: Int,
            permissions: Array<String>,
            onPermissionGranted: (Array<String>) -> Unit,
            onShowRationale: (Array<String>) -> Unit) {
        if (client.hasPermissions(*permissions)) {
            onPermissionGranted(permissions)

            return
        }

        if (!client.shouldShowRequestPermissionRationale(*permissions)) {
            client.requestPermissions(requestCode, *permissions)

            return
        }

        onShowRationale(permissions)
    }

    fun checkAndRunPermissions(
            client: PermissionClient,
            requestCode: Int,
            permission: String,
            onPermissionGranted: (String) -> Unit,
            onShowRationale: (String) -> Unit) {
        if (client.hasPermissions(permission)) {
            onPermissionGranted(permission)

            return
        }

        if (!client.shouldShowRequestPermissionRationale(permission)) {
            client.requestPermissions(requestCode, permission)

            return
        }

        onShowRationale(permission)
    }

    fun runPermissions(
            client: PermissionClient,
            requestCode: Int,
            permissions: Array<String>,
            onShowRationale: (Array<String>) -> Unit) {
        if (!client.shouldShowRequestPermissionRationale(*permissions)) {
            client.requestPermissions(requestCode, *permissions)

            return
        }

        onShowRationale(permissions)
    }

    fun runPermissions(
            client: PermissionClient,
            requestCode: Int,
            permission: String,
            onShowRationale: (String) -> Unit) {
        if (!client.shouldShowRequestPermissionRationale(permission)) {
            client.requestPermissions(requestCode, permission)

            return
        }

        onShowRationale(permission)
    }

    fun compatShouldShowRequestPermissionRationale(activity: Activity, vararg permissions: String)
            = permissions.any { ActivityCompat.shouldShowRequestPermissionRationale(activity, it) }

    fun shouldShowRequestPermissionRationale(activity: Activity, vararg permissions: String) =
            if (BuildVersionUtil.isMOrHigher()) {
                // Marshmallow+
                permissions.any { activity.shouldShowRequestPermissionRationale(it) }
            } else {
                // Pre-Marshmallow
                false
            }

    fun runIfVerifiedOrShowRationale(
            client: PermissionClient,
            permissions: Array<String>,
            grantResults: IntArray,
            onPermissionsGranted: (Array<String>) -> Unit,
            onPermissionsDenied: (Array<String>) -> Unit,
            onPermissionsNeverAskAgain: (Array<String>) -> Unit) {
        if (client.verifyPermissions(*grantResults)) {
            onPermissionsGranted(permissions)

            return
        }

        if (!client.shouldShowRequestPermissionRationale(*permissions)) {
            onPermissionsNeverAskAgain(permissions)

            return
        }

        onPermissionsDenied(permissions)
    }

    fun runIfVerifiedOrShowRationale(
            client: PermissionClient,
            permission: String,
            grantResult: Int,
            onPermissionGranted: (String) -> Unit,
            onPermissionDenied: (String) -> Unit,
            onPermissionNeverAskAgain: (String) -> Unit) {
        // Permission granted
        if (client.verifyPermissions(grantResult)) {
            onPermissionGranted(permission)

            return
        }

        // Permission denied and check should we show an explanation ?
        if (!client.shouldShowRequestPermissionRationale(permission)) {
            /**
             * The app has requested permission and the user denied the permission
             * and checked 'Never Ask Again'.
             *
             * Disable features of the app that depends on this permission
             * OR
             * Open another dialog explaining again the permission
             * OR
             * Directing to the app settings
             */
            onPermissionNeverAskAgain(permission)

            return
        }

        // Permission denied and show rationale
        /**
         * The app has requested permission and the user denied the permission
         * but didn't check 'Never Ask Again'.
         *
         * Explain the user why you need permission and
         * ask he wants to accept it with the rationale.
         */
        onPermissionDenied(permission)
    }

    fun runIfVerifiedOrShowRationaleOnMatchRequestCode(
            client: PermissionClient,
            checkRequestCode: Int,
            permissionsRequestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray,
            onPermissionsGranted: (Array<String>) -> Unit,
            onPermissionsDenied: (Array<String>) -> Unit,
            onPermissionsNeverAskAgain: (Array<String>) -> Unit) {
        when (checkRequestCode) {
            permissionsRequestCode -> runIfVerifiedOrShowRationale(
                    client,
                    permissions,
                    grantResults,
                    onPermissionsGranted,
                    onPermissionsDenied,
                    onPermissionsNeverAskAgain)
        }
    }

    fun runIfVerifiedOrShowRationaleOnMatchRequestCode(
            client: PermissionClient,
            checkRequestCode: Int,
            permissionRequestCode: Int,
            permission: String,
            grantResult: Int,
            onPermissionGranted: (String) -> Unit,
            onPermissionDenied: (String) -> Unit,
            onPermissionNeverAskAgain: (String) -> Unit) {
        when (checkRequestCode) {
            permissionRequestCode -> runIfVerifiedOrShowRationale(
                    client,
                    permission,
                    grantResult,
                    onPermissionGranted,
                    onPermissionDenied,
                    onPermissionNeverAskAgain)
        }
    }

    fun verifyPermissions(vararg grantResults: Int)
            = grantResults.none { it != PackageManager.PERMISSION_GRANTED }
}

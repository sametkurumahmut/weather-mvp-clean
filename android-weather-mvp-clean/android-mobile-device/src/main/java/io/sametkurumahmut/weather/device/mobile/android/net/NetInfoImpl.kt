package io.sametkurumahmut.weather.device.mobile.android.net

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.RequiresPermission
import io.sametkurumahmut.weather.domain.device.net.NetInfo
import javax.inject.Inject

class NetInfoImpl @Inject constructor(private val context: Context) : NetInfo {

    //region Properties
    private val connManager by lazy {
        this.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    //endregion

    //region NetInfo Methods
    /**
     * Returns `true` if network connectivity exists.
     */
    @RequiresPermission(anyOf = arrayOf(Manifest.permission.ACCESS_NETWORK_STATE))
    override fun isConnected() =
            this.connManager.activeNetworkInfo != null
                    && this.connManager.activeNetworkInfo!!.isConnectedOrConnecting

    /**
     * Returns `true` if network connectivity doesn't exist.
     */
    @RequiresPermission(anyOf = arrayOf(Manifest.permission.ACCESS_NETWORK_STATE))
    override fun isNotConnected() =
            this.connManager.activeNetworkInfo == null
                    || !this.connManager.activeNetworkInfo!!.isConnectedOrConnecting
    //endregion
}

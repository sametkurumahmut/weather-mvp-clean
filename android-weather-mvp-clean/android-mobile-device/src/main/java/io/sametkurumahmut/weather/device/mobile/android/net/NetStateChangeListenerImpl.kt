package io.sametkurumahmut.weather.device.mobile.android.net

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import io.reactivex.Completable
import io.sametkurumahmut.weather.domain.device.net.NetInfo
import io.sametkurumahmut.weather.domain.device.net.NetStateChangeListener
import javax.inject.Inject

class NetStateChangeListenerImpl @Inject constructor(
        private val context: Context,
        private val netInfo: NetInfo) : NetStateChangeListener {

    override fun onNetConnected(): Completable {
        return Completable.create {
            val isConnectedToNetworkReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    if (netInfo.isConnected()) {
                        it.onComplete()
                    }
                }
            }

            this.context.registerReceiver(
                    isConnectedToNetworkReceiver,
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

            it.setCancellable {
                this.context.unregisterReceiver(isConnectedToNetworkReceiver)
            }
        }
    }
}

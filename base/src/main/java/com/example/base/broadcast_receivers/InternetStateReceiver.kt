package com.example.base.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import com.example.base.ui.BaseActivity
import com.example.base.util.InternetConnection
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InternetStateReceiver @Inject constructor() : BroadcastReceiver() {

    var receivers: WeakHashMap<BaseActivity, Context> = WeakHashMap()

    @Inject
    lateinit var internetConnection: InternetConnection

    val INTERNET_RECEIVER = "android.net.conn.CONNECTIVITY_CHANGE"


    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.getAction() != null && intent.getAction() == INTERNET_RECEIVER) {
            if (internetConnection.isNetworkConnected()) {
//                internet connected
                for (receiver in receivers.entries)
                    receiver.key.onInternetConnected()

            } else {
//                internet not connected
                for (receiver in receivers.entries)
                    receiver.key.onInternetDisconnected()
            }

        }
    }

    fun addReciver(activity: BaseActivity) {
        receivers.put(activity, activity)

//        sending first event right after registering the client
        if (!internetConnection.isNetworkConnected()) {

//            sending event with delay so that snackbar animation works properly
            Handler().postDelayed(Runnable {
                //                checking if activity is still alive to avoid null pointer exception
                if (receivers.contains(activity))
                    activity.onInternetDisconnected()
            }, 400)
        }
    }

    fun removeReceiver(activity: BaseActivity) {
        receivers.remove(activity)
    }

}
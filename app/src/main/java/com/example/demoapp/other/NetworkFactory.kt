package com.example.demoapp.other

import android.content.Context
import android.net.ConnectivityManager

open class NetworkFactory(var context: Context) {

    fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

}
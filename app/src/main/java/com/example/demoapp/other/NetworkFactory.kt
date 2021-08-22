package com.example.demoapp.other

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.demoapp.models.UserResponse
import retrofit2.Response

open class NetworkFactory(var context: Context) {

    fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    fun showApiResult(result: String , raw:String) {
        Log.i("RESULT_API_RESPONSE",result)
        Log.i("RESULT_API_LINK",raw)
    }
}
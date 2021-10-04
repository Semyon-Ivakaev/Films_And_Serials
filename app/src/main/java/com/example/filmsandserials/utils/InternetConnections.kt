package com.example.filmsandserials.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

class InternetConnections(val context: Context) {

    private fun isInternetConnection(): Boolean? {
        Log.v("AppVerbose", "isInternetConnection")
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        Log.v("AppVerbose", "${networkInfo?.isConnected}")
        return networkInfo?.isConnected
    }

    fun checkInternetConnections(): Boolean {
        if (isInternetConnection() == null) {
            Log.v("AppVerbose", "isInternetConnection(context) == false")
            return true
        }
        return false
    }
}
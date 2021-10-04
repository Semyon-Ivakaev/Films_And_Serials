package com.example.filmsandserials.utils

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner

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
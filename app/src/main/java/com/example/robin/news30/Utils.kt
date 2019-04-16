package com.example.robin.news30

import android.content.Context
import android.net.ConnectivityManager

object Utils {

    fun hasNetwork(ctx: Context?): Boolean {
        val connectivityManager =
            ctx?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
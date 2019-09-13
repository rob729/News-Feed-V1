package com.example.robin.news30.utils

import android.content.Context
import android.net.ConnectivityManager

object Utils {

    fun hasNetwork(ctx: Context?): Boolean {
        val connectivityManager =
            ctx?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    val apiKey = "4663b6001744472eaac1f5aa16076a7a"
}
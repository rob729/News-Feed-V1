package com.robin.news30.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.robin.news30.R
import com.thefinestartist.finestwebview.FinestWebView

object Utils {

    val apiKey = "4663b6001744472eaac1f5aa16076a7a"
    val BASE_URL = "https://newsapi.org/v2/"

    lateinit var webViewBuilder: FinestWebView.Builder

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }

    fun initialiseWebView(context: Context) {
        if (!this::webViewBuilder.isInitialized) {
            webViewBuilder = FinestWebView.Builder(context)
                .showUrl(true)
                .showSwipeRefreshLayout(true)
                .webViewBuiltInZoomControls(true)
                .titleColorRes(R.color.white)
                .urlColorRes(R.color.white)
        }
    }

    fun setWebView(url: String) {
        return webViewBuilder.show(url)
    }

    fun getName(source: String): String {
        return when (source) {
            "the-verge" -> "Verge"
            "wired" -> "Wired"
            "techcrunch" -> "Techcrunch"
            "the-hindu" -> "The Hindu"
            "espn-cric-info" -> "ESPN"
            "reddit-r-all" -> "Reddit"
            "the-next-web" -> "The Next Web"
            "engadget" -> "Engadget"
            "new-scientist" -> "New Scientist"
            else -> "News"
        }
    }

    fun getImageUrlFromSourceName(source: String): String {
        return when (source) {
            "the-verge" -> "https://cdn.vox-cdn.com/uploads/chorus_asset/file/7395359/ios-icon.0.png"
            "wired" -> "https://www.wired.com/apple-touch-icon.png"
            "techcrunch" -> "https://pbs.twimg.com/profile_images/1096066608034918401/m8wnTWsX.png"
            "the-hindu" -> "https://icon-locator.herokuapp.com/lettericons/T-120-ffffff.png"
            "espn-cric-info" -> "https://images-na.ssl-images-amazon.com/images/I/21h-OE4-X7L._SY355_.png"
            "reddit-r-all" -> "https://www.redditstatic.com/mweb2x/favicon/120x120.png"
            "the-next-web" -> "https://cdn0.tnwcdn.com/wp-content/themes/cyberdelia/assets/icons/apple-touch-icon-120x120.png"
            "engadget" -> "https://s.blogsmithmedia.com/www.engadget.com/assets-h530cd5ea4f940095be1a3f313af013dc/images/apple-touch-icon-120x120.png?h=232a14b1a350de05a49b584a62abac9e"
            "new-scientist" -> "https://www.newscientist.com/wp-content/themes/new-scientist/img/layup/touch-icon/144x144.png"
            else -> ""
        }
    }

}
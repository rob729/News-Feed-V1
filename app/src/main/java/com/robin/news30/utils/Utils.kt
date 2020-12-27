package com.robin.news30.utils

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.robin.news30.R
import com.techyourchance.dagger2course.common.dependnecyinjection.app.AppScoped
import com.thefinestartist.finestwebview.FinestWebView
import javax.inject.Inject

@AppScoped
class Utils @Inject constructor(private val context: Context) {

    private val webViewBuilder: FinestWebView.Builder = FinestWebView.Builder(context)
        .showUrl(true)
        .showSwipeRefreshLayout(true)
        .webViewBuiltInZoomControls(true)
        .titleColorRes(R.color.white)
        .urlColorRes(R.color.white)


    fun isInternetAvailable(): Boolean {
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

    fun setWebView(url: String) {
        return webViewBuilder.show(url)
    }

    fun getName(domain: String): String {
        return when (domain) {
            getString(R.string.verge_domain) -> getString(R.string.verge)
            getString(R.string.wired_domain) -> getString(R.string.wired)
            getString(R.string.techcrunch_domain) -> getString(R.string.techcrunch)
            getString(R.string.the_hindu_domain) -> getString(R.string.the_hindu)
            getString(R.string.espn_domain) -> getString(R.string.espn)
            getString(R.string.reddit_domain) -> getString(R.string.reddit)
            getString(R.string.the_next_web_domain) -> getString(R.string.the_next_web)
            getString(R.string.engadget_domain) -> getString(R.string.engadget)
            getString(R.string.new_scientist_domain) -> getString(R.string.new_scientist)
            else -> "News"
        }
    }

    fun getString(id: Int): String{
        return context.getString(id)
    }

    fun getImageUrlFromDomainName(domain: String): String {
        return when (domain) {
            getString(R.string.verge_domain) -> "https://kahoot.com/files/2020/10/the-verge-logo.jpg"
            getString(R.string.wired_domain) -> "https://res-2.cloudinary.com/crunchbase-production/image/upload/c_lpad,f_auto,q_auto:eco/v1489030150/i1jbqbfetqzi8dr9nmvb.png"
            getString(R.string.techcrunch_domain) -> "https://pbs.twimg.com/profile_images/1096066608034918401/m8wnTWsX.png"
            getString(R.string.the_hindu_domain) -> "https://planetabled.com/wp-content/uploads/2019/07/The-Hindu-Logo.jpg"
            getString(R.string.espn_domain) -> "https://images-na.ssl-images-amazon.com/images/I/21h-OE4-X7L._SY355_.png"
            getString(R.string.reddit_domain) -> "https://www.redditstatic.com/mweb2x/favicon/120x120.png"
            getString(R.string.the_next_web_domain) -> "https://assets.stickpng.com/thumbs/5841a001a6515b1e0ad75a6e.png"
            getString(R.string.engadget_domain) -> "https://s.blogsmithmedia.com/www.engadget.com/assets-h530cd5ea4f940095be1a3f313af013dc/images/apple-touch-icon-120x120.png?h=232a14b1a350de05a49b584a62abac9e"
            getString(R.string.new_scientist_domain) -> "https://is5-ssl.mzstatic.com/image/thumb/Purple114/v4/f1/5a/51/f15a5132-65b6-6966-b50a-45be69857b33/AppIcon-0-0-1x_U007emarketing-0-0-0-7-0-0-sRGB-0-0-0-GLES2_U002c0-512MB-85-220-0-0.png/1200x630wa.png"
            else -> " "
        }
    }

}
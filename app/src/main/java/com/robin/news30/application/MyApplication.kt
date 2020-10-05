package com.robin.news30.application

import com.robin.news30.di.DaggerAppComponent
import com.robin.news30.utils.Utils
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class MyApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Utils.initialiseWebView(applicationContext)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }


}
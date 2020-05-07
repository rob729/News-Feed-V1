package com.example.robin.news30.di

import com.example.robin.news30.activity.MainActivity
import com.example.robin.news30.activity.SplashActivity
import com.example.robin.news30.di.main.MainFragmentBuildersModule
import com.example.robin.news30.di.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class, MainModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}
package com.robin.news30.di.main

import com.robin.news30.fragment.NewsListFragment
import com.robin.news30.fragment.NewsSourceFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributesNewsListFragment(): NewsListFragment

    @ContributesAndroidInjector()
    abstract fun contributesNewsSourceFragment(): NewsSourceFragment
}
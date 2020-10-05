package com.robin.news30.di.main

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.bumptech.glide.RequestManager
import com.robin.news30.adapter.NewsAdapter
import com.robin.news30.network.NewsApi
import com.robin.news30.utils.ViewModelProviderFactory
import com.robin.news30.viewmodel.NewsSourceViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun providesNewsApi(retrofit: Retrofit): NewsApi{
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    fun providesAdapter(requestManager: RequestManager): NewsAdapter {
        return NewsAdapter(requestManager)
    }

    @Provides
    fun providesNewsSourceViewModel(newsApi: NewsApi): NewsSourceViewModel = ViewModelProvider(
        ViewModelStore(), ViewModelProviderFactory(
            NewsSourceViewModel::class){
            NewsSourceViewModel(newsApi)
        }).get(NewsSourceViewModel::class.java)
}
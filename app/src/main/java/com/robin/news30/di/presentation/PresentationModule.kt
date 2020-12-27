package com.robin.news30.di.presentation

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.robin.news30.adapter.NewsAdapter
import com.robin.news30.network.NewsApi
import com.robin.news30.repository.NewsRepository
import com.robin.news30.repository.NewsRepositoryImpl
import com.robin.news30.utils.ImageLoader
import com.robin.news30.utils.PreferenceRepository
import com.robin.news30.utils.Utils
import com.robin.news30.utils.ViewModelProviderFactory
import com.robin.news30.viewmodel.NewsSourceViewModel
import com.techyourchance.dagger2course.common.dependnecyinjection.presentation.PresentationScoped
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @PresentationScoped
    @Provides
    fun providesNewsRepository(
        newsApi: NewsApi,
        preferenceRepository: PreferenceRepository,
        utils: Utils
    ): NewsRepository = NewsRepositoryImpl(newsApi, preferenceRepository, utils)

    @PresentationScoped
    @Provides
    fun providesNewsSourceViewModel(newsRepository: NewsRepository): NewsSourceViewModel =
        ViewModelProvider(
            ViewModelStore(), ViewModelProviderFactory(
                NewsSourceViewModel::class
            ) {
                NewsSourceViewModel(newsRepository)
            }).get(NewsSourceViewModel::class.java)


    @PresentationScoped
    @Provides
    fun providesAdapter(imageLoader: ImageLoader, utils: Utils): NewsAdapter {
        return NewsAdapter(imageLoader, utils)
    }
}
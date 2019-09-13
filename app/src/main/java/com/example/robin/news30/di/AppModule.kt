package com.example.robin.news30.di

import com.example.robin.news30.network.NewsApi
import com.example.robin.news30.viewmodel.NewsSourceViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object AppModule {

    val appModule = module {

        viewModel { NewsSourceViewModel(get()) }
        single { NewsApi() }
    }
}
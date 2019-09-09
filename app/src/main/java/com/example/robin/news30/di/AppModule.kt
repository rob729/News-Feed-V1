package com.example.robin.news30.di

import com.example.robin.news30.viewmodel.NewsSourceViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val appModule = module {

        viewModel { NewsSourceViewModel() }
    }
}
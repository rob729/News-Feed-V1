package com.example.robin.news30

import com.example.robin.news30.NewsSource.NewsSourceViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val appModule =  module{

        viewModel { NewsSourceViewModel() }
    }
}
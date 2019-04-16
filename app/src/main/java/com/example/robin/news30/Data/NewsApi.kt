package com.example.robin.news30.Data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object NewsApi {

    private val BASE_URL = "https://newsapi.org/v2/"
    private var retrofit: Retrofit? = null
    private var newsService: NewsService? = null
    val instance: NewsService?
        get() {
            if (newsService != null)
                return newsService
            if (retrofit == null)
                initalizeRetrofit()
            newsService = retrofit!!.create<NewsService>(
                NewsService::class.java
            )
            return newsService
        }

    private fun initalizeRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

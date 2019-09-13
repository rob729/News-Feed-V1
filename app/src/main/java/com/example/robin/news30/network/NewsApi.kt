package com.example.robin.news30.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApi {

    private val BASE_URL = "https://newsapi.org/v2/"

    fun initalizeRetrofit(): NewsService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(NewsService::class.java)
    }
}

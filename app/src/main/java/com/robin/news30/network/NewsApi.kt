package com.robin.news30.network

import com.robin.news30.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("domains") domain: String,
        @Query("apiKey") apiKey: String,
        @Query("language") language: String
    ): Response<News>
}

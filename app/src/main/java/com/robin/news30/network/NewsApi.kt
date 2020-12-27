package com.robin.news30.network

import com.robin.news30.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String
    ): Response<News>
}

package com.example.robin.news30.network

import com.example.robin.news30.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsApi {

    @GET
    fun getNews(@Url url: String): Call<News>
}

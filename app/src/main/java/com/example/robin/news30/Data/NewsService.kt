package com.example.robin.news30.Data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsService {
    @GET
    fun getNews(@Url url: String): Call<News>

    companion object {
       // val s = newsProvider.source
        val s = "the-verge"
    }
}

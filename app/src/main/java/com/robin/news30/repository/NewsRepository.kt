package com.robin.news30.repository

import com.robin.news30.model.News
import com.robin.news30.model.NewsResource

interface NewsRepository {
    suspend fun getNews(): NewsResource<News>
}
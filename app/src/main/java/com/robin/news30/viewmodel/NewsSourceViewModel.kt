package com.robin.news30.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robin.news30.model.News
import com.robin.news30.model.NewsResource
import com.robin.news30.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsSourceViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _news = MutableLiveData<NewsResource<News>>()

    val news: LiveData<NewsResource<News>>
        get() = _news

    init {
        fetchNews()
    }

    fun fetchNews() {
        _news.value = NewsResource.Loading()
        CoroutineScope(Dispatchers.IO).launch {
            _news.postValue(newsRepository.getNews())
        }
    }
}
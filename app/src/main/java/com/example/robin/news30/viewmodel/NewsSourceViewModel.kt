package com.example.robin.news30.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.robin.news30.model.News
import com.example.robin.news30.model.NewsResource
import com.example.robin.news30.network.NewsApi
import com.example.robin.news30.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsSourceViewModel(private val newsApi: NewsApi) : ViewModel() {

    private val _news = MutableLiveData<NewsResource<News>>()

    val news: LiveData<NewsResource<News>>
        get() = _news

    lateinit var source: String

    fun fetchRepos() {
        _news.value = NewsResource.Loading()

        CoroutineScope(Dispatchers.IO).launch {
            val request = newsApi.getNews("top-headlines?sources=$source&apiKey=${Utils.apiKey}")

            request.enqueue(object : Callback<News> {

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    if (response.isSuccessful) {
                        _news.value = NewsResource.Success(response.body()!!)
                    } else {
                        _news.value =
                            NewsResource.Error("Something went wrong ${response.message()}", null)
                    }
                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    _news.value = NewsResource.Error("Something went wrong ${t.message}", null)
                }
            })
        }

    }

}
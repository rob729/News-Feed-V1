package com.example.robin.news30.viewmodel

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.robin.news30.model.News
import com.example.robin.news30.network.NewsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsSourceViewModel : ViewModel() {

    private val _news = MutableLiveData<News>()

    val news: LiveData<News>
        get() = _news

    private val _loading = MutableLiveData<Boolean>()

    val loading: LiveData<Boolean>
        get() = _loading

    private val _newsLoadError = MutableLiveData<Boolean>()

    val newsLoadError: LiveData<Boolean>
        get() = _newsLoadError

    private var newscall: Call<News>? = null

    lateinit var source: String


    fun fetchRepos() {
        _loading.value = true
        newscall =
            NewsApi.instance?.getNews("top-headlines?sources=$source&apiKey=4663b6001744472eaac1f5aa16076a7a");
        newscall?.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, @NonNull response: Response<News>) {
                _newsLoadError.value = false
                Log.e("TAG", "OnResponse Success $source")
                _news.value = response.body()
                _loading.value = false
                newscall = null
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.e("TAG", "Error in loading ")
                _newsLoadError.value = true
                _loading.value = false
                newscall = null
            }
        })

    }

    override fun onCleared() {
        super.onCleared()
        if (newscall != null) {
            newscall!!.cancel()
            newscall = null
        }
    }
}
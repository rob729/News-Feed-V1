package com.example.robin.news30.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.robin.news30.model.News
import com.example.robin.news30.network.NewsApi
import com.example.robin.news30.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsSourceViewModel(news: NewsApi) : ViewModel() {

    val newsApi: NewsApi = news

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

        CoroutineScope(Dispatchers.IO).launch {
            val request = newsApi.initalizeRetrofit()
                .getNews("top-headlines?sources=$source&apiKey=${Utils.apiKey}")
            withContext(Dispatchers.IO) {
                try {

                    request.enqueue(object : Callback<News> {

                        override fun onResponse(call: Call<News>, response: Response<News>) {
                            _newsLoadError.value = false
                            _news.value = response.body()
                            _loading.value = false
                            Log.e("TAG", "${response.body()?.totalResults}")
                        }

                        override fun onFailure(call: Call<News>, t: Throwable) {
                            _newsLoadError.value = true
                            _loading.value = false
                            Log.e("TAG", "fail")
                        }


                    })
                } catch (e: Exception) {
                    Log.e(
                        "MainActicity",
                        "Exception ${e.message}"
                    )
                }
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        if (newscall != null) {
            newscall!!.cancel()
            newscall = null
        }
    }
}
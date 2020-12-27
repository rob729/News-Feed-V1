package com.robin.news30.repository

import android.content.Context
import android.util.Log
import com.robin.news30.model.News
import com.robin.news30.model.NewsResource
import com.robin.news30.network.NewsApi
import com.robin.news30.utils.PreferenceRepository
import com.robin.news30.utils.Utils
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val preferenceRepository: PreferenceRepository,
    private val context: Context
) : NewsRepository {

    override suspend fun getNews(): NewsResource<News> {
        if(!Utils.isInternetAvailable(context)){
            return NewsResource.Error("Something went wrong", null)
        }
        delay(50)
        val newsSource = preferenceRepository.getNewsSource().first()
        return getDataFromService(newsApi.getNews(newsSource, Utils.apiKey))
    }

    private val coroutineExceptionHanlder = CoroutineExceptionHandler { _, throwable ->
        NewsResource.Error("Something went wrong ${throwable.message}", null)
    }

    private suspend inline fun <reified T> getDataFromService(result: retrofit2.Response<T>): NewsResource<T> =
        withContext(Dispatchers.IO + coroutineExceptionHanlder) {
            return@withContext try {
                if (result.isSuccessful && result.body() != null) {
                    NewsResource.Success(result.body()!!)
                } else {
                    NewsResource.Error(
                        "Something went wrong ${result.message()}",
                        null
                    )
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                NewsResource.Error("Something went wrong ${exception.message}", null)
            }
        }
}
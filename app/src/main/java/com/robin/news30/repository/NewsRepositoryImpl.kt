package com.robin.news30.repository

import com.robin.news30.R
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

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val preferenceRepository: PreferenceRepository,
    private val utils: Utils
) : NewsRepository {

    override suspend fun getNews(): NewsResource<News> {
        if (!utils.isInternetAvailable()) {
            return NewsResource.Error("Something went wrong", null)
        }
        delay(50)
        val newsSource = preferenceRepository.getNewsSource().first()
        return getDataFromService(
            newsApi.getNews(
                newsSource,
                utils.getString(R.string.news_api_key),
                "en"
            )
        )
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
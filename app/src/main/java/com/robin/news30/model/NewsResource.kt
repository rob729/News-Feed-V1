package com.robin.news30.model

sealed class NewsResource<T>(
    val status: Status? = null,
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : NewsResource<T>(Status.SUCCESS, data)
    class Loading<T>(data: T? = null) : NewsResource<T>(Status.LOADING, data)
    class Error<T>(message: String, data: T? = null) : NewsResource<T>(Status.ERROR, data, message)
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

}
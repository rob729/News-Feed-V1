package com.robin.news30.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Articles(
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String,
    @Json(name = "urlToImage") val urlToImage: String?,
    @Json(name = "description") val description: String
)
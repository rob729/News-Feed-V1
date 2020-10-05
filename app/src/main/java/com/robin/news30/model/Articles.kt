package com.robin.news30.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Articles(
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String,
    @SerializedName("description") val description: String
)
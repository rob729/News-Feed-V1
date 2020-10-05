package com.robin.news30.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class News(@SerializedName("articles") val articles: List<Articles>)

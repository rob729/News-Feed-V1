package com.robin.news30.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
class News(@Json(name = "articles") val articles: List<Articles>)

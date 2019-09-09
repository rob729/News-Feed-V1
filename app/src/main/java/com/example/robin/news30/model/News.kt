package com.example.robin.news30.model

class News {

    var articles: List<Articles>
    lateinit var status: String
    var totalResults: Int? = null

    constructor(n: News) {
        this.articles = n.articles
    }


    constructor(articles: List<Articles>, status: String, totalResults: Int?) {
        this.articles = articles
        this.status = status
        this.totalResults = totalResults
    }
}

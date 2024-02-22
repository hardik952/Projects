package com.example.newsapi.Model

data class TopNewsResponse(
    var status:String,
    var totalResults:Int,
    var articles: MutableList<Article>
)

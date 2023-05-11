package com.newsapp.data.remote.response

data class BaseNewsData(
    val articles: List<NewsData>,
    val status: String,
    val totalResults: Int
)

package com.newsapp.network

import com.newsapp.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    fun getNewsTopHeadlines(
        @Query("country") country: String
    ): Call<NewsResponse>

    @GET("top-headlines")
    fun getArticlesByCategory(
        @Query("category") category: String,
        @Query("language") lang: String = "en"
    ): Call<NewsResponse>

    @GET("everything")
    fun getArticlesBySource(
        @Query("sources") source: String)
    : Call<NewsResponse>
}
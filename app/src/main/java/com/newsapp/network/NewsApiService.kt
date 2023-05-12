package com.newsapp.network

import com.newsapp.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    fun getNewsTopHeadlines(
        @Query("country") country: String
    ): Call<TopNewsResponse>

    @GET("top-headlines")
    fun getArticlesByCategory(
        @Query("category") category: String,
        @Query("language") lang: String = "en"
    ): Call<TopNewsResponse>

    @GET("everything")
    fun getArticlesBySource(
        @Query("sources") source: String)
    : Call<TopNewsResponse>
}
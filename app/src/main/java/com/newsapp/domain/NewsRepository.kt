package com.newsapp.domain

import com.newsapp.data.remote.response.NewsData
import com.newsapp.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getAllNewsData(category: String, country: String): Flow<ResultData<List<NewsData>>>
}
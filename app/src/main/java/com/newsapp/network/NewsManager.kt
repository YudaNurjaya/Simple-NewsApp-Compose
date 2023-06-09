package com.newsapp.network

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.newsapp.models.ArticleCategory
import com.newsapp.models.NewsResponse
import com.newsapp.models.getArticleCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsManager {
    private val _newsResponse = mutableStateOf(NewsResponse())
    val newsResponse: State<NewsResponse>
        @Composable get() = remember {
            _newsResponse
        }

    private val _responseCategories = mutableStateOf(NewsResponse())
    val responseCategories: MutableState<NewsResponse>
        @Composable get() = remember {
            _responseCategories
        }

    val selectedCategory: MutableState<ArticleCategory?> = mutableStateOf(null)

    val sourceName = mutableStateOf("abc-news")
    private val _responseSources = mutableStateOf(NewsResponse())
    val responseSources: MutableState<NewsResponse>
        @Composable get() = remember {
            _responseSources
        }

    init {
        getArticles()
    }

    private fun getArticles() {
        val service = ApiClient.retrofitService.getNewsTopHeadlines("us")
        service.enqueue(object : Callback<NewsResponse> {

            override fun onResponse(call: Call<NewsResponse>, response:
            Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    _newsResponse.value = response.body()!!
                    Log.i("NewsResponse: ", "${_newsResponse.value}")
                }
                else {
                    Log.e("Response Error:", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("GetArticles Error:", "${t.printStackTrace()}")
            }
        })
    }

    fun onSelectedCategory(category: String) {
        val newCategory = getArticleCategory(category = category)
        selectedCategory.value = newCategory
    }

    fun getArticlesByCategory(category: String) {
        val service = ApiClient.retrofitService.getArticlesByCategory(category)
        service.enqueue(object : Callback<NewsResponse> {

            override fun onResponse(call: Call<NewsResponse>, response:
            Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    _responseCategories.value = response.body()!!
                    Log.i("CategoryResponse: ", "${_responseCategories.value}")
                }
                else {
                    Log.e("Response Error:", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ArticlesCategory Error:", "${t.printStackTrace()}")
            }
        })
    }

    fun getArticlesBySources() {
        val service = ApiClient.retrofitService.getArticlesBySource(sourceName.value)
        service.enqueue(object : Callback<NewsResponse> {

            override fun onResponse(call: Call<NewsResponse>, response:
            Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    _responseSources.value = response.body()!!
                    Log.i("SourcesResponse: ", "${_responseSources.value}")
                }
                else {
                    Log.e("Response Error:", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ArtSources Error:", "${t.printStackTrace()}")
            }
        })
    }
}
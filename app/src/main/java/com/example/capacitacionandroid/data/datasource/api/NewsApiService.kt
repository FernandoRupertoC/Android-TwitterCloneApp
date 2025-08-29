package com.example.capacitacionandroid.data.datasource.api

import com.example.capacitacionandroid.data.datasource.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("1/latest")
    suspend fun getNews(
        @Query("apikey") apiKey: String,
        @Query("q") query: String? = null,
        @Query("country") country: String? = null,
        @Query("language") language: String? = null,
        @Query("prioritydomain") priorityDomain: String? = null
    ): NewsResponse
}


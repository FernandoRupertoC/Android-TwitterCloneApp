package com.example.capacitacionandroid.data.datasource.repository

import com.example.capacitacionandroid.data.datasource.di.RetrofitInstance
import com.example.capacitacionandroid.data.datasource.response.NewsResponse
import com.example.capacitacionandroid.data.datasource.response.NewsResult

class NewsRepositoryImpl {
    private val apiKey = "pub_9911ad0a14bf4fa6a877f151ea77f552"

    suspend fun getForYouNews(): List<NewsResult> {
        val response = RetrofitInstance.api.getNews(
            apiKey = apiKey,
            priorityDomain = "top",
            language = "es"
        )
        return response.results
    }
}

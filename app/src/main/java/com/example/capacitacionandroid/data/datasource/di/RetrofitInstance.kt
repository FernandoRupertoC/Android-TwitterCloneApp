package com.example.capacitacionandroid.data.datasource.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.capacitacionandroid.data.datasource.api.NewsApiService

object RetrofitInstance {
    private const val BASE_URL = "https://newsdata.io/api/"

    val api: NewsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }
}
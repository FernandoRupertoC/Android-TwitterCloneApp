package com.example.capacitacionandroid.data.datasource.di

import com.example.capacitacionandroid.data.datasource.api.ApiServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://randomuser.me/"

    fun getApiService(): ApiServices {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiServices::class.java)
    }

}
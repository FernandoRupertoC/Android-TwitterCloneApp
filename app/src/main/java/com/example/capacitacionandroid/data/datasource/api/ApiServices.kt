package com.example.capacitacionandroid.data.datasource.api

import com.example.capacitacionandroid.data.datasource.response.UserResponse
import retrofit2.http.GET

interface ApiServices {
    @GET("api/?results=16")
    suspend fun getUser(): UserResponse
}

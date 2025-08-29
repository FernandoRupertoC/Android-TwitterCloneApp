package com.example.capacitacionandroid.data.datasource.repository

import com.example.capacitacionandroid.data.datasource.api.ApiServices
import com.example.capacitacionandroid.data.datasource.response.User
import com.example.capacitacionandroid.domain.repositoris.UserRepository

class UserRepositoryImpl(private val api: ApiServices) : UserRepository {

    override suspend fun obtenerTodosLosUsuarios():List<User> {
        return api.getUser().results
    }
}
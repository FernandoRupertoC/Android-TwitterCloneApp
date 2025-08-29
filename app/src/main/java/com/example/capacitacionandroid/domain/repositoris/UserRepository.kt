package com.example.capacitacionandroid.domain.repositoris

import com.example.capacitacionandroid.data.datasource.response.User

interface UserRepository {
    suspend fun obtenerTodosLosUsuarios():List<User>
}
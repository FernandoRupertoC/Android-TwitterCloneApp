package com.example.capacitacionandroid.domain.useCase

import com.example.capacitacionandroid.domain.repositoris.UserRepository

class GetAllUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.obtenerTodosLosUsuarios()
}
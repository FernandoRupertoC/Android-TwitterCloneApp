package com.example.capacitacionandroid.ui.view.following

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.capacitacionandroid.data.datasource.di.ApiFactory
import com.example.capacitacionandroid.data.datasource.repository.UserRepositoryImpl
import com.example.capacitacionandroid.data.datasource.response.User
import com.example.capacitacionandroid.domain.useCase.GetAllUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object UserContainer {
    private val api = ApiFactory.getApiService()
    val repository = UserRepositoryImpl(api)
    val useCase = GetAllUsersUseCase(repository)
}


class FollowingViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        obtenerUsuarios()
    }

    private fun obtenerUsuarios() {
        viewModelScope.launch {
            try {
                val resultado = getAllUsersUseCase()
                _users.value = resultado
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    class UserViewModelFactory(
        private val useCase: GetAllUsersUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FollowingViewModel::class.java)) {
                return FollowingViewModel(useCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

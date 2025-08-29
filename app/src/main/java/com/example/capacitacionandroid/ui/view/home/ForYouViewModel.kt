package com.example.capacitacionandroid.ui.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capacitacionandroid.data.datasource.repository.NewsRepositoryImpl
import com.example.capacitacionandroid.data.datasource.response.NewsResponse
import com.example.capacitacionandroid.data.datasource.response.NewsResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForYouViewModel(private val repository: NewsRepositoryImpl = NewsRepositoryImpl()): ViewModel() {

    private val _news = MutableStateFlow<List<NewsResult>>(emptyList())
    val news: StateFlow<List<NewsResult>> = _news

    init {
        fetchForYouNews()
    }

    private fun fetchForYouNews() {
        viewModelScope.launch {
            try {
                _news.value = repository.getForYouNews()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
package com.example.newsinternet.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsinternet.data.network.dto.NewsResponse
import com.example.newsinternet.domain.NewsInteractor
import kotlinx.coroutines.launch

class NewsApiViewModel(private val interactor: NewsInteractor) : ViewModel() {

    private val _newsApi = MutableLiveData<NewsResponse>()
    val newsApi: LiveData<NewsResponse> get() =_newsApi

    init {
        loadNewsApi()
    }

    private fun loadNewsApi() {
        viewModelScope.launch {
            _newsApi.value = interactor.getNews()
        }
    }
}
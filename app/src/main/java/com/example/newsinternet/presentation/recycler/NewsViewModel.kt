package com.example.newsinternet.presentation.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsViewModel(private val interactor: NewsDataBaseInteractor) : ViewModel() {

    val newsDataBase: LiveData<List<News>> get() = _newsDataBase
    private val _newsDataBase = MutableLiveData<List<News>>()

    fun loadNewsDataBase() {
        viewModelScope.launch {
            _newsDataBase.value = interactor.getNewsDataBase()
        }
    }

    fun insertNewsDataBase(news: News) {
        viewModelScope.launch {
            interactor.insertNewsDataBse(news)
        }
    }

    fun deleteNewsDataBase(news: News) {
        viewModelScope.launch {
            interactor.deleteNewsDataBse(news)
        }
    }
}
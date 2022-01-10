package com.example.newsinternet.presentation.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsinternet.domain.News
import com.example.newsinternet.domain.NewsDataBaseInteractor
import kotlinx.coroutines.launch

class NewsDataBaseViewModel(private val interactor: NewsDataBaseInteractor) : ViewModel() {

    private val _newsDataBase = MutableLiveData<List<News>>()
    val newsDataBase: LiveData<List<News>> get() = _newsDataBase

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
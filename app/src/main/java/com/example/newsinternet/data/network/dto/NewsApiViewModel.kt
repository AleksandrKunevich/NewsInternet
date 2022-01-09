package com.example.newsinternet.data.network.dto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsinternet.domain.NewsFilter
import com.example.newsinternet.domain.NewsApiInteractor
import kotlinx.coroutines.launch

class NewsApiViewModel(private val interactor: NewsApiInteractor) : ViewModel() {

    val newsApi: LiveData<NewsResponse> get() = _newsApi
    private val _newsApi = MutableLiveData<NewsResponse>()

    fun loadNewsApi(filter: NewsFilter?) {
        viewModelScope.launch {
            _newsApi.value = interactor.getNewsApi(filter)
        }
    }

//    fun onSaveClick(position: Int){
//        val item = _newsApi.value?.articles?.get(position)
//        val list = _newsApi.value?.articles?.toMutableList()
//        list[position] = item.copy(source = )
//    }
}
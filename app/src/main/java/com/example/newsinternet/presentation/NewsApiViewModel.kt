package com.example.newsinternet.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsinternet.data.network.dto.NewsResponse
import com.example.newsinternet.domain.NewsFilter
import com.example.newsinternet.domain.NewsInteractor
import com.example.newsinternet.domain.NewsInteractorImp
import com.example.newsinternet.presentation.recycler.News
import kotlinx.coroutines.launch

class NewsApiViewModel(private val interactor: NewsInteractor) : ViewModel() {

    val newsApi: LiveData<NewsResponse> get() = _newsApi
    private val _newsApi = MutableLiveData<NewsResponse>()

    fun loadNewsApi(filter: NewsFilter?) {
        viewModelScope.launch {
            _newsApi.value = interactor.getNews(filter)
        }
    }

    fun insetNews(news: News) {
        viewModelScope.launch {
            interactor.insetNews(news)
        }
    }

    fun deleteNews(news: News) {
        viewModelScope.launch {
            interactor.deleteNews(news)
        }
    }

//    fun onSaveClick(position: Int){
//        val item = _newsApi.value?.articles?.get(position)
//        val list = _newsApi.value?.articles?.toMutableList()
//        list[position] = item.copy(source = )
//    }
}
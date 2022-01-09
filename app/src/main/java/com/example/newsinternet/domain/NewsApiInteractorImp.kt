package com.example.newsinternet.domain

import com.example.newsinternet.data.network.NewsApi
import com.example.newsinternet.data.network.dto.NewsResponse
import com.example.newsinternet.presentation.recycler.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsApiInteractorImp(private val newsApi: NewsApi) : NewsApiInteractor {

    companion object {
        private val defaultFilter = NewsFilter()
    }

    override suspend fun getNewsApi(filter: NewsFilter?): NewsResponse {
        return withContext(Dispatchers.IO) {
            newsApi.getEverything(
                query = filter?.queryFilter ?: defaultFilter.queryFilter,
                language = filter?.languageFilter ?: defaultFilter.languageFilter
            )
        }
    }
}
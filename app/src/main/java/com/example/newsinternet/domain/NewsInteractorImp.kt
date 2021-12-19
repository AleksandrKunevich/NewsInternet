package com.example.newsinternet.domain

import com.example.newsinternet.data.network.NewsApi
import com.example.newsinternet.data.network.dto.NewsResponse

class NewsInteractorImp(private val newsApi: NewsApi) : NewsInteractor {

    override suspend fun getNews(filter: NewsFilter?): NewsResponse {
        return newsApi.getEverything(
            query = filter?.queryFilter ?: "Sport",
            language = filter?.languageFilter ?: "en"
        )
    }
}
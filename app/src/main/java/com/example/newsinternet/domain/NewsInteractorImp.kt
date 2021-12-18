package com.example.newsinternet.domain

import com.example.newsinternet.data.network.NewsApi
import com.example.newsinternet.data.network.dto.NewsResponse

class NewsInteractorImp(private val newsApi: NewsApi): NewsInteractor {
    override suspend fun getNews(): NewsResponse {
        return newsApi.getEverything(
            query = "Sport",
            language = "en"
        )
    }
}
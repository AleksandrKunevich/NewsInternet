package com.example.newsinternet.domain

import com.example.newsinternet.data.network.dto.NewsResponse

interface NewsApiInteractor {

    suspend fun getNewsApi(filter: NewsFilter?): NewsResponse
}
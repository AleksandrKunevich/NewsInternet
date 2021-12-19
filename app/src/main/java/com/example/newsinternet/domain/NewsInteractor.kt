package com.example.newsinternet.domain

import com.example.newsinternet.data.network.dto.NewsResponse

interface NewsInteractor {
    suspend fun getNews(filter: NewsFilter?): NewsResponse
}
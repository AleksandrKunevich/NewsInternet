package com.example.newsinternet.domain

import com.example.newsinternet.data.network.dto.NewsResponse
import com.example.newsinternet.presentation.recycler.News

interface NewsInteractor {
    suspend fun getNews(filter: NewsFilter?): NewsResponse

    suspend fun insetNews(news: News)

    suspend fun deleteNews(news: News)
}
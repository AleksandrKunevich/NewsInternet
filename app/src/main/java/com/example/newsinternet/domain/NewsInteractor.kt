package com.example.newsinternet.domain

import com.example.newsinternet.domain.News

interface NewsInteractor {

    suspend fun getNewsDataBase(): List<News>

    suspend fun deleteNewsDataBse(news: News)

    suspend fun insertNewsDataBse(news: News)
}
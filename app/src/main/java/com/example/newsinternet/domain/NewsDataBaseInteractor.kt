package com.example.newsinternet.domain

interface NewsDataBaseInteractor {

    suspend fun getNewsDataBase(): List<News>

    suspend fun deleteNewsDataBse(news: News)

    suspend fun insertNewsDataBse(news: News)
}
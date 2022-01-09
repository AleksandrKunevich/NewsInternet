package com.example.newsinternet.presentation.recycler

interface NewsDataBaseInteractor {

    suspend fun getNewsDataBase(): List<News>

    suspend fun deleteNewsDataBse(news: News)

    suspend fun insertNewsDataBse(news: News)
}
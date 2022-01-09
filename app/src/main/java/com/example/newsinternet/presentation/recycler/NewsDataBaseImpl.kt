package com.example.newsinternet.presentation.recycler

import com.example.newsinternet.data.storage.NewsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsDataBaseImpl(private val newsDao: NewsDao) : NewsDataBaseInteractor{

    override suspend fun getNewsDataBase(): List<News> {
        return withContext(Dispatchers.IO){
            newsDao.getAll().map { newsEntity ->
                newsEntity.toNews()
            }
        }
    }

    override suspend fun deleteNewsDataBse(news: News) {
    }

    override suspend fun insertNewsDataBse(news: News) {
    }
}
package com.example.newsinternet.data.storage

import com.example.newsinternet.domain.News
import com.example.newsinternet.domain.NewsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsInteractorImpl(private val newsDao: NewsDao) : NewsInteractor {

    override suspend fun getNewsDataBase(): List<News> {
        return withContext(Dispatchers.IO) {
            newsDao.getAll().map { newsEntity ->
                newsEntity.toNews()
            }
        }
    }

    override suspend fun deleteNewsDataBse(news: News) {
        newsDao.delete(news.toNewsEntity())
    }

    override suspend fun insertNewsDataBse(news: News) {
        newsDao.insert(news.toNewsEntity())
    }
}
package com.example.newsinternet.data.storage

import com.example.newsinternet.domain.News
import com.example.newsinternet.domain.NewsDataBaseInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsDataBaseInteractorImpl(private val newsDao: NewsDao) : NewsDataBaseInteractor {

    override suspend fun getNewsDataBase(): List<News> {
        return withContext(Dispatchers.IO) {
            newsDao.getAll().map { newsEntity ->
                newsEntity.toNews()
            }
        }
    }

    override suspend fun deleteNewsDataBse(news: News) {
        return withContext(Dispatchers.IO) {
            newsDao.getAll().forEach { newsEntity ->
                if (newsEntity.title == news.title)
                    newsDao.delete(newsEntity)
            }
        }
    }

    override suspend fun insertNewsDataBse(news: News) {
        return withContext(Dispatchers.IO) {
            var isHaveNews = false
            newsDao.getAll().forEach { newsEntity ->
                if (newsEntity.title == news.title) {isHaveNews = true}
            }
            if (!isHaveNews) {newsDao.insert(news.toNewsEntity())}
        }
    }
}
package com.example.newsinternet.data.storage

import com.example.newsinternet.data.network.dto.Article
import com.example.newsinternet.data.network.dto.NewsResponse
import com.example.newsinternet.domain.News

fun NewsEntity.toNews() = News(
    imageUrl = imageUrl,
    urlResource = urlResource,
    title = title,
    isSaved = isSaved
)

fun News.toNewsEntity() = NewsEntity(
    imageUrl = imageUrl,
    urlResource = urlResource,
    title = title,
    isSaved = isSaved
)

fun NewsResponse.toNews(position: Int) = News(
    imageUrl = articles[position].urlImage!!,
    urlResource = articles[position].url!!,
    title = articles[position].title!!,
    isSaved = false
)

fun Article.toNews() = News(
    imageUrl = urlImage!!,
    urlResource = url!!,
    title = title!!,
    isSaved = false
)
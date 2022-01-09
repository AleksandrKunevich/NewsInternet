package com.example.newsinternet.presentation.recycler

import com.example.newsinternet.data.network.dto.Article
import com.example.newsinternet.data.network.dto.NewsResponse
import com.example.newsinternet.data.storage.NewsEntity

fun NewsEntity.toNews() = News(
    imageUrl = imageUrl,
    urlResource = urlResource,
    title = title
)

fun NewsResponse.toNews(position: Int) = News(
    imageUrl = articles[position].urlImage!!,
    urlResource = articles[position].url!!,
    title = articles[position].title!!
)

fun Article.toNews() = News(
    imageUrl = urlImage!!,
    urlResource = url!!,
    title = title!!
)
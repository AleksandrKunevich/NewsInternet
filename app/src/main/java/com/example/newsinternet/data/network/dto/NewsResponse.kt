package com.example.newsinternet.data.network.dto

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    val status: String?,
    @SerializedName("totalResults")
    val total: Int?,
    val articles: List<Article>
)

data class Article(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    @SerializedName("urlToImage")
    val urlImage: String?,
    @SerializedName("publishedAt")
    val date: String?,
    val content: String?
)

data class Source(
    val id: String?,
    val name: String?
)
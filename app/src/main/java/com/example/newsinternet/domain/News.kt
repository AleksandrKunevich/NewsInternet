package com.example.newsinternet.domain

data class News(
    val imageUrl: String,
    val urlResource: String,
    val title: String,
    var isSaved: Boolean
)
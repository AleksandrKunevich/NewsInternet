package com.example.newsinternet.presentation.recycler

data class News(
    val imageUrl: String,
    val urlResource: String,
    val title: String,
    var isSaved: Boolean = false
)
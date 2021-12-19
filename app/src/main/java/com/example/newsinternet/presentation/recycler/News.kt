package com.example.newsinternet.presentation.recycler

data class News(
    val imageUrl: String? = null,
    val title: String,
    val isSaved: Boolean = false
)

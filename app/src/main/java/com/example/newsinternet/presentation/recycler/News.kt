package com.example.newsinternet.presentation.recycler

data class News(
    val imageUrl: String = "@drawable/ic_baseline_error_84",
    val urlResource: String = "@drawable/ic_baseline_error_84",
    val title: String = "Error load title",
    var isSaved: Boolean = false,
    val date: String = "Error load Date",
    val content: String = "Error load content",
    val author: String = "Error load author"
)

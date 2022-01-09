package com.example.newsinternet.domain

interface OnNewsApiClickListener {
    fun onImageSaveItemNewsClickListener(adapterPosition: Int)
    fun onTitleNewsContainerClickListener(news: News)
    fun onImageNewsContainerClickListener(imageUrl: String)
}
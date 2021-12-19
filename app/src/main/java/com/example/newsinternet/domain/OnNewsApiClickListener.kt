package com.example.newsinternet.domain

import android.widget.ImageView
import com.example.newsinternet.presentation.recycler.News

interface OnNewsApiClickListener {
    fun onImageSaveItemNewsClickListener(adapterPosition: Int)
    fun onTitleNewsContainerClickListener(news: News)
    fun onImageNewsContainerClickListener(imageUrl: String)
}
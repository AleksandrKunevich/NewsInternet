package com.example.newsinternet.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsinternet.R

class NewsHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun from(parentViewGroup: ViewGroup): NewsHolder {
            return NewsHolder(
                LayoutInflater
                    .from(parentViewGroup.context)
                    .inflate(R.layout.item_news, parentViewGroup, false)
            )
        }
    }

    private val image: ImageView by lazy { itemView.findViewById(R.id.imageItemNews) }
    private val title: TextView by lazy { itemView.findViewById(R.id.textViewItemNews) }

    fun bindView(item: News) {
        item.imageUrl?.let { loadImageUrl(it) }
        title.text = item.title
    }

    private fun loadImageUrl(url: String) {
        Glide.with(image)
            .load(url)
            .into(image)
    }
}
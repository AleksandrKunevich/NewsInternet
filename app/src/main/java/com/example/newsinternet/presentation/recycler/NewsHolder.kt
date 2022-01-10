package com.example.newsinternet.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsinternet.R
import com.example.newsinternet.domain.News
import com.example.newsinternet.domain.OnNewsApiClickListener

class NewsHolder private constructor(
    itemView: View,
    private val clickListener: OnNewsApiClickListener
) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun from(parentViewGroup: ViewGroup, clickListener: OnNewsApiClickListener): NewsHolder {
            return NewsHolder(
                LayoutInflater
                    .from(parentViewGroup.context)
                    .inflate(R.layout.item_news, parentViewGroup, false),
                clickListener
            )
        }
    }

    private val image: ImageView by lazy { itemView.findViewById(R.id.imageItemNews) }
    private val title: TextView by lazy { itemView.findViewById(R.id.textViewItemNews) }
    private val isCheckedIcon: CheckBox by lazy { itemView.findViewById(R.id.isSaveNewsCheckBox) }

    fun bindView(item: News) {
        loadImageUrl(item.imageUrl)
        title.text = item.title
        isCheckedIcon.isChecked = item.isSaved

        isCheckedIcon.setOnClickListener {
            clickListener.onImageSaveItemNewsClickListener(adapterPosition)
        }

        title.setOnClickListener {
            clickListener.onTitleNewsContainerClickListener(item)
        }

        image.setOnClickListener {
            clickListener.onImageNewsContainerClickListener(item.imageUrl)
        }
    }

    private fun loadImageUrl(url: String) {
        Glide.with(image)
            .load(url)
            .into(image)
    }
}
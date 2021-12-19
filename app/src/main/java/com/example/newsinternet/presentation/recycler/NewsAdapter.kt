package com.example.newsinternet.presentation.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsinternet.data.network.dto.NewsResponse
import com.example.newsinternet.domain.OnNewsApiClickListener

class NewsAdapter(private val clickListener: OnNewsApiClickListener) :
    RecyclerView.Adapter<NewsHolder>() {

    private var items: List<News> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder.from(parent, clickListener)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(data: List<News>) {
        items = data
        notifyDataSetChanged()
    }
}
package com.example.newsinternet.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsinternet.databinding.NewsListApiBinding
import com.example.newsinternet.domain.OnNewsApiClickListener
import com.example.newsinternet.presentation.recycler.News
import com.example.newsinternet.presentation.recycler.NewsAdapter

class NewsFragment(private val news: List<News>) : Fragment() {

    companion object {
        const val TAG = "NewsFragment(news)"

        fun newInstance(news: List<News>) = NewsFragment(news)
    }

    private lateinit var binding: NewsListApiBinding
    private val adapterNews by lazy { NewsAdapter(newsApiClickListener) }

    private val newsApiClickListener: OnNewsApiClickListener = object: OnNewsApiClickListener{
        override fun onImageCheckItemNewsClickListener(adapterPosition: Int) {
            news[adapterPosition].isSaved = !news[adapterPosition].isSaved
        }

        override fun onItemNewsContainerClickListener() {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsListApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.apply {
            recyclerNewsApi.adapter = adapterNews
            recyclerNewsApi.layoutManager = LinearLayoutManager(activity)
            adapterNews.submitList(news)
        }
    }
}
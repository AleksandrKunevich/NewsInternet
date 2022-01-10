package com.example.newsinternet.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.newsinternet.R
import com.example.newsinternet.data.network.dto.NewsApiViewModel
import com.example.newsinternet.data.storage.toNews
import com.example.newsinternet.databinding.NewsListApiBinding
import com.example.newsinternet.domain.News
import com.example.newsinternet.domain.NewsFilter
import com.example.newsinternet.domain.OnNewsApiClickListener
import com.example.newsinternet.presentation.recycler.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {

    private var newsList: List<News> = emptyList()
    private lateinit var binding: NewsListApiBinding
    private val adapterNews by lazy { NewsAdapter(newsApiClickListener) }
    private val newsApiViewModel by viewModel<NewsApiViewModel>()
    private val newsViewModel by viewModel<NewsDataBaseViewModel>()

    private val newsApiClickListener: OnNewsApiClickListener = object : OnNewsApiClickListener {
        override fun onImageSaveItemNewsClickListener(adapterPosition: Int) {
            newsList[adapterPosition].apply {
                isSaved = !isSaved
                if (isSaved) {
                    newsViewModel.insertNewsDataBase(this)
                } else {
                    newsViewModel.deleteNewsDataBase(this)
                }
            }
        }

        override fun onTitleNewsContainerClickListener(news: News) {
            openOneNewsFragment(news)
        }

        override fun onImageNewsContainerClickListener(imageUrl: String) {
            binding.recyclerNewsApi.visibility = View.INVISIBLE
            binding.imageNews.visibility = View.VISIBLE
            Glide.with(requireContext()).load(imageUrl).into(binding.imageNews)
            binding.imageNews.setOnClickListener {
                binding.recyclerNewsApi.visibility = View.VISIBLE
                binding.imageNews.visibility = View.INVISIBLE
            }
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

        newsApiViewModel.loadNewsApi(null)
        newsViewModel.loadNewsDataBase()
        initRecycler()

        binding.btnSavedNews.setOnClickListener {
            findNavController().navigate(R.id.action_newsFragment_to_savedNewsFragment)
        }

        binding.btnReset.setOnClickListener {
        }

        binding.btnFilter.setOnClickListener {
            newsApiViewModel.loadNewsApi(NewsFilter("Здоровье", "ru"))
        }

        newsApiViewModel.newsApi.observe(this) { newsResponse ->
            newsList = newsResponse.articles.map {
                it.toNews()
            }
            adapterNews.submitList(newsList)
        }
    }

    private fun openOneNewsFragment(news: News) {
        val address = Uri.parse(news.urlResource)
        val openIntent = Intent(Intent.ACTION_VIEW, address)
        startActivity(openIntent);
    }

    private fun initRecycler() {
        binding.apply {
            recyclerNewsApi.adapter = adapterNews
            recyclerNewsApi.layoutManager = LinearLayoutManager(activity)
        }
    }
}
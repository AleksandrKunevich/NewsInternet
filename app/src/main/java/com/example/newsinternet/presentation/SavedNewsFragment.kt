package com.example.newsinternet.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsinternet.R
import com.example.newsinternet.databinding.SavedNewsBinding
import com.example.newsinternet.domain.News
import com.example.newsinternet.domain.OnNewsApiClickListener
import com.example.newsinternet.presentation.recycler.NewsAdapter
import com.example.newsinternet.presentation.recycler.NewsDataBaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedNewsFragment : Fragment() {

    private lateinit var binding: SavedNewsBinding
    private val adapterNews by lazy { NewsAdapter(newsApiClickListener) }
    private val newsViewModel by viewModel<NewsDataBaseViewModel>()

    private val newsApiClickListener: OnNewsApiClickListener = object : OnNewsApiClickListener {
        override fun onImageSaveItemNewsClickListener(adapterPosition: Int) {
            newsViewModel.newsDataBase.value!![adapterPosition].apply {
                isSaved = !isSaved
                if (isSaved) {
                    newsViewModel.insertNewsDataBase(this)
                } else {
                    newsViewModel.deleteNewsDataBase(this)
                }
            }
        }

        override fun onTitleNewsContainerClickListener(news: News) {
            val address = Uri.parse(news.urlResource)
            val openIntent = Intent(Intent.ACTION_VIEW, address)
            startActivity(openIntent);
        }

        override fun onImageNewsContainerClickListener(imageUrl: String) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        newsViewModel.loadNewsDataBase()
        initRecycler()
        initObserve()
    }

    private fun initObserve() {
        newsViewModel.newsDataBase.observe(this) { newsList ->
            newsList.forEach {
                it.isSaved
            }
            adapterNews.submitList(newsList)
        }
    }

    private fun initRecycler() {
        binding.apply {
            recyclerNewsSaved.adapter = adapterNews
            recyclerNewsSaved.layoutManager = LinearLayoutManager(activity)
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_savedNewsFragment_to_newsFragment)
        }
    }
}
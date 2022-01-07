package com.example.newsinternet.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsinternet.R
import com.example.newsinternet.databinding.SavedNewsBinding
import com.example.newsinternet.domain.OnNewsApiClickListener
import com.example.newsinternet.presentation.recycler.News
import com.example.newsinternet.presentation.recycler.NewsAdapter

class SavedNewsFragment : Fragment() {

    private lateinit var binding: SavedNewsBinding
    private val savedNews: List<News> = mutableListOf()
    private val adapterNews by lazy { NewsAdapter(newsApiClickListener) }

    private val newsApiClickListener: OnNewsApiClickListener = object : OnNewsApiClickListener {
        override fun onImageSaveItemNewsClickListener(adapterPosition: Int) {
        }

        override fun onTitleNewsContainerClickListener(news: News) {
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
        initRecycler()
    }

    private fun initRecycler() {
        binding.apply {
            recyclerNewsSaved.adapter = adapterNews
            recyclerNewsSaved.layoutManager = LinearLayoutManager(activity)
            adapterNews.submitList(savedNews)
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_savedNewsFragment_to_newsFragment)
        }
    }
}
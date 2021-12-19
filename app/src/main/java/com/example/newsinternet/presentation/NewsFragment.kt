package com.example.newsinternet.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.newsinternet.databinding.ActivityMainBinding
import com.example.newsinternet.databinding.NewsListApiBinding
import com.example.newsinternet.domain.OnNewsApiClickListener
import com.example.newsinternet.presentation.recycler.News
import com.example.newsinternet.presentation.recycler.NewsAdapter

class NewsFragment(private val newsList: List<News>) : Fragment() {

    companion object {
        const val TAG = "NewsFragment(news)"

        fun newInstance(news: List<News>) = NewsFragment(news)
    }

    private lateinit var binding: NewsListApiBinding
    private lateinit var bindingActivity: ActivityMainBinding
    private val adapterNews by lazy { NewsAdapter(newsApiClickListener) }

    private val newsApiClickListener: OnNewsApiClickListener = object : OnNewsApiClickListener {
        override fun onImageCheckItemNewsClickListener(adapterPosition: Int) {
            newsList[adapterPosition].isSaved = !newsList[adapterPosition].isSaved
        }

        override fun onTitleNewsContainerClickListener(news: News) {
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(OneNewsFragment.TAG)
                .add(
                    bindingActivity.container.id,
                    OneNewsFragment.newInstance(news),
                    OneNewsFragment.TAG
                )
                .remove(this@NewsFragment)
                .commit()
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
        bindingActivity = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.apply {
            recyclerNewsApi.adapter = adapterNews
            recyclerNewsApi.layoutManager = LinearLayoutManager(activity)
            adapterNews.submitList(newsList)
        }
    }
}
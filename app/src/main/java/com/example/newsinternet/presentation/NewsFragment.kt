package com.example.newsinternet.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.newsinternet.data.storage.AppDataBase
import com.example.newsinternet.data.storage.NewsDao
import com.example.newsinternet.databinding.ActivityMainBinding
import com.example.newsinternet.databinding.NewsListApiBinding
import com.example.newsinternet.domain.NewsFilter
import com.example.newsinternet.domain.OnNewsApiClickListener
import com.example.newsinternet.presentation.recycler.News
import com.example.newsinternet.presentation.recycler.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment(private var newsList: List<News>) : Fragment() {

    companion object {
        const val TAG = "NewsFragment(news)"

        fun newInstance(news: List<News>) = NewsFragment(news)
    }

    private lateinit var binding: NewsListApiBinding
    private lateinit var bindingActivity: ActivityMainBinding
    private val adapterNews by lazy { NewsAdapter(newsApiClickListener) }
    private var newsDao: List<News> = mutableListOf()
    private lateinit var db: NewsDao
    private val newsApiViewModel: NewsApiViewModel by viewModel()

    private val newsApiClickListener: OnNewsApiClickListener = object : OnNewsApiClickListener {
        override fun onImageSaveItemNewsClickListener(adapterPosition: Int) {
            newsList[adapterPosition].apply {
                isSaved = !isSaved
                if (isSaved) {
                    db.inset(this)
                } else {
                    db.delete(this)
                }
                newsDao = db.getAll()
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
        bindingActivity = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        initRecycler()
        initDao()

        binding.btnSavedNews.setOnClickListener {
            newsDao = db.getAll()
            openSavedNewsFragment(newsDao)
        }

        binding.btnReset.setOnClickListener {
            newsDao.forEach { news ->
                db.delete(news)
            }
            newsDao = db.getAll()
        }

        binding.btnFilter.setOnClickListener {
            newsApiViewModel.loadNewsApi(NewsFilter("Здоровье", "ru"))
        }

        newsApiViewModel.newsApi.observe(this) { news ->
            newsList  = MainActivity().convertNewsResponseToNews(news, newsDao)
            adapterNews.submitList(newsList)
        }
    }

    private fun openOneNewsFragment(news: News) {
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

    private fun openSavedNewsFragment(newsDao: List<News>) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(SavedNewsFragment.TAG)
            .add(
                bindingActivity.container.id,
                SavedNewsFragment.newInstance(newsDao),
                SavedNewsFragment.TAG
            )
            .remove(this@NewsFragment)
            .commit()
    }

    private fun initRecycler() {
        binding.apply {
            recyclerNewsApi.adapter = adapterNews
            recyclerNewsApi.layoutManager = LinearLayoutManager(activity)
            adapterNews.submitList(newsList)
        }
    }

    private fun initDao() {
        db = Room
            .databaseBuilder(requireContext(), AppDataBase::class.java, "DAO saved News")
            .allowMainThreadQueries()
            .build()
            .newsDao()
        newsDao = db.getAll()
    }
}
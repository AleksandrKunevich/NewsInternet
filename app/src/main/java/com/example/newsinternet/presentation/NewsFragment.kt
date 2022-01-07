package com.example.newsinternet.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.newsinternet.R
import com.example.newsinternet.data.network.dto.NewsResponse
import com.example.newsinternet.data.storage.AppDataBase
import com.example.newsinternet.data.storage.NewsDao
import com.example.newsinternet.databinding.NewsListApiBinding
import com.example.newsinternet.domain.NewsFilter
import com.example.newsinternet.domain.OnNewsApiClickListener
import com.example.newsinternet.presentation.recycler.News
import com.example.newsinternet.presentation.recycler.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {

    private var newsList: List<News> = emptyList()
    private lateinit var binding: NewsListApiBinding
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
                    newsDao.forEach {
                        if (it.title == this.title) {
                            db.delete(it)
                        }
                    }
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
        initRecycler()
        initDao()

        binding.btnSavedNews.setOnClickListener {
            findNavController().navigate(R.id.action_newsFragment_to_savedNewsFragment)
        }

        binding.btnReset.setOnClickListener {
            newsDao.forEach { news ->
                db.delete(news)
            }
        }

        binding.btnFilter.setOnClickListener {
            newsApiViewModel.loadNewsApi(NewsFilter("Здоровье", "ru"))
        }

        newsApiViewModel.newsApi.observe(this) { news ->
            newsList = convertNewsResponseToNews(news, newsDao)
            adapterNews.submitList(newsList)
        }
    }

    private fun openOneNewsFragment(news: News) {

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

    private fun convertNewsResponseToNews(
        newsResponse: NewsResponse,
        newsDao: List<News>?
    ): List<News> {
        val news: MutableList<News> = mutableListOf()
        newsResponse.articles.forEach {
            val randomId = (0..Int.MAX_VALUE).random()
            var checked = false
            newsDao?.let { listNews ->
                listNews.forEach { news ->
                    if (news.title == it.title) {
                        checked = news.isSaved
                    }
                }
            }
            news.add(
                News(
                    uid = randomId,
                    imageUrl = it.urlImage ?: News(randomId).imageUrl,
                    urlResource = it.url ?: News(randomId).urlResource,
                    title = it.title ?: News(randomId).title,
                    date = it.date ?: News(randomId).date,
                    content = it.content ?: News(randomId).content,
                    author = it.author ?: News(randomId).author,
                    isSaved = checked
                )
            )
        }
        return news.toList()
    }
}
package com.example.newsinternet.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.newsinternet.R
import com.example.newsinternet.data.network.dto.NewsResponse
import com.example.newsinternet.data.storage.AppDataBase
import com.example.newsinternet.data.storage.NewsDao
import com.example.newsinternet.databinding.ActivityMainBinding
import com.example.newsinternet.presentation.recycler.News
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val newsApiViewModel: NewsApiViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: NewsDao
    private var newsDao: List<News> = mutableListOf()

    override fun onStart() {
        super.onStart()
        initDao()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsApiViewModel.loadNewsApi(null)

        newsApiViewModel.newsApi.observe(this) { news ->
            openFragment(convertNewsResponseToNews(news, newsDao))
        }
    }

    private fun openFragment(news: List<News>) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(NewsFragment.TAG)
            .add(binding.container.id, NewsFragment.newInstance(news), NewsFragment.TAG)
            .commit()
    }

    fun convertNewsResponseToNews(newsResponse: NewsResponse, newsDao: List<News>?): List<News> {
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

    private fun initDao() {
        db = Room
            .databaseBuilder(this, AppDataBase::class.java, "DAO saved News")
            .allowMainThreadQueries()
            .build()
            .newsDao()
        newsDao = db.getAll()
    }
}
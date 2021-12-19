package com.example.newsinternet.presentation

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsinternet.R
import com.example.newsinternet.data.network.dto.NewsResponse
import com.example.newsinternet.databinding.ActivityMainBinding
import com.example.newsinternet.presentation.recycler.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val newsApiViewModel: NewsApiViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onStart() {
        super.onStart()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsApiViewModel.newsApi.observe(this) { news ->
//            Toast.makeText(this, news.articles.size.toString(), Toast.LENGTH_LONG).show()

//            binding.textView.setOnClickListener {
                openFragment(convertNewsResponseToNews(news))
//            }

//            CoroutineScope(Dispatchers.Main).launch {
//                news.articles.forEach { article ->
//                    binding.textView.text = article.title
//                    delay(1000L)
//                }
//            }
        }
    }

    private fun openFragment(news: List<News>) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(NewsFragment.TAG)
            .add(binding.container.id, NewsFragment.newInstance(news), NewsFragment.TAG)
            .commit()
    }

    private fun convertNewsResponseToNews(newsResponse: NewsResponse): List<News> {
        val news: MutableList<News> = mutableListOf()
        newsResponse.articles.forEach {
            news.add(
                News(
                    imageUrl = it.urlImage ?: News().imageUrl,
                    urlResource = it.url ?: News().urlResource,
                    title = it.title ?: News().title,
                    date = it.date ?: News().date,
                    content = it.content ?: News().content,
                    author = it.author ?: News().author
                )
            )
        }
        return news.toList()
    }
}
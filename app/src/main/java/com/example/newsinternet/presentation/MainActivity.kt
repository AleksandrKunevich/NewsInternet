package com.example.newsinternet.presentation

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsinternet.R
import com.example.newsinternet.databinding.ActivityMainBinding
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
            Toast.makeText(this, news.articles.size.toString(), Toast.LENGTH_LONG).show()
            CoroutineScope(Dispatchers.Main).launch {
                news.articles.forEach { article ->
                    binding.textView.text = article.title
                    delay(2000L)
                }
            }
        }
    }
}
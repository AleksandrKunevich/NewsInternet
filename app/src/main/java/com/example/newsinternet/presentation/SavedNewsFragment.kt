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
import androidx.room.Room
import com.example.newsinternet.R
import com.example.newsinternet.data.storage.AppDataBase
import com.example.newsinternet.data.storage.NewsDao
import com.example.newsinternet.databinding.SavedNewsBinding
import com.example.newsinternet.domain.OnNewsApiClickListener
import com.example.newsinternet.presentation.recycler.News
import com.example.newsinternet.presentation.recycler.NewsAdapter

class SavedNewsFragment : Fragment() {

    private lateinit var binding: SavedNewsBinding
    private var savedNews: List<News> = mutableListOf()
    private val adapterNews by lazy { NewsAdapter(newsApiClickListener) }
    private lateinit var db: NewsDao

    private val newsApiClickListener: OnNewsApiClickListener = object : OnNewsApiClickListener {
        override fun onImageSaveItemNewsClickListener(adapterPosition: Int) {
            savedNews[adapterPosition].apply {
                isSaved = !isSaved
                if (isSaved) {
                    db.inset(this)
                } else {
                    savedNews.forEach {
                        if (it.title == this.title) {
                            db.delete(it)
                        }
                    }
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

        initDao()
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

    private fun initDao() {
        db = Room
            .databaseBuilder(requireContext(), AppDataBase::class.java, "DAO saved News")
            .allowMainThreadQueries()
            .build()
            .newsDao()
        savedNews = db.getAll()
    }
}
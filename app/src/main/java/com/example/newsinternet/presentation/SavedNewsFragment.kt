package com.example.newsinternet.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.newsinternet.databinding.SavedNewsBinding
import com.example.newsinternet.presentation.recycler.News

class SavedNewsFragment(private val newsDao: List<News>?) : Fragment() {

    companion object {
        const val TAG = "SavedNewsFragment(news)"

        fun newInstance(newsDao: List<News>) = SavedNewsFragment(newsDao)
    }

    private lateinit var binding: SavedNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val textView: TextView by lazy { binding.textViewSavedNews }

    override fun onStart() {
        super.onStart()

        textView.text = if (newsDao.isNullOrEmpty()) {
            "No saved news"
        } else {
            newsDao.first().urlResource
        }
    }
}
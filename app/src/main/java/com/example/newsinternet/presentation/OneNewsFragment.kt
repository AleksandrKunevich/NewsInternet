package com.example.newsinternet.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsinternet.databinding.OneNewsBinding
import com.example.newsinternet.domain.News

class OneNewsFragment(private val news: News) : Fragment() {
    companion object {
        const val TAG = "OneNewsFragment(news)"

        fun newInstance(news: News) = OneNewsFragment(news)
    }

    private lateinit var binding: OneNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OneNewsBinding.inflate(inflater, container, false)
        return binding.root
    }
}
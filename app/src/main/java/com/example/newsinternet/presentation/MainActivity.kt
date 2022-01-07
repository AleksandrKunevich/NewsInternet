package com.example.newsinternet.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.newsinternet.R
import com.example.newsinternet.data.network.dto.NewsResponse
import com.example.newsinternet.data.storage.AppDataBase
import com.example.newsinternet.data.storage.NewsDao
import com.example.newsinternet.databinding.ActivityMainBinding
import com.example.newsinternet.presentation.recycler.News
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main)
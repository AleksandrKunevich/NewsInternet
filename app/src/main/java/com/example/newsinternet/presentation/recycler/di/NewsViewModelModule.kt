package com.example.newsinternet.presentation.recycler.di

import com.example.newsinternet.presentation.recycler.NewsViewModel
// тут была ошибка импорат *.android* -> *.androidx.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsViewModelModule = module {
    viewModel<NewsViewModel> {
        NewsViewModel(interactor = get())
    }
}
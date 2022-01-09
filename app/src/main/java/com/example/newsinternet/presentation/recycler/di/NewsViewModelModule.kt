package com.example.newsinternet.presentation.recycler.di

import com.example.newsinternet.presentation.recycler.NewsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsViewModelModule = module {
    viewModel<NewsViewModel> {
        NewsViewModel(interactor = get())
    }
}
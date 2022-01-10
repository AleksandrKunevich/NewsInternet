package com.example.newsinternet.presentation.recycler.di

import com.example.newsinternet.presentation.recycler.NewsDataBaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsDataBaseViewModelModule = module {
    viewModel<NewsDataBaseViewModel> {
        NewsDataBaseViewModel(interactor = get())
    }
}
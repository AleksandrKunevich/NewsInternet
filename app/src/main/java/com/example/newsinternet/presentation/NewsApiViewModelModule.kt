package com.example.newsinternet.presentation

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsApiViewModelModule = module {
    viewModel<NewsApiViewModel> {
        NewsApiViewModel(interactor = get())
    }
}
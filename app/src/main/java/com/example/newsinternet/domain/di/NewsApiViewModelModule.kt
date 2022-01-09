package com.example.newsinternet.domain.di

import com.example.newsinternet.data.network.dto.NewsApiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsApiViewModelModule = module {
    viewModel<NewsApiViewModel> {
        NewsApiViewModel(interactor = get())
    }
}
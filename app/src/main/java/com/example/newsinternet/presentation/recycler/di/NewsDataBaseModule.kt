package com.example.newsinternet.presentation.recycler.di

import com.example.newsinternet.data.storage.NewsInteractorImpl
import com.example.newsinternet.domain.NewsInteractor
import org.koin.dsl.module

val newsDataBaseModule = module {
    single<NewsInteractor> {
        NewsInteractorImpl(newsDao = get())
    }
}
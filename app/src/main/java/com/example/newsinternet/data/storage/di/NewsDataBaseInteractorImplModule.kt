package com.example.newsinternet.data.storage.di

import com.example.newsinternet.data.storage.NewsDataBaseInteractorImpl
import com.example.newsinternet.domain.NewsDataBaseInteractor
import org.koin.dsl.module

val newsDataBaseInteractorImplModule = module {
    single<NewsDataBaseInteractor> {
        NewsDataBaseInteractorImpl(newsDao = get())
    }
}
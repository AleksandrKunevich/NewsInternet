package com.example.newsinternet.domain.di

import com.example.newsinternet.domain.NewsApiInteractor
import com.example.newsinternet.domain.NewsApiInteractorImp
import com.example.newsinternet.presentation.recycler.NewsDataBaseImpl
import com.example.newsinternet.presentation.recycler.NewsDataBaseInteractor
import org.koin.dsl.module

val newsInteractorImpModule = module {

    single<NewsApiInteractor> {
        NewsApiInteractorImp(newsApi = get())
    }

    single<NewsDataBaseInteractor> {
        NewsDataBaseImpl(newsDao = get())
    }
}
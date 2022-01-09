package com.example.newsinternet.domain.di

import com.example.newsinternet.domain.NewsApiInteractor
import com.example.newsinternet.domain.NewsApiInteractorImp
import org.koin.dsl.module

val newsInteractorImpModule = module {

    single<NewsApiInteractor> {
        NewsApiInteractorImp(newsApi = get())
    }
}
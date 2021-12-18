package com.example.newsinternet.data.network

import com.example.newsinternet.domain.NewsInteractor
import com.example.newsinternet.domain.NewsInteractorImp
import org.koin.dsl.module

val newsInteractorImpModule = module {
    single<NewsInteractor> {
        NewsInteractorImp(get())
    }
}
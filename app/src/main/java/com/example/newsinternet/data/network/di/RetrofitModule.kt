package com.example.newsinternet.data.network.di

import com.example.newsinternet.data.network.RetrofitClient
import org.koin.dsl.module

val retrofitModule = module {
    single {
        RetrofitClient.getNewsInternet()
    }
}
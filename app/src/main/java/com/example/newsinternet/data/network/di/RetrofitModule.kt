package com.example.newsinternet.data.network

import org.koin.dsl.module

val retrofitModule = module {
    single {
        RetrofitClient.getNewsInternet()
    }
}
package com.example.newsinternet

import android.app.Application
import com.example.newsinternet.data.network.newsInteractorImpModule
import com.example.newsinternet.data.network.retrofitModule
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                retrofitModule,
                newsInteractorImpModule
            )
        }
    }
}
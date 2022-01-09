package com.example.newsinternet

import android.app.Application
import com.example.newsinternet.data.network.newsInteractorImpModule
import com.example.newsinternet.data.network.retrofitModule
import com.example.newsinternet.data.storage.di.roomModule
import com.example.newsinternet.presentation.newsApiViewModelModule
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                roomModule,
                retrofitModule,
                newsInteractorImpModule,
                newsApiViewModelModule
            )
        }
    }
}
package com.example.newsinternet

import android.app.Application
import com.example.newsinternet.data.network.retrofitModule
import com.example.newsinternet.data.storage.di.roomModule
import com.example.newsinternet.domain.di.newsApiViewModelModule
import com.example.newsinternet.domain.di.newsInteractorImpModule
import com.example.newsinternet.presentation.recycler.di.newsViewModelModule
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                roomModule,
                retrofitModule,
                newsInteractorImpModule,
                newsApiViewModelModule,
                newsViewModelModule
            )
        }
    }
}
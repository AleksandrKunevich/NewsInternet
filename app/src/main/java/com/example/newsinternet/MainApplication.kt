package com.example.newsinternet

import android.app.Application
import com.example.newsinternet.data.network.di.retrofitModule
import com.example.newsinternet.data.storage.di.roomModule
import com.example.newsinternet.domain.di.newsApiViewModelModule
import com.example.newsinternet.domain.di.newsApiInteractorImpModule
import com.example.newsinternet.data.storage.di.newsDataBaseInteractorImplModule
import com.example.newsinternet.presentation.recycler.di.newsDataBaseViewModelModule
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                roomModule,
                newsApiInteractorImpModule,
                newsApiViewModelModule,
                newsDataBaseInteractorImplModule,
                newsDataBaseViewModelModule,
                retrofitModule
            )
        }
    }
}
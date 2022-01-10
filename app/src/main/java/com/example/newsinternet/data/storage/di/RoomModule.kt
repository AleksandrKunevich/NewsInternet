package com.example.newsinternet.data.storage.di

import androidx.room.Room
import com.example.newsinternet.data.storage.AppDataBase
import org.koin.dsl.module

val roomModule = module {
    single<AppDataBase> {
        Room.databaseBuilder(
            get(),
            AppDataBase::class.java,
            "room"
        ).build()
    }

    single {
        get<AppDataBase>().getNewsDao()
    }
}
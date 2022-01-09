package com.example.newsinternet.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsinternet.presentation.recycler.News

@Database(
    entities = [NewsEntity::class], version = AppDataBase.VERSION
)

abstract class AppDataBase : RoomDatabase() {
    companion object {
        const val VERSION = 1
    }

    abstract fun getNewsDao(): NewsDao
}


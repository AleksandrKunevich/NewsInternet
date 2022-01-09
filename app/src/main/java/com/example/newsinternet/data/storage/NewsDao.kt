package com.example.newsinternet.data.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {
    @Query("SELECT * FROM RoomDataBase")
    fun getAll(): List<NewsEntity>

    @Insert
    fun insert(news: NewsEntity)

    @Delete
    fun delete(news: NewsEntity)
}
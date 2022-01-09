package com.example.newsinternet.data.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newsinternet.presentation.recycler.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM RoomDataBase")
    fun getAll(): List<News>

    @Insert
    fun inset(news: News)

    @Delete
    fun delete(news: News)
}
package com.example.newsinternet.data.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room")
data class NewsEntity(

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,

    @ColumnInfo(name = "urlResource")
    val urlResource: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "isSaved")
    var isSaved: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    // Здесь была ошибка, был VAL, А надо VAR - изменяемый!!!
    var uid: Int = 0
}
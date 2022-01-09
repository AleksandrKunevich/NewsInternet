package com.example.newsinternet.data.storage

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "RoomDataBase")
@Parcelize
data class NewsEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "imageUrl") val imageUrl: String = "@drawable/ic_baseline_error_84",
    @ColumnInfo(name = "urlResource") val urlResource: String = "@drawable/ic_baseline_error_84",
    @ColumnInfo(name = "title") val title: String = "Error load title",
    @ColumnInfo(name = "isSaved") var isSaved: Boolean = false
) : Parcelable
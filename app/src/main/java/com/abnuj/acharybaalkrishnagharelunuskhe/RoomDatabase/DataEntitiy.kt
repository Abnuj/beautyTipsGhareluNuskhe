package com.abnuj.acharybaalkrishnagharelunuskhe.RoomDatabase

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarkTable")
data class DataEntitiy(
    @PrimaryKey(autoGenerate = true)
    var _id:Int = 0,
    var imageName: Int,
    var heading: String,
    var details: String, var isfavorite: Boolean = true
)

package com.abnuj.acharybaalkrishnagharelunuskhe.RoomDatabase

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(dataEntitiy: DataEntitiy)

    @Query("select * from bookmarkTable")
    suspend fun getAllData(): List<DataEntitiy>

    @Delete
    suspend fun deleteData(dataEntitiy: DataEntitiy)
}
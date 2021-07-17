package com.abnuj.acharybaalkrishnagharelunuskhe.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DataEntitiy::class), version = 1, exportSchema = false)
abstract class FavDatabase : RoomDatabase() {
    abstract val dao: Dao

    companion object {
        @Volatile
        private var Instance: FavDatabase? = null
        fun getDatabase(context: Context): FavDatabase {
            val tempInstance = Instance
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance: FavDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    FavDatabase::class.java,
                    "FavDatabase"
                ).build()
                Instance = instance
                return instance
            }
        }
    }
}

package com.heydar.simplemcv.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.heydar.simplemcv.model.database.dao.ProductDao
import com.heydar.simplemcv.model.database.entity.Product
import com.heydar.simplemcv.utils.Constants
import kotlin.concurrent.Volatile


@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = databaseBuilder(context.applicationContext, AppDatabase::class.java, Constants.DB_NAME).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
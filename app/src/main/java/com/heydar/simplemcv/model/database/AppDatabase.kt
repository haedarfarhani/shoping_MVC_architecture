package com.heydar.simplemcv.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.heydar.simplemcv.model.database.dao.ProductDao
import com.heydar.simplemcv.model.database.dao.UserDao
import com.heydar.simplemcv.model.database.entity.ProductEntity
import com.heydar.simplemcv.model.database.entity.UserEntity
import com.heydar.simplemcv.utils.Constants
import kotlin.concurrent.Volatile


@Database(entities = [ProductEntity::class,UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
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
package com.heydar.simplemcv.model.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.heydar.simplemcv.model.database.entity.Product


interface ProductDao {
    @Insert
    fun insert(user: Product?)

    @Update
    fun update(user: Product?)

    @Delete
    fun delete(user: Product?)

    @Query("SELECT * FROM product_table WHERE login_id = :userId")
    fun getUserByLoginId(userId: String?): Product?

    @Query("SELECT * FROM product_table")
    fun getAllUsers(): List<Product?>?
}
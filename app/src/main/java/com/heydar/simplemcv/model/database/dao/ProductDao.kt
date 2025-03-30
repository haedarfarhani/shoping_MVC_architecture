package com.heydar.simplemcv.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.heydar.simplemcv.model.database.entity.ProductEntity

@Dao
interface ProductDao {
    @Insert
    fun insert(user: ProductEntity)

    @Update
    fun update(user: ProductEntity)

    @Delete
    fun delete(user: ProductEntity)

    @Query("SELECT * FROM product")
    fun getAllUsers(): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE productId = :productId LIMIT 1")
    suspend fun findById(productId: Int): ProductEntity?
}
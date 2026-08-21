package com.example.myapplication.data.local.dao.stationery

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.local.entity.Stationery.ProductDataModel
import com.example.myapplication.data.local.entity.Stationery.SalesDataModel
import com.example.myapplication.domain.model.stationery.ProductData
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Upsert
    suspend fun upsertProduct(product: ProductDataModel)

    @Delete
    suspend fun deleteProduct(product: ProductDataModel)

    @Query("SELECT * FROM table_product ORDER BY created_at DESC")
    fun getAllProduct(): Flow<List<ProductDataModel>>

    @Query("SELECT * FROM table_product WHERE id= :id")
    suspend fun getProductById(id:Int): ProductDataModel?

    @Query("SELECT * FROM table_product WHERE stock>0 ORDER BY created_at DESC")
    fun getAvailableProducts(): Flow<List<ProductDataModel>>


    @Query("SELECT * FROM table_product WHERE product_name like  '%' || :productName || '%'")
    fun getSearchProductWithName(productName: String): Flow<List<ProductDataModel>>





}
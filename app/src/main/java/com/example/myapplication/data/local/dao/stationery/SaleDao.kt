package com.example.myapplication.data.local.dao.stationery

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.local.entity.Stationery.SalesDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SaleDao {
    @Upsert
    suspend fun upsertSale(salesDataModel: SalesDataModel)

    @Delete
    suspend fun deleteSale(salesDataModel: SalesDataModel)

    @Query("SELECT * FROM sales_product WHERE id = :id")
    suspend fun getSaleById(id: Int): SalesDataModel?

    @Query("SELECT * FROM sales_product ORDER by created_at DESC")
    fun getAllSales(): Flow<List<SalesDataModel>>

    @Query("SELECT * FROM sales_product WHERE date =:date ORDER BY created_at DESC" )
    fun getSaleByDate(date: String): Flow<List<SalesDataModel>>


    @Query("SELECT * FROM sales_product WHERE date like :month || '%' ORDER BY created_at DESC")
    fun getSaleByMonth(month: String): Flow<List<SalesDataModel>>

    @Query("SELECT * FROM sales_product WHERE product_id= :productId ORDER BY created_at DESC")
    suspend fun getsaleByProductId(productId: Int): List<SalesDataModel>
}






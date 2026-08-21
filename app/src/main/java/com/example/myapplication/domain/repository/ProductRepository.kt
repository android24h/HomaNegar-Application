package com.example.myapplication.domain.repository

import com.example.myapplication.data.local.entity.Stationery.ProductDataModel
import com.example.myapplication.domain.model.stationery.ProductData
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun upsertProduct(product: ProductData)
    suspend fun deleteProduct(productData: ProductData)
    suspend fun getProductById(id: Int): ProductData?
    fun getAllProduct(): Flow<List<ProductData>>
    fun getAvailableProducts(): Flow<List<ProductData>>

    fun getSearchProductWithName(name: String): Flow<List<ProductData>>

}
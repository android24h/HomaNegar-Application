package com.example.myapplication.data.repository

import com.example.myapplication.data.local.dao.stationery.ProductDao
import com.example.myapplication.data.mapper.toDataModel
import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.domain.model.stationery.ProductData
import com.example.myapplication.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productDao: ProductDao): ProductRepository {
    override suspend fun upsertProduct(productData: ProductData) {
        productDao.upsertProduct(productData.toDataModel())
    }

    override suspend fun deleteProduct(productData: ProductData) {
        productDao.deleteProduct(productData.toDataModel())
    }

    override suspend fun getProductById(id: Int): ProductData? {
        return productDao.getProductById(id)?.toDomain()
    }

    override fun getAllProduct(): Flow<List<ProductData>> {
        return productDao.getAllProduct().map { list->
            list.map { it.toDomain()}

        }
    }

    override fun getAvailableProducts(): Flow<List<ProductData>> {
        return productDao.getAvailableProducts().map{list->
            list.map { it.toDomain() }

        }
    }

    override fun getSearchProductWithName(name: String): Flow<List<ProductData>> {
        return productDao.getSearchProductWithName(name).map {list->
            list.map { it.toDomain() }

        }
    }


}
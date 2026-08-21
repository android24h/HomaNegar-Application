package com.example.myapplication.domain.useCase.stationery

import com.example.myapplication.domain.model.stationery.ProductData
import com.example.myapplication.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductUpsertUseCase @Inject constructor(private val repositoryProduct: ProductRepository) {
    suspend operator fun invoke(product: ProductData) {
        repositoryProduct.upsertProduct(product)
    }

}

class ProductDeleteUseCase @Inject constructor(private val repositoryProduct: ProductRepository){
    suspend operator fun invoke(productData: ProductData){
        repositoryProduct.deleteProduct(productData)
    }

}

class GetAllProduct @Inject constructor(private val repositoryProduct: ProductRepository){
    operator fun invoke(): Flow<List<ProductData>>{
        return repositoryProduct.getAllProduct()

    }
}

class SearchProductByName @Inject constructor(private val repositoryProduct: ProductRepository){
    operator fun invoke(name: String): Flow<List<ProductData>>{

        return repositoryProduct.getSearchProductWithName(name)
    }
}

class GetProductWithId @Inject constructor(private val repositoryProduct: ProductRepository){
    suspend operator fun invoke(id: Int): ProductData?{
        return repositoryProduct.getProductById(id)
    }
}

class GetAvailableProducts @Inject constructor(private val repositoryProduct: ProductRepository){
    operator fun invoke(): Flow<List<ProductData>>{
        return repositoryProduct.getAvailableProducts()
    }
}
package com.example.myapplication.domain.model.stationery

data class SaleData(
    val id: Int,
    val productId: Int,
     val date: String,
    val quantity:Int,
    val purchasePriceAsSale: Int,
    val salePriceAsSale: Int,
    val description: String? = null,
    val createdAt: Long= System.currentTimeMillis()
)

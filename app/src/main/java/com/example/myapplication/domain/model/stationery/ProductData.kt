package com.example.myapplication.domain.model.stationery

data class ProductData(
    val id:Int=0,
    val productName: String,
    val purchasePrice:Int,
    val salesPrice:Int,
    val stock:Int,
    val description: String?=null,
    val createdAt: Long= System.currentTimeMillis()
)

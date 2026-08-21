package com.example.myapplication.domain.model

data class PrintData(
    val id: Int,
    val serviceName: String,
    val price: Int,
    val quantity:Int,
    val date: String,
    val description: String="",
    val createdAt: Long= System.currentTimeMillis()
){
    val totalPrice: Int
        get() = price * quantity
}

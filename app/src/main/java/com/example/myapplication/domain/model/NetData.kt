package com.example.myapplication.domain.model

data class NetData(
    val id: Long,
    val serviceName: String,
    val price: Int,
    val quantity: Int =1,
    val date: String,
    val description: String? =null,
    val createAt: Long = System.currentTimeMillis(),
){
    val totalPrice: Int
        get()=price*quantity
}

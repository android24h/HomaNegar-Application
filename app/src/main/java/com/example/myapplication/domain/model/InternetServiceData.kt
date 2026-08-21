package com.example.myapplication.domain.model

data class InternetServiceData(
    val id:Int=0,
    val service: String,
    val price: Int,
    val created_ad: Long= System.currentTimeMillis()
)

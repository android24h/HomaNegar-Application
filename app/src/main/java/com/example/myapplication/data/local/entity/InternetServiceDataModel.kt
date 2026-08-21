package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("internet_service_table")
data class InternetServiceDataModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val service: String,
    val price: Int,
    val created_ad: Long=System.currentTimeMillis()
)

package com.example.myapplication.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "net_table")
data class NetDataModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long =0,
    @ColumnInfo("service_name")
    val serviceName: String,
    @ColumnInfo("price")
    val price: Int,
    @ColumnInfo("quantity")
    val quantity: Int =1,
    @ColumnInfo("date")
    val date: String,
    @ColumnInfo("description")
    val description: String? =null,
    @ColumnInfo("createAt")
    val createAt: Long = System.currentTimeMillis()
)
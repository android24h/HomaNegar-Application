package com.example.myapplication.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "print_table")
data class PrintDataModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("service_name")
    val serviceName: String,
    @ColumnInfo("price")
    val price: Int,
    @ColumnInfo("quantity")
    val quantity: Int,
    @ColumnInfo("date")
    val date: String,
    @ColumnInfo("description")
    val description: String="",
    @ColumnInfo("created_at")
    val createdAt: Long = System.currentTimeMillis()
)

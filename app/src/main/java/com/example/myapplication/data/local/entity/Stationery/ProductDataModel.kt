package com.example.myapplication.data.local.entity.Stationery

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_product")
data class ProductDataModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("product_name")
    val productName: String,
    @ColumnInfo("purchase_price")
    val purchasePrice: Int,
    @ColumnInfo("sale_price")
    val salesPrice: Int,
    @ColumnInfo("stock")
    val stock: Int,
    @ColumnInfo("description")
    val description: String? = null,
    @ColumnInfo("created_at")
    val createdAt: Long = System.currentTimeMillis()
)

package com.example.myapplication.data.local.entity.Stationery

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("sales_product")
data class SalesDataModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo("product_id")
    val productId: Int,
    @ColumnInfo("purchace_PriseAsSale")
     val purchasePriceAsSale: Int,
    @ColumnInfo("sale_Price")
    val salePriceAsSale: Int,
    @ColumnInfo("quantity")
    val quantity: Int,
    @ColumnInfo("date")
    val date: String,
    @ColumnInfo("description")
    val description: String? = null,
    @ColumnInfo("created_at")
    val createdAt: Long=System.currentTimeMillis()
)

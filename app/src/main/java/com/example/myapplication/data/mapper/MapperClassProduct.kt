package com.example.myapplication.data.mapper

import com.example.myapplication.data.local.entity.Stationery.ProductDataModel
import com.example.myapplication.domain.model.stationery.ProductData

fun ProductData.toDataModel(): ProductDataModel {
    return ProductDataModel(
        id=this.id,
        productName = this.productName,
        purchasePrice = this.purchasePrice,
        salesPrice = this.salesPrice,
        stock = this.stock,
        description = this.description,
        createdAt = this.createdAt

    )
}

fun ProductDataModel.toDomain(): ProductData{
    return ProductData(
        id=this.id,
        productName=this.productName,
        purchasePrice=this.purchasePrice,
        salesPrice=this.salesPrice,
        stock=this.stock,
        description=this.description,
        createdAt=this.createdAt
    )
}
package com.example.myapplication.data.mapper

import com.example.myapplication.data.local.entity.Stationery.SalesDataModel
import com.example.myapplication.domain.model.stationery.SaleData

fun SalesDataModel.toDomain(): SaleData {
    return SaleData(
        id = this.id,
        productId = this.productId,
        date = this.date,
        quantity = this.quantity,
        description = this.description,
        createdAt = this.createdAt,
        purchasePriceAsSale = this.purchasePriceAsSale,
        salePriceAsSale = this.salePriceAsSale
    )
}

fun SaleData.toDataModel(): SalesDataModel {
    return SalesDataModel(
        id = this.id,
        productId = this.productId,
        date = this.date,
        quantity = this.quantity,
        description = this.description,
        createdAt = this.createdAt,
        purchasePriceAsSale = this.purchasePriceAsSale,
        salePriceAsSale = this.salePriceAsSale
    )
}
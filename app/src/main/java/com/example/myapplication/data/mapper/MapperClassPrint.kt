package com.example.myapplication.data.mapper

import com.example.myapplication.data.local.entity.PrintDataModel
import com.example.myapplication.domain.model.PrintData

fun PrintDataModel.toDomainModel(): PrintData {
    return PrintData(
        id=this.id,
        serviceName = this.serviceName,
        price = this.price,
        quantity = this.quantity,
        date = this.date,
        description = this.description,
        createdAt = this.createdAt

    )

}

fun PrintData.toDataModel(): PrintDataModel{
    return PrintDataModel(
        id=this.id,
        serviceName = this.serviceName,
        price = this.price,
        quantity = this.quantity,
        date = this.date,
        description = this.description,
        createdAt = this.createdAt
    )
}
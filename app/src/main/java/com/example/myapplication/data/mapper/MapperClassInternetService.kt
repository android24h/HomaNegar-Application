package com.example.myapplication.data.mapper

import com.example.myapplication.data.local.entity.InternetServiceDataModel
import com.example.myapplication.domain.model.InternetServiceData

fun InternetServiceDataModel.toDomainModelInternet(): InternetServiceData{
    return InternetServiceData(
        id=this.id,
        service=this.service,
        price=this.price,
        created_ad=this.created_ad
    )
}

fun InternetServiceData.toDataModelInternet(): InternetServiceDataModel{
    return InternetServiceDataModel(
        id=this.id,
        service=this.service,
        price=this.price,
        created_ad=this.created_ad
    )

}
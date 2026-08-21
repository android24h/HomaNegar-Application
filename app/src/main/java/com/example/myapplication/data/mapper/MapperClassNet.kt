package com.example.myapplication.data.mapper

import com.example.myapplication.data.local.entity.NetDataModel
import com.example.myapplication.domain.model.NetData


    fun NetDataModel.toDomainModel(): NetData{
        return NetData(
            id =this.id,
            serviceName =this.serviceName,
            price =this.price,
            quantity =this.quantity,
            date =this.date,
            description =this.description,
            createAt =this.createAt
        )

    }

    fun NetData.toDataModel(): NetDataModel{
        return NetDataModel(
            id =this.id,
            serviceName =this.serviceName,
            price =this.price,
            quantity =this.quantity,
            date =this.date,
            description =this.description,
            createAt =this.createAt
        )
    }



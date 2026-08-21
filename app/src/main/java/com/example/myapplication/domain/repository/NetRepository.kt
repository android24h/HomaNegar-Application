package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.NetData
import kotlinx.coroutines.flow.Flow

interface NetRepository {
    suspend fun upsertNet(netData: NetData)
    suspend fun deleteNet(netData: NetData)
    fun getAllData(): Flow<List<NetData>>

    fun getNetDataByDate(date: String): Flow<List<NetData>>

    fun getNetDataByMonth(month: String): Flow<List<NetData>>

    suspend fun getNetDataByDateAndService(date: String,serviceName: String): NetData?


}
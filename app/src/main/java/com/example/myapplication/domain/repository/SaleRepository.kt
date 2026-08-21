package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.stationery.SaleData
import kotlinx.coroutines.flow.Flow

interface SaleRepository {
    suspend fun upsertDataSale(saleData: SaleData)

    suspend fun deleteDataSale(saleData: SaleData)

    fun getAllSale(): Flow<List<SaleData>>

    suspend fun getSaleById(id: Int): SaleData?

    fun getSaleByDate(date: String):Flow<List<SaleData>>

    fun getSaleByMonth(month: String): Flow<List<SaleData>>





}
package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.PrintData
import kotlinx.coroutines.flow.Flow

interface PrintRepository {
    suspend fun upsertPrint(printData: PrintData)
    suspend fun deletePrint(printData: PrintData)
    fun getAllPrintData(): Flow<List<PrintData>>
    fun getPrintByDate(date: String): Flow<List<PrintData>>
    fun getPrintByMonth(month: String): Flow<List<PrintData>>
}
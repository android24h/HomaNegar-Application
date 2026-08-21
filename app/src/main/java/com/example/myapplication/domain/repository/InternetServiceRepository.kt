package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.InternetServiceData
import kotlinx.coroutines.flow.Flow

interface InternetServiceRepository {

    suspend fun upsertInternetService(internetServiceData: InternetServiceData)
    suspend fun deleteInternetService(internetServiceData: InternetServiceData)
    fun showAllService(): Flow<List<InternetServiceData>>
}
package com.example.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.local.entity.InternetServiceDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface InternetServiceDao {
    @Upsert
    suspend fun upsertInternetService(internetServiceDataModel: InternetServiceDataModel)

    @Delete
    suspend fun deleteInternetService(internetServiceDataModel: InternetServiceDataModel)

    @Query("SELECT * FROM internet_service_table ORDER by created_ad DESC")
    fun getAllInternetService(): Flow<List<InternetServiceDataModel>>

    @Query("SELECT * FROM internet_service_table WHERE id=:id")
    suspend fun getNetServiceWithId(id: Int): InternetServiceDataModel?

    @Query("DELETE from internet_service_table")
    suspend fun deleteAllInternetService()
}
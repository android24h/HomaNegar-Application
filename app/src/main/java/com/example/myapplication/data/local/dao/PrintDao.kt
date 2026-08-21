package com.example.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query

import androidx.room.Upsert
import com.example.myapplication.data.local.entity.PrintDataModel
import kotlinx.coroutines.flow.Flow


@Dao
interface PrintDao {

    @Upsert
    suspend fun upsertPrint(printDataModel: PrintDataModel)

    @Delete
    suspend fun deletePrint(printDataModel: PrintDataModel)

    @Query("SELECT * FROM print_table order by created_at DESC")
    fun getAllPrintData(): Flow<List<PrintDataModel>>

    @Query("SELECT * FROM print_table WHERE  id = :id")
    suspend fun getPrintDataById(id: Int): PrintDataModel?

    @Query("SELECT * FROM print_table where date = :date order by created_at DESC")
    fun getPrintDataByDate(date: String): Flow<List<PrintDataModel>>

    @Query("SELECT * FROM print_table where date like :month || '%'")
    fun getPrintDataByMonth(month: String): Flow<List<PrintDataModel>>


}
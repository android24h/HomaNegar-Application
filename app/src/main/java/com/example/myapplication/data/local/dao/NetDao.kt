package com.example.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.local.entity.NetDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NetDao {

    @Upsert
    suspend fun upsertNet(netDataModel: NetDataModel)

    @Delete
    suspend fun deleteNet(netDataModel: NetDataModel)

    @Query("SELECT * FROM net_table ORDER BY createAt DESC")
    fun getAllNetData(): Flow<List<NetDataModel>>

    @Query("SELECT * FROM net_table WHERE id = :id")
    suspend fun getNetDataById(id: Long): NetDataModel?

    @Query("DELETE FROM net_table")
    suspend fun deleteAllNetData()

    @Query("  SELECT * FROM net_table WHERE date = :date ORDER BY createAt DESC")
    fun getNetDataByDate(date: String): Flow<List<NetDataModel>>

    @Query("SELECT * FROM net_table WHERE date LIKE :month || '%' ")
    fun getByMonth(month: String): Flow<List<NetDataModel>>

    // جدید
    // پیدا کردن یک خدمت مشخص در یک روز
    @Query("SELECT * FROM net_table WHERE date = :date AND service_name = :serviceName LIMIT 1")
    suspend fun getNetDataByDateAndService(
        date: String,
        serviceName: String
    ): NetDataModel?
}
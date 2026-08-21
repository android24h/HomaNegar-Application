package com.example.myapplication.data.repository

import com.example.myapplication.data.local.dao.stationery.SaleDao
import com.example.myapplication.data.mapper.toDataModel
import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.domain.model.stationery.SaleData
import com.example.myapplication.domain.repository.SaleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SaleRepositoryImpl @Inject constructor(private val saleDao: SaleDao): SaleRepository {
    override suspend fun upsertDataSale(saleData: SaleData) {
        saleDao.upsertSale(saleData.toDataModel())
    }

    override suspend fun deleteDataSale(saleData: SaleData) {
       saleDao.deleteSale(saleData.toDataModel())
    }

    override fun getAllSale(): Flow<List<SaleData>> {
       return saleDao.getAllSales().map { list->
           list.map { it.toDomain() }

       }
    }

    override suspend fun getSaleById(id: Int): SaleData? {
        return saleDao.getSaleById(id)?.toDomain()
    }

    override fun getSaleByDate(date: String): Flow<List<SaleData>> {
       return saleDao.getSaleByDate(date).map{list->
           list.map { it.toDomain() }

       }
    }

    override fun getSaleByMonth(month: String): Flow<List<SaleData>> {
        return saleDao.getSaleByMonth(month).map{list->
            list.map { it.toDomain() }

        }
    }
}
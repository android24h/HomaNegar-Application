package com.example.myapplication.data.repository

import com.example.myapplication.data.local.dao.PrintDao
import com.example.myapplication.data.mapper.toDataModel
import com.example.myapplication.data.mapper.toDomainModel
import com.example.myapplication.domain.model.PrintData
import com.example.myapplication.domain.repository.PrintRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PrintRepositoryImpl @Inject constructor(private val printDao: PrintDao): PrintRepository {
    override suspend fun upsertPrint(printData: PrintData) {
        printDao.upsertPrint(printData.toDataModel())
    }

    override suspend fun deletePrint(printData: PrintData) {
        printDao.deletePrint(printData.toDataModel())
    }

    override fun getAllPrintData(): Flow<List<PrintData>> {
        return printDao.getAllPrintData().map {list->
            list.map { it.toDomainModel() }

        }
    }

    override fun getPrintByDate(date: String): Flow<List<PrintData>> {
       return printDao.getPrintDataByDate(date).map {list->
           list.map {
               it.toDomainModel()
           }

       }
    }

    override fun getPrintByMonth(month: String): Flow<List<PrintData>> {
        return printDao.getPrintDataByMonth(month).map{list->
            list.map { it.toDomainModel() }

        }
    }

}
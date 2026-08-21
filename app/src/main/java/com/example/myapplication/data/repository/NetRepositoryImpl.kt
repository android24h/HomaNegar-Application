package com.example.myapplication.data.repository

import com.example.myapplication.data.local.dao.NetDao
import com.example.myapplication.data.mapper.toDataModel
import com.example.myapplication.data.mapper.toDomainModel
import com.example.myapplication.domain.model.NetData
import com.example.myapplication.domain.repository.NetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NetRepositoryImpl @Inject constructor(
    private val netDao: NetDao,

): NetRepository {
    override suspend fun upsertNet(netData: NetData) {
        netDao.upsertNet(netData.toDataModel())


    }

    override suspend fun deleteNet(netData: NetData) {
        netDao.deleteNet(netData.toDataModel())

    }

    override fun getAllData(): Flow<List<NetData>> {
        return netDao.getAllNetData().map {list->
            list.map { it.toDomainModel() }
        }
    }


    override fun getNetDataByDate(date: String): Flow<List<NetData>> {
        return netDao.getNetDataByDate(date).map {list->
            list.map { it.toDomainModel() }
        }

    }

    override fun getNetDataByMonth(month: String): Flow<List<NetData>> {
        return netDao.getByMonth(month).map { list->
            list.map {it.toDomainModel()  }

        }
    }

    override suspend fun getNetDataByDateAndService(date: String,serviceName: String): NetData? {
        return netDao
            .getNetDataByDateAndService(
                date = date,
                serviceName = serviceName
            )
            ?.toDomainModel()
    }
}
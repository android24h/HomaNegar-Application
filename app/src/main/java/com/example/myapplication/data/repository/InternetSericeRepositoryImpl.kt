package com.example.myapplication.data.repository

import com.example.myapplication.data.local.dao.InternetServiceDao
import com.example.myapplication.data.mapper.toDataModelInternet
import com.example.myapplication.data.mapper.toDomainModelInternet
import com.example.myapplication.domain.model.InternetServiceData
import com.example.myapplication.domain.repository.InternetServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InternetServiceRepositoryImpl @Inject constructor(private val internetServiceDao: InternetServiceDao) :
    InternetServiceRepository {
    override suspend fun upsertInternetService(internetServiceData: InternetServiceData) {
        internetServiceDao.upsertInternetService(internetServiceData.toDataModelInternet())
    }

    override suspend fun deleteInternetService(internetServiceData: InternetServiceData) {
        internetServiceDao.deleteInternetService(internetServiceData.toDataModelInternet())
    }

    override fun showAllService(): Flow<List<InternetServiceData>> {
        return internetServiceDao.getAllInternetService().map {item->
            item.map { it.toDomainModelInternet() }

        }
    }
}
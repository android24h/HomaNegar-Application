package com.example.myapplication.domain.useCase.net

import com.example.myapplication.domain.model.InternetServiceData
import com.example.myapplication.domain.repository.InternetServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpsertInternetServiceUseCase @Inject constructor(private val repository: InternetServiceRepository){
    suspend operator fun invoke(internetServiceData: InternetServiceData){
        repository.upsertInternetService(internetServiceData)
    }

}

class DeleteInternetServiceUseCase @Inject constructor(private val repository: InternetServiceRepository){
    suspend operator fun invoke(internetServiceData: InternetServiceData){
        repository.deleteInternetService(internetServiceData)
    }
}

class ShowAllInternetServiceUseCase @Inject constructor(private val repository: InternetServiceRepository){
    operator fun invoke(): Flow<List<InternetServiceData>>{
        return repository.showAllService()

    }
}

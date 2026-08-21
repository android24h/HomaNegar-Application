package com.example.myapplication.domain.useCase.print

import com.example.myapplication.domain.model.PrintData
import com.example.myapplication.domain.repository.PrintRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpsertPrintUseCase @Inject constructor(private val repository: PrintRepository){
    suspend operator fun invoke(printData: PrintData){
        repository.upsertPrint(printData)
    }

}

class DeletePrintUseCase @Inject constructor(private val repository: PrintRepository){
    suspend operator fun invoke(printData: PrintData){
        repository.deletePrint(printData)
    }

}

class GetAllPrintDataUseCase @Inject constructor(private val repository: PrintRepository){
    operator fun invoke(): Flow<List<PrintData>>{
        return repository.getAllPrintData()
    }
}

class GetPrintByDateUseCase @Inject constructor(val repository: PrintRepository){
    operator fun invoke(date: String): Flow<List<PrintData>>{
        return repository.getPrintByDate(date)
    }

}

class GetPrintByMonthUseCase @Inject constructor(private val repository: PrintRepository){
    operator fun invoke(month: String): Flow<List<PrintData>>{
       return repository.getPrintByMonth(month)
    }
}
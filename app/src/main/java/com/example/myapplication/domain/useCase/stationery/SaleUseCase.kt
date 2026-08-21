package com.example.myapplication.domain.useCase.stationery

import com.example.myapplication.domain.model.stationery.SaleData
import com.example.myapplication.domain.repository.SaleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpsertSaleUseCase @Inject constructor(val repository: SaleRepository){
    suspend operator fun invoke(saleData: SaleData){
        repository.upsertDataSale(saleData)
    }

}

class DeleteSaleUseCase @Inject constructor(private val repository: SaleRepository){
    suspend operator fun invoke(saleData: SaleData){
        repository.deleteDataSale(saleData)

    }
}

class GetAllSaleUseCase @Inject constructor(private val repository: SaleRepository){
     operator fun invoke(): Flow<List<SaleData>>{
      return  repository.getAllSale()

    }
}

class GetSaleByIdUseCase @Inject constructor(private val repository: SaleRepository){
    suspend operator fun invoke(id: Int): SaleData?{
        return repository.getSaleById(id)


    }


}

class SaleByDateUseCase @Inject constructor(private val repository: SaleRepository){
    operator fun invoke(date: String): Flow<List<SaleData>>{
        return repository.getSaleByDate(date)

    }
}

class SaleByMonthUseCase @Inject constructor(private val repository: SaleRepository){
    operator fun invoke(month: String): Flow<List<SaleData>>{
        return repository.getSaleByMonth(month)

    }
}
package com.example.myapplication.domain.useCase.net

import com.example.myapplication.domain.model.NetData
import com.example.myapplication.domain.repository.NetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpSertNetUseCase @Inject constructor(
    private val netRepository: NetRepository
) {

    suspend operator fun invoke(netData: NetData) {

        // بررسی می‌کنیم آیا همین خدمت در همین روز قبلاً ثبت شده یا نه
        val existingService =
            netRepository.getNetDataByDateAndService(
                date = netData.date,
                serviceName = netData.serviceName
            )

        if (existingService != null) {

            // اگر وجود داشت، تعداد قبلی را با تعداد جدید جمع می‌کنیم
            val updatedData =
                existingService.copy(
                    quantity =
                        existingService.quantity +
                                netData.quantity
                )

            netRepository.upsertNet(updatedData)

        } else {

            // اگر وجود نداشت، یک رکورد جدید ایجاد می‌کنیم
            netRepository.upsertNet(netData)
        }
    }
}

class DeleteNetUseCase @Inject constructor(private val netRepository: NetRepository){
    suspend operator fun invoke(netData: NetData){
        netRepository.deleteNet(netData)

    }
}

class GetAllNetUseCase @Inject constructor(private val netRepository: NetRepository){
    operator fun invoke(): Flow<List<NetData>>{
        return netRepository.getAllData()
    }
}

class GetNetDataByDateUseCase @Inject constructor(private val netRepository: NetRepository){
    operator fun invoke(date: String): Flow<List<NetData>>{
        return netRepository.getNetDataByDate(date)
    }

}

class GetNetDataByMonthUseCase @Inject constructor(
    private val repository: NetRepository
) {

    operator fun invoke(
        month: String
    ): Flow<List<NetData>> {

        return repository.getNetDataByMonth(month)
    }
}
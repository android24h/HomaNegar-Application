package com.example.myapplication.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.InternetServiceData
import com.example.myapplication.domain.model.NetData
import com.example.myapplication.domain.useCase.net.DeleteInternetServiceUseCase
import com.example.myapplication.domain.useCase.net.DeleteNetUseCase
import com.example.myapplication.domain.useCase.net.GetNetDataByDateUseCase
import com.example.myapplication.domain.useCase.net.GetNetDataByMonthUseCase
import com.example.myapplication.domain.useCase.net.ShowAllInternetServiceUseCase
import com.example.myapplication.domain.useCase.net.UpSertNetUseCase
import com.example.myapplication.domain.useCase.net.UpsertInternetServiceUseCase
import com.example.myapplication.presentation.util.PersianDateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetViewModel @Inject constructor(
    private val upSertNetUseCase: UpSertNetUseCase,
    private val deleteNetUseCase: DeleteNetUseCase,
    private val getNetDataByDateUseCase: GetNetDataByDateUseCase,
    private val getNetDataByMonthUseCase: GetNetDataByMonthUseCase,
    private val upsertInternetServiceUseCase: UpsertInternetServiceUseCase,
    private val deleteInternetServiceUseCase: DeleteInternetServiceUseCase,
    private val showAllInternetServiceUseCase: ShowAllInternetServiceUseCase
): ViewModel() {


    private val _selectedMonth =MutableStateFlow(PersianDateUtil.today().substring(0, 7))

    val selectedMonth =_selectedMonth.asStateFlow()

    fun changeMonth(month: String) {
        _selectedMonth.value = month
    }

    private val _selectedDate = MutableStateFlow(PersianDateUtil.today())
    val selectedDate = _selectedDate.asStateFlow()


    fun changeDate(date: String) {
        _selectedDate.value = date
    }

    val servicesByDate: Flow<List<NetData>> =
        selectedDate.flatMapLatest { date ->
            getNetDataByDateUseCase(date)
        }

    val totalByDate: Flow<Long> =
        servicesByDate.map { list ->
            list.sumOf { it.totalPrice }.toLong()
        }

    val monthlyServices: Flow<List<NetData>> =
        selectedMonth.flatMapLatest { month ->
            getNetDataByMonthUseCase(month)
        }
    val monthlyTotal: Flow<Long> =
        monthlyServices.map { list ->
            list.sumOf { it.totalPrice }.toLong()
        }
    val monthlyCount: Flow<Int> =
        monthlyServices.map { list ->
            list.sumOf { it.quantity }
        }

    val topService: Flow<String> =
        monthlyServices.map { list ->

            list.groupBy { it.serviceName }
                .maxByOrNull { entry ->
                    entry.value.sumOf { it.quantity }
                }
                ?.key
                ?: "-"
        }

    fun upsertNetData(netData: NetData){
        viewModelScope.launch {
            upSertNetUseCase(netData)

        }
    }

    fun deleteNetData(netData: NetData){
        viewModelScope.launch {
           deleteNetUseCase(netData)
        }
    }

    val showAllInternetServices: StateFlow<List<InternetServiceData>> =
        showAllInternetServiceUseCase()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun upsertInternetService(
        service: InternetServiceData
    ) {

        viewModelScope.launch {
            upsertInternetServiceUseCase(service)
        }
    }





}
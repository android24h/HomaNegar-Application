package com.example.myapplication.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.PrintData
import com.example.myapplication.domain.useCase.print.DeletePrintUseCase
import com.example.myapplication.domain.useCase.print.GetPrintByDateUseCase
import com.example.myapplication.domain.useCase.print.GetPrintByMonthUseCase
import com.example.myapplication.domain.useCase.print.UpsertPrintUseCase
import com.example.myapplication.presentation.util.PersianDateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrintViewModel @Inject constructor(
    private val upsertPrintUseCase: UpsertPrintUseCase,
    private val deletePrintUseCase: DeletePrintUseCase,
    private val getPrintByDateUseCase: GetPrintByDateUseCase,
    private val getPrintByMonthUseCase: GetPrintByMonthUseCase
) : ViewModel() {

    private val _selectedData = MutableStateFlow(PersianDateUtil.today())
    var selectedDate = _selectedData.asStateFlow()

    fun changeData(date: String) {
        _selectedData.value = date
    }

    val serviceByDate: Flow<List<PrintData>> =
        selectedDate.flatMapLatest { date ->
            getPrintByDateUseCase(date)
        }

    val totalPriceByDate: Flow<Int> =
        serviceByDate.map { list ->
            list.sumOf {
                it.totalPrice
            }

        }
    val currentMonth= PersianDateUtil.today().substring(0,7)

    val monthlyServices: Flow<List<PrintData>> =
        getPrintByMonthUseCase(currentMonth)


    val monthlyTotal: Flow<Int> =
        monthlyServices.map { list ->
            list.sumOf { it.totalPrice }
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
                ?.key ?: "-"
        }

    fun upsertPrintData(printData: PrintData){
        viewModelScope.launch {
            upsertPrintUseCase(printData)

        }
    }

    fun deletePrintData(printData: PrintData){
        viewModelScope.launch {
            deletePrintUseCase(printData)
        }
    }








}
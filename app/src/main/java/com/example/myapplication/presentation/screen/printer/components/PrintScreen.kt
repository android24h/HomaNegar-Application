package com.example.myapplication.presentation.screen.print

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.screen.print.component.AddServiceBottomSheet
import com.example.myapplication.presentation.screen.print.component.AddServiceButton
import com.example.myapplication.presentation.screen.print.component.DateSection
import com.example.myapplication.presentation.screen.print.component.ServiceItem
import com.example.myapplication.presentation.screen.print.component.SummarySection
import com.example.myapplication.presentation.screen.printer.components.DatePickerBottomSheet
import com.example.myapplication.presentation.viewModel.PrintViewModel

@Composable
fun PrintScreen(
    viewModel: PrintViewModel = hiltViewModel()
) {
    var showDatePicker by remember {mutableStateOf(false)}

    val services by viewModel.serviceByDate.collectAsState(initial = emptyList())

    val todayIncome by viewModel.totalPriceByDate.collectAsState(initial = 0)

    val monthlyIncome by viewModel.monthlyTotal.collectAsState(initial = 0)

    val monthlyCount by viewModel.monthlyCount.collectAsState(initial = 0)

    val topService by viewModel.topService.collectAsState(initial = "-")

    val selectedDate by viewModel.selectedDate.collectAsState()

    var showAddSheet by remember {
        mutableStateOf(false)
    }

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Scaffold(

            containerColor = MaterialTheme.colorScheme.background,

            bottomBar = {

                AddServiceButton(

                    onClick = {

                        showAddSheet = true

                    }

                )

            }

        ) { padding ->

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 6.dp,
                        bottom = 0.dp
                    )

            ) {

                DateSection(
                    date = selectedDate,
                    onClick = {
                        showDatePicker = true
                    }
                )

                SummarySection(

                    todayIncome = todayIncome,

                    monthlyIncome = monthlyIncome,

                    monthlyCount = monthlyCount,

                    topService = topService

                )

                Text(

                    text = "خدمات ثبت شده در این روز",

                    fontSize = 20.sp,

                    fontWeight = FontWeight.Bold,

                    color = MaterialTheme.colorScheme.onBackground,

                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 10.dp
                    )

                )

                LazyColumn(

                    modifier = Modifier.weight(1f),

                    contentPadding = PaddingValues(

                        start = 16.dp,

                        end = 16.dp,

                        top = 4.dp,

                        bottom = 90.dp

                    ),

                    verticalArrangement = Arrangement.spacedBy(12.dp)

                ) {

                    items(

                        items = services,

                        key = {

                            it.id

                        }

                    ) { service ->

                        ServiceItem(

                            service = service,

                            onDelete = {

                                viewModel.deletePrintData(service)

                            }

                        )

                    }

                }

            }


            if (showAddSheet) {

                AddServiceBottomSheet(

                    onDismiss = {

                        showAddSheet = false

                    },

                    onSave = {

                        viewModel.upsertPrintData(it)

                        showAddSheet = false

                    }

                )

            }
            if (showDatePicker) {

                DatePickerBottomSheet(

                    currentDate = selectedDate,

                    onDismiss = {
                        showDatePicker = false
                    },

                    onDateSelected = { date ->

                        viewModel.changeData(date)

                        showDatePicker = false
                    }
                )
            }

        }
    }





}
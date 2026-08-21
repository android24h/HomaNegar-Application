package com.example.myapplication.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.presentation.util.PersianDateUtil
import com.example.myapplication.presentation.viewModel.NetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthlyReportScreen(
    viewModel: NetViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val monthlyTotal =
        viewModel.monthlyTotal.collectAsState(initial = 0L)

    val monthlyCount =
        viewModel.monthlyCount.collectAsState(initial = 0)

    val topService =
        viewModel.topService.collectAsState(initial = "-")

    val monthlyServices =
        viewModel.monthlyServices.collectAsState(initial = emptyList())

    val selectedMonth by
    viewModel.selectedMonth.collectAsState()

    val months =
        PersianDateUtil.monthsOfYear()

    var monthExpanded by remember {
        mutableStateOf(false)
    }

    val selectedMonthTitle =
        months
            .find { it.second == selectedMonth }
            ?.first
            ?: selectedMonth

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {

            item {

                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "گزارش ماه $selectedMonthTitle",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Text("بازگشت")
                    }



                }


                ExposedDropdownMenuBox(

                    expanded = monthExpanded,

                    onExpandedChange = {
                        monthExpanded = !monthExpanded
                    }

                ) {

                    TextField(

                        modifier =
                            Modifier
                                .menuAnchor()
                                .fillMaxWidth(),

                        value =
                            selectedMonthTitle,

                        onValueChange = {},

                        readOnly = true,

                        label = {
                            Text("انتخاب ماه")
                        }
                    )

                    ExposedDropdownMenu(

                        expanded = monthExpanded,

                        onDismissRequest = {
                            monthExpanded = false
                        }

                    ) {

                        months.forEach { month ->

                            DropdownMenuItem(

                                text = {
                                    Text(month.first)
                                },

                                onClick = {

                                    viewModel.changeMonth(
                                        month.second
                                    )

                                    monthExpanded = false
                                }
                            )
                        }
                    }
                }



                Spacer(modifier = Modifier.height(16.dp))
            }

            item {

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "خلاصه عملکرد ماه",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "درآمد کل: ${monthlyTotal.value} تومان",
                            color = Color(0xFF2E7D32),
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "تعداد خدمات: ${monthlyCount.value}"
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "پرفروش‌ترین خدمت: ${topService.value}"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "ریز خدمات ماه",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            if (monthlyServices.value.isEmpty()) {

                item {

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "در این ماه اطلاعاتی ثبت نشده است",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

            items(monthlyServices.value) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = item.serviceName,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = item.date,
                            style = MaterialTheme.typography.bodySmall
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {

                            Card {
                                Text(
                                    text = "تعداد: ${item.quantity}",
                                    modifier = Modifier.padding(8.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Card {
                                Text(
                                    text = "${item.totalPrice} تومان",
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }

                        if (!item.description.isNullOrBlank()) {

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "توضیحات: ${item.description}"
                            )
                        }
                    }
                }
            }
        }

    }


}
package com.example.myapplication.presentation.screen.printer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.util.PersianDateUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerBottomSheet(
    currentDate: String,
    onDismiss: () -> Unit,
    onDateSelected: (String) -> Unit
) {

    val parts = currentDate.split("/")

    val initialYear = parts[0].toInt()
    val initialMonth = parts[1].toInt()
    val initialDay = parts[2].toInt()

    var selectedYear by remember {
        mutableIntStateOf(initialYear)
    }

    var selectedMonth by remember {
        mutableIntStateOf(initialMonth)
    }

    var selectedDay by remember {
        mutableIntStateOf(initialDay)
    }

    val years = PersianDateUtil.getYears()

    val months = PersianDateUtil.getMonths(selectedYear)

    val days = PersianDateUtil.getDays(
        selectedYear,
        selectedMonth
    )

    if (selectedDay > days.size) {
        selectedDay = days.size
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 30.dp
                ),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Text(
                text = "انتخاب تاریخ",
                fontSize = 22.sp
            )

            Text(
                text = PersianDateUtil.createDate(
                    selectedYear,
                    selectedMonth,
                    selectedDay
                ),
                fontSize = 20.sp,
                modifier = Modifier.padding(
                    top = 12.dp,
                    bottom = 20.dp
                )
            )

            Text(
                text = "سال",
                fontSize = 16.sp
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(vertical = 5.dp),

                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(years) { year ->

                    if (year == selectedYear) {

                        Button(
                            onClick = {
                                selectedYear = year
                            }
                        ) {
                            Text(year.toString())
                        }

                    } else {

                        TextButton(
                            onClick = {
                                selectedYear = year
                            }
                        ) {
                            Text(year.toString())
                        }
                    }
                }
            }

            Text(
                text = "ماه",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 15.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(vertical = 5.dp),

                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(months) { month ->

                    val monthName = month.first
                    val monthNumber = month.second

                    if (monthNumber == selectedMonth) {

                        Button(
                            onClick = {
                                selectedMonth = monthNumber
                            }
                        ) {
                            Text(monthName)
                        }

                    } else {

                        TextButton(
                            onClick = {
                                selectedMonth = monthNumber
                            }
                        ) {
                            Text(monthName)
                        }
                    }
                }
            }

            Text(
                text = "روز",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 15.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(vertical = 5.dp),

                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(days) { day ->

                    if (day == selectedDay) {

                        Button(
                            onClick = {
                                selectedDay = day
                            }
                        ) {
                            Text(day.toString())
                        }

                    } else {

                        TextButton(
                            onClick = {
                                selectedDay = day
                            }
                        ) {
                            Text(day.toString())
                        }
                    }
                }
            }

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),

                horizontalArrangement = Arrangement.spacedBy(12.dp)

            ) {

                TextButton(
                    modifier = Modifier.weight(1f),
                    onClick = onDismiss
                ) {
                    Text("انصراف")
                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {

                        val date = PersianDateUtil.createDate(
                            selectedYear,
                            selectedMonth,
                            selectedDay
                        )

                        onDateSelected(date)

                    }
                ) {
                    Text("تأیید")
                }
            }
        }
    }
}
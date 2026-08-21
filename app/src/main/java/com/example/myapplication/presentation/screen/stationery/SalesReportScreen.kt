package com.example.myapplication.presentation.screen.stationery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.util.toPersianNumber
import com.example.myapplication.presentation.viewModel.stationery.SaleProductViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SalesReportScreen(
    saleViewModel: SaleProductViewModel = hiltViewModel()
) {

    val sales by saleViewModel.showAllSaleProduct.collectAsState()

    val today = SimpleDateFormat(
        "yyyy/MM/dd",
        Locale.getDefault()
    ).format(Date())

    val currentMonth = today.substring(0, 7)

    val todaySales = sales.filter {
        it.date == today
    }

    val monthSales = sales.filter {
        it.date.startsWith(currentMonth)
    }

    val todayCount = todaySales.sumOf {
        it.quantity
    }

    val monthCount = monthSales.sumOf {
        it.quantity
    }

    val todayAmount = todaySales.sumOf {
        it.salePriceAsSale * it.quantity
    }

    val monthAmount = monthSales.sumOf {
        it.salePriceAsSale * it.quantity
    }

    val todayProfit = todaySales.sumOf {
        (it.salePriceAsSale - it.purchasePriceAsSale) * it.quantity
    }

    val monthProfit = monthSales.sumOf {
        (it.salePriceAsSale - it.purchasePriceAsSale) * it.quantity
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "📊 گزارش فروش",
            style = MaterialTheme.typography.headlineMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "گزارش امروز",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "تعداد کالاهای فروخته‌شده: ${todayCount.toPersianNumber()}"
                )

                Text(
                    text = "مبلغ فروش: ${todayAmount.toPersianNumber()} تومان"
                )

                Text(
                    text = "سود تقریبی: ${todayProfit.toPersianNumber()} تومان"
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "گزارش این ماه",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "تعداد کالاهای فروخته‌شده: ${monthCount.toPersianNumber()}"
                )

                Text(
                    text = "مبلغ فروش: ${monthAmount.toPersianNumber()} تومان"
                )

                Text(
                    text = "سود تقریبی: ${monthProfit.toPersianNumber()} تومان"
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "تعداد تراکنش‌های امروز: ${todaySales.size.toPersianNumber()}",
                modifier = Modifier.padding(16.dp)
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "تعداد تراکنش‌های این ماه: ${monthSales.size.toPersianNumber()}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
package com.example.myapplication.presentation.screen.stationery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.domain.model.stationery.ProductData
import com.example.myapplication.presentation.util.toPersianNumber
import com.example.myapplication.presentation.viewModel.stationery.ProductViewModel

@Composable
fun LowStockScreen(
    productViewModel: ProductViewModel = hiltViewModel()
) {

    val products by productViewModel.productList.collectAsState()

    val lowStockProducts = products.filter {
        it.stock <= 5
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "⚠️ موجودی کم",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "کالاهای دارای موجودی ۵ عدد یا کمتر",
            modifier = Modifier.padding(vertical = 12.dp)
        )

        if (lowStockProducts.isEmpty()) {

            Text(
                text = "هیچ کالایی موجودی کم ندارد.",
                modifier = Modifier.padding(16.dp)
            )

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(
                    items = lowStockProducts,
                    key = { it.id }
                ) { product ->

                    LowStockItem(
                        product = product
                    )
                }
            }
        }
    }
}

@Composable
fun LowStockItem(
    product: ProductData
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = product.productName,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "کد کالا: ${product.id.toPersianNumber()}"
            )

            Text(
                text = "موجودی فعلی: ${product.stock.toPersianNumber()}"
            )

            Text(
                text = "قیمت فروش: ${product.salesPrice.toPersianNumber()} تومان"
            )
        }
    }
}
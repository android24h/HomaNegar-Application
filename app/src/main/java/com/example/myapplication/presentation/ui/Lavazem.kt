package com.example.myapplication.presentation.screen.stationery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Lavazem(
    navController: NavHostController
) {

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "لوازم‌التحریر",
                style = MaterialTheme.typography.headlineMedium
            )

            // مدیریت کالاها
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("product")
                    }
            ) {

                Text(
                    text = "📦 مدیریت کالاها",
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // ثبت فروش
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("sale")
                    }
            ) {

                Text(
                    text = "🛒 ثبت فروش",
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // گزارش‌ها
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("sales_report")
                    }
            ) {

                Text(
                    text = "📊 گزارش‌ها",
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // موجودی کم
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("low_stock")
                    }
            ) {

                Text(
                    text = "⚠️ موجودی کم",
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
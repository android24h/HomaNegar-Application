package com.example.myapplication.presentation.screen.print.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.ui.theme.Primary


@Composable
fun SummarySection(
    todayIncome: Int,
    monthlyIncome: Int,
    monthlyCount: Int,
    topService: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        SummaryCard(
            modifier = Modifier.weight(1f),
            title = "درآمد امروز",
            value = "$todayIncome تومان",
            icon = Icons.Default.AttachMoney
        )

        SummaryCard(
            modifier = Modifier.weight(1f),
            title = "درآمد ماه",
            value = "$monthlyIncome تومان",
            icon = Icons.Default.CalendarMonth
        )
    }
}


@Composable
private fun SummaryCard(
    modifier: Modifier,
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {

        Card(
            modifier = modifier.height(82.dp),

            shape = RoundedCornerShape(20.dp),

            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),

                verticalArrangement = Arrangement.SpaceBetween,

                horizontalAlignment = Alignment.End
            ) {

                // آیکون
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Primary
                )

                // عنوان و مبلغ در یک خط
                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    // عنوان
                    Text(
                        text = title,

                        fontSize = 11.sp,

                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant,

                        textAlign = TextAlign.Right
                    )

                    // مبلغ
                    Text(
                        text = value,

                        fontSize = 14.sp,

                        fontWeight = FontWeight.Bold,

                        color = Primary,

                        textAlign = TextAlign.Right
                    )
                }
            }
        }
    }
}
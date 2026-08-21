package com.example.myapplication.presentation.screen.print.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.ui.theme.Primary
import androidx.compose.material3.MaterialTheme

@Composable
fun DateSection(
    date: String,
    onClick: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 6.dp,
                bottom = 10.dp
            )
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically

        ) {

            Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = null,
                tint = Primary
            )

            Text(
                text = date,
                fontSize = 18.sp,
                color = Primary
            )

            Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = null,
                tint = Primary
            )
        }
    }
}
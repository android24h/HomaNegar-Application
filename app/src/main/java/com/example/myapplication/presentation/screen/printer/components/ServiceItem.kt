package com.example.myapplication.presentation.screen.print.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Print
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.domain.model.PrintData
import com.example.myapplication.presentation.ui.theme.Danger
import androidx.compose.material3.MaterialTheme


@Composable
fun ServiceItem(
    service: PrintData,
    onDelete: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp),

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )

    ) {


        Row(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp),

            verticalAlignment = Alignment.CenterVertically

        ) {


            // آیکون خدمت

            Box(

                modifier = Modifier
                    .size(45.dp)
                    .background(
                        Color(0xFF6D4CFF),
                        RoundedCornerShape(12.dp)
                    ),

                contentAlignment = Alignment.Center

            ) {

                Icon(

                    imageVector = Icons.Default.Print,

                    contentDescription = null,

                    tint = Color.White,

                    modifier = Modifier.size(24.dp)

                )

            }


            Spacer(
                modifier = Modifier.width(14.dp)
            )


            // نام خدمت

            Text(

                text = service.serviceName,

                modifier = Modifier
                    .weight(1f),

                textAlign = TextAlign.Right,

                fontSize = 17.sp,

                fontWeight = FontWeight.Bold,

                color = MaterialTheme.colorScheme.onSurface

            )



            // تعداد

            Text(

                text = "${service.quantity} عدد",

                fontSize = 14.sp,

                color =  MaterialTheme.colorScheme.onSurfaceVariant

            )



            Spacer(
                modifier = Modifier.width(20.dp)
            )



            // قیمت

            Text(

                text = "${service.totalPrice}",

                fontSize = 17.sp,

                fontWeight = FontWeight.Bold,

                color = MaterialTheme.colorScheme.onSurface

            )



            Spacer(
                modifier = Modifier.width(12.dp)
            )



            // حذف

            IconButton(

                onClick = onDelete,

                modifier = Modifier.size(40.dp)

            ) {

                Icon(

                    imageVector = Icons.Default.Delete,

                    contentDescription = null,

                    tint = Danger,

                    modifier = Modifier.size(22.dp)

                )

            }


        }


    }


}
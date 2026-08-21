package com.example.myapplication.presentation.screen.print.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.ui.theme.Primary
import com.example.myapplication.presentation.ui.theme.PrimaryLight


@Composable
fun Header(){


    Row(

        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(

                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Primary,
                        PrimaryLight
                    )
                )

            ),

        horizontalArrangement = Arrangement.SpaceBetween,

        verticalAlignment = Alignment.CenterVertically

    ){


        Icon(

            imageVector = Icons.Default.Menu,

            contentDescription = null,

            tint = Color.White

        )


        Text(

            text = "گزارش خدمات چاپ",

            color = Color.White,

            fontSize = 20.sp

        )


        Icon(

            imageVector = Icons.Default.BarChart,

            contentDescription = null,

            tint = Color.White

        )


    }


}
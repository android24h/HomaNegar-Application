package com.example.myapplication.tamrin

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

data class InternetService(
    val name: String,
    val price: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Temrin() {

    val internetServices = listOf(
        InternetService("Service 1", 10),
        InternetService("Service 2", 20),
        InternetService("Service 3", 30)
    )

    var expended by remember { mutableStateOf(false) }
    var selectedName by remember { mutableStateOf("") }
    var selectedprice by remember { mutableStateOf<Int?>(null) }

    ExposedDropdownMenuBox(
        expanded = expended,
        onExpandedChange = { expended = !expended }
    ) {
        TextField(
            value = selectedName,
            onValueChange = {},
            modifier = Modifier.menuAnchor(),
            label = { Text("Select Internet Service") },
            readOnly = true
        )
        ExposedDropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false }
        ) {
            internetServices.forEach { service ->
                DropdownMenuItem(
                    text = { Text(service.name) },
                    onClick = {
                        selectedName=service.name
                        selectedprice= service.price
                        expended = false
                    }
                )

            }
        }


    }

    if (selectedName.isNotBlank()){
        Text("your price is:= $selectedprice")
    }
    }




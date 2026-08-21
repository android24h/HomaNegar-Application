package com.example.myapplication.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection

@Composable
fun SaleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    keyboardType: KeyboardType= KeyboardType.Text,
    modifier: Modifier= Modifier

) {
    OutlinedTextField(
        value=value,
        onValueChange=onValueChange,
        placeholder = {
            Text(placeHolder)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType=keyboardType
        ),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            textDirection = TextDirection.Rtl
        )
    )

}
package com.example.myapplication.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun ProductTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier= Modifier,


    ) {
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        OutlinedTextField(
           modifier= modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,

            placeholder = {

                Text(
                    placeHolder
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                textDirection = TextDirection.Rtl
            ),
            shape =
                RoundedCornerShape(14.dp),

            colors =
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor =
                        Color(0xFF0F766E),

                    unfocusedBorderColor =
                        Color.LightGray
                )
        )

    }


}
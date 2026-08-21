package com.example.myapplication.presentation.screen.print.component

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.domain.model.PrintData
import com.example.myapplication.presentation.ui.theme.PinkButton
import com.example.myapplication.presentation.ui.theme.Primary
import com.example.myapplication.presentation.util.PersianDateUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddServiceBottomSheet(

    onDismiss: () -> Unit,

    onSave: (PrintData) -> Unit

) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val scrollState = rememberScrollState()

    val keyboardController = LocalSoftwareKeyboardController.current


    var expanded by remember {
        mutableStateOf(false)
    }


    val services = listOf(
        "پرینت A4",
        "پرینت A3",
        "فتوکپی",
        "اسکن",
        "لمینت",
        "فنر"
    )


    var serviceName by remember {
        mutableStateOf(services.first())
    }


    var quantity by remember {
        mutableStateOf("")
    }


    var price by remember {
        mutableStateOf("")
    }


    var description by remember {
        mutableStateOf("")
    }


    val total =
        (price.toIntOrNull() ?: 0) *
                (quantity.toIntOrNull() ?: 0)



    ModalBottomSheet(

        onDismissRequest = onDismiss,

        sheetState = sheetState,

        dragHandle = {

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .width(60.dp)
                    .height(5.dp)
                    .navigationBarsPadding()
            ) {

            }

        },

        shape = RoundedCornerShape(
            topStart = 28.dp,
            topEnd = 28.dp
        )

    ) {


        Column(

            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
                .verticalScroll(scrollState)
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 30.dp
                ),

            verticalArrangement = Arrangement.spacedBy(14.dp)

        ) {


            Text(

                text = "ثبت خدمت جدید",

                fontSize = 22.sp,

                fontWeight = FontWeight.Bold,

                color = Primary

            )



            ExposedDropdownMenuBox(

                expanded = expanded,

                onExpandedChange = {

                    expanded = !expanded

                }

            ) {


                OutlinedTextField(

                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(
                            MenuAnchorType.PrimaryNotEditable
                        ),

                    value = serviceName,

                    onValueChange = {},

                    readOnly = true,

                    label = {
                        Text("نوع خدمت")
                    },

                    trailingIcon = {

                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )

                    }

                )



                DropdownMenu(

                    expanded = expanded,

                    onDismissRequest = {

                        expanded = false

                    }

                ) {


                    services.forEach {


                        DropdownMenuItem(

                            text = {

                                Text(it)

                            },

                            onClick = {

                                serviceName = it

                                expanded = false

                            }

                        )


                    }

                }


            }



            OutlinedTextField(

                modifier = Modifier.fillMaxWidth(),

                value = quantity,

                onValueChange = {

                    quantity = it

                },

                label = {

                    Text("تعداد")

                }

            )



            OutlinedTextField(

                modifier = Modifier.fillMaxWidth(),

                value = price,

                onValueChange = {

                    price = it

                },

                label = {

                    Text("قیمت هر واحد")

                },

                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                    keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
                )

            )



            OutlinedTextField(

                modifier = Modifier.fillMaxWidth(),

                value = description,

                onValueChange = {

                    description = it

                },

                label = {

                    Text("توضیحات")

                },

                minLines = 3

            )



            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(18.dp),

                colors = CardDefaults.cardColors(

                    containerColor = Primary.copy(
                        alpha = 0.08f
                    )

                )

            ) {


                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),

                    horizontalArrangement = Arrangement.SpaceBetween,

                    verticalAlignment = Alignment.CenterVertically

                ) {


                    Text(
                        text = "مبلغ کل",
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )


                    Text(

                        text = "$total تومان",

                        fontSize = 20.sp,

                        fontWeight = FontWeight.Bold,

                        color = Primary

                    )


                }


            }



            Button(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(

                    containerColor = PinkButton

                ),


                onClick = {


                    keyboardController?.hide()


                    val item = PrintData(

                        id = 0,

                        serviceName = serviceName,

                        price = price.toIntOrNull() ?: 0,

                        quantity = quantity.toIntOrNull() ?: 0,

                        date = PersianDateUtil.today(),

                        description = description

                    )


                    onSave(item)


                }

            ) {


                Text(

                    text = "ثبت خدمت جدید",

                    fontSize = 17.sp,

                    fontWeight = FontWeight.Bold

                )


            }


        }


    }


}
package com.example.myapplication.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.domain.model.InternetServiceData
import com.example.myapplication.domain.model.NetData
import com.example.myapplication.presentation.util.PersianDateUtil
import com.example.myapplication.presentation.util.toPersianNumber
import com.example.myapplication.presentation.viewModel.NetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InternetScreen(
    viewModel: NetViewModel = hiltViewModel(),
    navController: NavHostController
) {

    // =========================================================
    // سرویس‌های تعریف‌شده در Room
    // =========================================================

    val internetService by viewModel
        .showAllInternetServices
        .collectAsState()

    // =========================================================
    // وضعیت ویرایش
    // =========================================================

    var editingItem by remember {
        mutableStateOf<NetData?>(null)
    }

    // =========================================================
    // وضعیت حذف
    // =========================================================

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    var selectedItem by remember {
        mutableStateOf<NetData?>(null)
    }

    // =========================================================
    // سرویس انتخاب‌شده از Dropdown
    // =========================================================

    var selectedServiceInternet by remember {
        mutableStateOf<InternetServiceData?>(null)
    }

    // =========================================================
    // تاریخ‌ها
    // =========================================================

    val dates = PersianDateUtil.last7Days()

    var dateExpanded by remember {
        mutableStateOf(false)
    }

    // =========================================================
    // اطلاعات ثبت‌شده بر اساس تاریخ
    // =========================================================

    val servicesByDate =
        viewModel
            .servicesByDate
            .collectAsState(
                initial = emptyList()
            )

    val totalByDate =
        viewModel
            .totalByDate
            .collectAsState(
                initial = 0L
            )

    val selectedDate =
        viewModel
            .selectedDate
            .collectAsState()

    // =========================================================
    // تعداد
    // =========================================================

    var quantity by remember {
        mutableStateOf(1)
    }

    // =========================================================
    // توضیحات
    // =========================================================

    var description by remember {
        mutableStateOf("")
    }

    // =========================================================
    // باز و بسته بودن Dropdown خدمات
    // =========================================================

    var expanded by remember {
        mutableStateOf(false)
    }

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {

        Column(

            modifier =
                Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.background
                    )
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    ),

            verticalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            // =====================================================
            // Header
            // =====================================================

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme.colorScheme.surface
                    ),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation =
                            2.dp
                    )
            ) {

                Row(

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(

                        text =
                            "خدمات ${selectedDate.value}",

                        modifier =
                            Modifier.weight(1f),

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    Button(

                        colors =
                            ButtonDefaults
                                .buttonColors(
                                    containerColor =
                                        Color(0xFF0F766E)
                                ),

                        onClick = {

                            navController
                                .navigate(
                                    "monthly_report"
                                )
                        }
                    ) {

                        Text(
                            text =
                                "گزارش ماهانه"
                        )
                    }
                }
            }

            // =====================================================
            // فرم ثبت خدمت
            // =====================================================

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme.colorScheme.surface
                    ),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation =
                            2.dp
                    )
            ) {

                Column(

                    modifier =
                        Modifier.padding(16.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    Text(

                        text =
                            "ثبت خدمت جدید",

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    // =================================================
                    // انتخاب تاریخ
                    // =================================================

                    ExposedDropdownMenuBox(

                        expanded =
                            dateExpanded,

                        onExpandedChange = {

                            dateExpanded =
                                !dateExpanded
                        }
                    ) {

                        OutlinedTextField(

                            modifier =
                                Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),

                            // اینجا باید تاریخ نمایش داده شود
                            value =
                                selectedDate.value,

                            onValueChange = {},

                            readOnly = true,

                            label = {

                                Text(
                                    text =
                                        "تاریخ"
                                )
                            },
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

                        ExposedDropdownMenu(

                            expanded =
                                dateExpanded,

                            onDismissRequest = {

                                dateExpanded =
                                    false
                            }
                        ) {

                            dates.forEach { date ->

                                DropdownMenuItem(

                                    text = {

                                        Text(
                                            text =
                                                date
                                        )
                                    },

                                    onClick = {

                                        viewModel
                                            .changeDate(
                                                date
                                            )

                                        dateExpanded =
                                            false
                                    }
                                )
                            }
                        }
                    }

                    // =================================================
                    // انتخاب خدمت اینترنتی
                    // =================================================

                    ExposedDropdownMenuBox(

                        expanded =
                            expanded,

                        onExpandedChange = {

                            expanded =
                                !expanded
                        }
                    ) {

                        OutlinedTextField(

                            modifier =
                                Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),

                            value =
                                selectedServiceInternet
                                    ?.service
                                    ?: "",

                            onValueChange = {},

                            readOnly = true,

                            label = {

                                Text(
                                    text =
                                        "انتخاب خدمت"
                                )
                            },
                            shape =
                                RoundedCornerShape(14.dp),

                            colors =
                                OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor =
                                        Color(0xFF0F766E),

                                    unfocusedBorderColor =
                                        MaterialTheme.colorScheme.outline
                                )
                        )

                        ExposedDropdownMenu(

                            expanded =
                                expanded,

                            onDismissRequest = {

                                expanded =
                                    false
                            }
                        ) {

                            internetService.forEach { service ->

                                DropdownMenuItem(

                                    text = {

                                        Text(
                                            text =
                                                "${service.service} - ${service.price.toPersianNumber()} تومان"
                                        )
                                    },

                                    onClick = {

                                        // مهم:
                                        // کل آبجکت سرویس را ذخیره می‌کنیم
                                        selectedServiceInternet =
                                            service


                                        expanded =
                                            false
                                    }
                                )
                            }
                        }
                    }

                    // =================================================
                    // تعداد
                    // =================================================

                    Row(

                        modifier =
                            Modifier.fillMaxWidth(),

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Button(

                            onClick = {

                                if (
                                    quantity > 1
                                ) {

                                    quantity--
                                }
                            }
                        ) {

                            Text(
                                text =
                                    "-"
                            )
                        }

                        Text(

                            text =
                                quantity.toString(),

                            modifier =
                                Modifier.padding(
                                    horizontal = 20.dp
                                ),

                            style =
                                MaterialTheme
                                    .typography
                                    .titleMedium
                        )

                        Button(

                            onClick = {

                                quantity++
                            }
                        ) {

                            Text(
                                text =
                                    "+"
                            )
                        }
                    }

                    // =================================================
                    // توضیحات
                    // =================================================

                    OutlinedTextField(

                        modifier =
                            Modifier.fillMaxWidth(),

                        value =
                            description,

                        onValueChange = {

                            description =
                                it
                        },

                        label = {

                            Text(
                                text =
                                    "توضیحات"
                            )
                        },

                        minLines = 2,
                        shape =
                            RoundedCornerShape(14.dp),

                        colors =
                            OutlinedTextFieldDefaults.colors(
                                focusedBorderColor =
                                    Color(0xFF0F766E),

                                unfocusedBorderColor =
                                    MaterialTheme.colorScheme.outline
                            )
                    )

                    // =================================================
                    // ثبت / ذخیره تغییرات
                    // =================================================

                    Button(

                        colors =
                            ButtonDefaults
                                .buttonColors(
                                    containerColor =
                                        Color(0xFF0F766E)
                                ),

                        modifier =
                            Modifier.fillMaxWidth(),

                        // تا وقتی خدمت انتخاب نشده دکمه غیرفعال است
                        enabled =
                            selectedServiceInternet != null,

                        onClick = {

                            // چون دکمه فقط وقتی فعال است که
                            // سرویس انتخاب شده باشد، اینجا safely
                            // می‌توانیم از selectedServiceInternet استفاده کنیم.

                            val service =
                                selectedServiceInternet
                                    ?: return@Button

                            viewModel
                                .upsertNetData(

                                    NetData(

                                        id =
                                            editingItem
                                                ?.id
                                                ?: 0,

                                        serviceName =
                                            service.service,

                                        price =
                                            service.price,

                                        quantity =
                                            quantity,

                                        date =
                                            selectedDate
                                                .value,

                                        description =
                                            description
                                                .ifBlank {
                                                    null
                                                }
                                    )
                                )

                            // -------------------------------
                            // پاک کردن فرم
                            // -------------------------------

                            editingItem =
                                null

                            selectedServiceInternet =
                                null

                            quantity =
                                1

                            description =
                                ""

                            // بعد از ثبت به امروز برگرد
                            viewModel
                                .changeDate(
                                    PersianDateUtil
                                        .today()
                                )
                        }
                    ) {

                        Text(

                            text =
                                if (
                                    editingItem == null
                                ) {

                                    "ثبت خدمت"

                                } else {

                                    "ذخیره تغییرات"
                                }
                        )
                    }

                    // =================================================
                    // لغو ویرایش
                    // =================================================

                    if (
                        editingItem != null
                    ) {

                        Button(

                            modifier =
                                Modifier.fillMaxWidth(),

                            colors =
                                ButtonDefaults
                                    .buttonColors(
                                        containerColor =
                                            Color(0xFF757575)
                                    ),

                            onClick = {

                                editingItem =
                                    null

                                selectedServiceInternet =
                                    null

                                quantity =
                                    1

                                description =
                                    ""

                                viewModel
                                    .changeDate(
                                        PersianDateUtil
                                            .today()
                                    )
                            }
                        ) {

                            Text(
                                text =
                                    "لغو ویرایش"
                            )
                        }
                    }
                }
            }

            // =====================================================
            // درآمد روز
            // =====================================================

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme.colorScheme.surface
                    ),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation =
                            2.dp
                    )
            ) {

                Column(

                    modifier =
                        Modifier.padding(16.dp)
                ) {

                    Text(

                        text =
                            "درآمد روز",

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    Spacer(
                        modifier =
                            Modifier.height(4.dp)
                    )

                    Text(

                        text =
                            "${totalByDate.value.toPersianNumber()} تومان",

                        color =
                            Color(0xFF1B5E20),

                        style =
                            MaterialTheme
                                .typography
                                .headlineMedium
                    )
                }
            }

            // =====================================================
            // عنوان لیست
            // =====================================================

            Text(

                text =
                    "خدمات ثبت شده",

                style =
                    MaterialTheme
                        .typography
                        .titleLarge
            )

            // =====================================================
            // لیست خدمات ثبت شده
            // =====================================================

            Column(

                modifier =
                    Modifier.fillMaxWidth(),

                verticalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {

                servicesByDate.value.forEach { item ->

                        Card(

                            modifier =
                                Modifier.fillMaxWidth(),

                            colors =
                                CardDefaults.cardColors(
                                    containerColor =
                                        MaterialTheme.colorScheme.surface
                                ),

                            elevation =
                                CardDefaults
                                    .cardElevation(
                                        defaultElevation =
                                            1.dp
                                    )
                        ) {

                            Column(

                                modifier =
                                    Modifier.padding(
                                        10.dp
                                    ),

                                verticalArrangement =
                                    Arrangement.spacedBy(2.dp)
                            ) {

                                Text(

                                    text =
                                        item.serviceName,

                                    style =
                                        MaterialTheme
                                            .typography
                                            .bodyLarge
                                )

                                Text(
                                    text =
                                        "تعداد: ${item.quantity}",
                                    style =
                                        MaterialTheme.typography.bodySmall
                                )

                                Text(
                                    text =
                                        "قیمت: ${item.price.toPersianNumber()} تومان",

                                    style =
                                        MaterialTheme.typography.bodySmall
                                )

                                Text(
                                    text =
                                        "جمع: ${item.totalPrice.toPersianNumber()} تومان",

                                    color =
                                        MaterialTheme.colorScheme.primary,

                                    style =
                                        MaterialTheme.typography.bodyMedium
                                )

                                if (
                                    !item.description
                                        .isNullOrBlank()
                                ) {

                                    Text(

                                        text =
                                            "توضیحات: ${item.description}"
                                    )
                                }

                                Spacer(
                                    modifier =
                                        Modifier.height(6.dp)
                                )

                                Row(

                                    modifier =
                                        Modifier.fillMaxWidth()
                                ) {

                                    // =================================
                                    // ویرایش
                                    // =================================

                                    Button(

                                        modifier =
                                            Modifier.weight(1f),

                                        colors =
                                            ButtonDefaults
                                                .buttonColors(
                                                    containerColor =
                                                        Color(
                                                            0xFF0288D1
                                                        )
                                                ),

                                        onClick = {

                                            editingItem =
                                                item

                                            // پیدا کردن سرویس از لیست Room
                                            selectedServiceInternet =
                                                internetService
                                                    .find {
                                                        it.service ==
                                                                item.serviceName
                                                    }

                                            quantity =
                                                item.quantity

                                            description =
                                                item.description
                                                    ?: ""

                                            viewModel
                                                .changeDate(
                                                    item.date
                                                )
                                        }
                                    ) {

                                        Text(
                                            text =
                                                "ویرایش"
                                        )
                                    }

                                    Spacer(
                                        modifier =
                                            Modifier.width(
                                                8.dp
                                            )
                                    )

                                    // =================================
                                    // حذف
                                    // =================================

                                    Button(

                                        modifier =
                                            Modifier.weight(1f),

                                        colors =
                                            ButtonDefaults
                                                .buttonColors(
                                                    containerColor =
                                                        Color(
                                                            0xFFD32F2F
                                                        )
                                                ),

                                        onClick = {

                                            selectedItem =
                                                item

                                            showDeleteDialog =
                                                true
                                        }
                                    ) {

                                        Text(
                                            text =
                                                "حذف"
                                        )
                                    }
                                }
                            }
                        }
                    }
            }
        }

        // =========================================================
        // دیالوگ حذف
        // =========================================================

        if (
            showDeleteDialog
        ) {

            AlertDialog(

                onDismissRequest = {

                    showDeleteDialog =
                        false
                },

                title = {

                    Text(
                        text =
                            "حذف رکورد"
                    )
                },

                text = {

                    Text(
                        text =
                            "آیا مطمئن هستید؟"
                    )
                },

                confirmButton = {

                    Button(

                        onClick = {

                            selectedItem
                                ?.let { item ->

                                    viewModel
                                        .deleteNetData(
                                            item
                                        )
                                }

                            showDeleteDialog =
                                false
                        }
                    ) {

                        Text(
                            text =
                                "بله"
                        )
                    }
                },

                dismissButton = {

                    Button(

                        onClick = {

                            showDeleteDialog =
                                false
                        }
                    ) {

                        Text(
                            text =
                                "خیر"
                        )
                    }
                }
            )
        }
    }
}
package com.example.myapplication.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.domain.model.InternetServiceData
import com.example.myapplication.presentation.screen.print.PrintScreen
import com.example.myapplication.presentation.screen.stationery.Lavazem
import com.example.myapplication.presentation.screen.stationery.LowStockScreen
import com.example.myapplication.presentation.screen.stationery.SalesReportScreen
import com.example.myapplication.presentation.screen.stationery.product.ProductScreen
import com.example.myapplication.presentation.screen.stationery.sale.SaleScreen
import com.example.myapplication.presentation.splash.SplashScreen
import com.example.myapplication.presentation.ui.theme.Primary
import com.example.myapplication.presentation.viewModel.NetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun AppScaffold(
    navController: NavHostController,
    darkTheme: Boolean,
    onThemeChange: () -> Unit
) {

    val currentRoute =
        navController
            .currentBackStackEntryAsState()
            .value
            ?.destination
            ?.route

    val isSplash = currentRoute == "splash"

    var menuExpanded by remember {
        mutableStateOf(false)
    }

    var showAddInternetServiceDialog by remember {
        mutableStateOf(false)
    }

    // جدید
    var showDeveloperDialog by remember {
        mutableStateOf(false)
    }

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {

        Scaffold(

            topBar = {
                if (!isSplash){
                    TopAppBar(

                        colors =
                            TopAppBarDefaults.topAppBarColors(
                                containerColor = Primary,
                                titleContentColor =  MaterialTheme.colorScheme.onPrimary,
                                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                            ),

                        title = {

                            Text(
                                text = "مدیریت خدمات",

                                modifier =
                                    Modifier.fillMaxWidth(),

                                textAlign =
                                    TextAlign.Right
                            )
                        },

                        navigationIcon = {

                            IconButton(
                                onClick = {
                                    menuExpanded = !menuExpanded
                                }
                            ) {

                                Icon(
                                    imageVector =
                                        Icons.Default.Menu,

                                    contentDescription =
                                        "منو"
                                )
                            }

                            DropdownMenu(

                                expanded =
                                    menuExpanded,

                                onDismissRequest = {
                                    menuExpanded = false
                                }
                            ) {

                                // فقط در بخش اینترنت
                                if (currentRoute == "net") {

                                    DropdownMenuItem(

                                        text = {

                                            Text(
                                                text =
                                                    "افزودن خدمات اینترنتی"
                                            )
                                        },

                                        onClick = {

                                            menuExpanded = false

                                            showAddInternetServiceDialog =
                                                true
                                        }
                                    )

                                    HorizontalDivider()
                                }

                                // جدید
                                DropdownMenuItem(

                                    text = {

                                        Text(
                                            text =
                                                "درباره برنامه‌نویس"
                                        )
                                    },

                                    onClick = {

                                        menuExpanded = false

                                        showDeveloperDialog = true
                                    }
                                )
                            }
                        },
                        actions = {

                            IconButton(
                                onClick = {
                                    onThemeChange()
                                }
                            ) {

                                Icon(

                                    imageVector = if (darkTheme) {
                                        Icons.Default.LightMode
                                    } else {
                                        Icons.Default.DarkMode
                                    },

                                    contentDescription = if (darkTheme) {
                                        "حالت روشن"
                                    } else {
                                        "حالت تاریک"
                                    },

                                    tint = Color.White
                                )
                            }
                        }
                    )

                }

            },

            bottomBar = {
                if (!isSplash){
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface
                    ) {

                        NavigationBarItem(

                            selected =
                                currentRoute == "net",

                            colors =
                                NavigationBarItemDefaults.colors(
                                    selectedIconColor =
                                        Color(0xFF0F766E),

                                    selectedTextColor =
                                        Color(0xFF0F766E),

                                    indicatorColor =
                                        Color(0xFFE6FFFA)
                                ),

                            icon = {

                                Icon(
                                    imageVector =
                                        Icons.Default.Language,

                                    contentDescription =
                                        "Internet"
                                )
                            },

                            label = {
                                Text("اینترنت")
                            },

                            onClick = {

                                navController.navigate("net") {

                                    popUpTo(
                                        navController.graph.startDestinationId
                                    ) {
                                        saveState = true
                                    }

                                    launchSingleTop = true
                                }
                            }
                        )

                        NavigationBarItem(

                            selected =
                                currentRoute == "printer",

                            colors =
                                NavigationBarItemDefaults.colors(
                                    selectedIconColor =
                                        Color(0xFF0F766E),

                                    selectedTextColor =
                                        Color(0xFF0F766E),

                                    indicatorColor =
                                        Color(0xFFE6FFFA)
                                ),

                            icon = {

                                Icon(
                                    imageVector =
                                        Icons.Default.Print,

                                    contentDescription =
                                        "پرینت"
                                )
                            },

                            label = {
                                Text("چاپ و فتوکپی")
                            },

                            onClick = {

                                navController.navigate("printer") {

                                    popUpTo(
                                        navController.graph.startDestinationId
                                    ) {
                                        saveState = true
                                    }

                                    launchSingleTop = true
                                }
                            }
                        )

                        NavigationBarItem(

                            selected =
                                currentRoute == "lavazem",

                            colors =
                                NavigationBarItemDefaults.colors(
                                    selectedIconColor =
                                        Color(0xFF0F766E),

                                    selectedTextColor =
                                        Color(0xFF0F766E),

                                    indicatorColor =
                                        Color(0xFFE6FFFA)
                                ),

                            icon = {

                                Icon(
                                    imageVector =
                                        Icons.Default.ShoppingCart,

                                    contentDescription =
                                        "لوازم التحریر"
                                )
                            },

                            label = {
                                Text("لوازم التحریر")
                            },

                            onClick = {

                                navController.navigate("lavazem") {

                                    popUpTo(
                                        navController.graph.startDestinationId
                                    ) {
                                        saveState = true
                                    }

                                    launchSingleTop = true
                                }
                            }
                        )
                    }

                }


            }
        ) { innerPadding ->

            NavHost(

                navController = navController,

                startDestination = "splash",

                modifier = Modifier.padding(innerPadding)
            ) {

                composable("splash") {

                    SplashScreen(

                        onSplashFinished = {

                            navController.navigate("net") {

                                popUpTo("splash") {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

                composable("net") {

                    InternetScreen(
                        navController = navController
                    )
                }

                composable("printer") {

                    PrintScreen()
                }

                composable("lavazem") {

                    Lavazem(
                        navController = navController
                    )
                }

                composable("monthly_report") {

                    MonthlyReportScreen(
                        navController = navController
                    )
                }

                composable("product") {

                    ProductScreen()
                }

                composable("sale") {

                    SaleScreen()
                }

                composable("sales_report") {

                    SalesReportScreen()
                }

                composable("low_stock") {

                    LowStockScreen()
                }
            }
        }
    }

    // دیالوگ افزودن خدمت اینترنتی
    if (showAddInternetServiceDialog) {

        AddInternetServiceDialog(

            onDismiss = {
                showAddInternetServiceDialog = false
            }
        )
    }

    // دیالوگ معرفی برنامه‌نویس
    if (showDeveloperDialog) {

        DeveloperInfoDialog(

            onDismiss = {
                showDeveloperDialog = false
            }
        )
    }
}


@Composable
fun AddInternetServiceDialog(
    onDismiss: () -> Unit,
    viewModel: NetViewModel = hiltViewModel()
) {

    var serviceName by remember {
        mutableStateOf("")
    }

    var price by remember {
        mutableStateOf("")
    }

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {

            Text(
                text = "افزودن خدمات اینترنتی"
            )
        },

        text = {

            Column(

                verticalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                androidx.compose.material3.OutlinedTextField(

                    value = serviceName,

                    onValueChange = {
                        serviceName = it
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    singleLine = true,

                    label = {
                        Text(
                            text = "عنوان خدمت"
                        )
                    }
                )

                androidx.compose.material3.OutlinedTextField(

                    value = price,

                    onValueChange = {

                        if (
                            it.all { char ->
                                char.isDigit()
                            }
                        ) {
                            price = it
                        }
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    singleLine = true,

                    label = {
                        Text(
                            text = "قیمت"
                        )
                    }
                )
            }
        },

        confirmButton = {

            TextButton(

                onClick = {

                    if (
                        serviceName.isBlank() ||
                        price.isBlank()
                    ) {
                        return@TextButton
                    }

                    viewModel.upsertInternetService(

                        InternetServiceData(

                            service = serviceName,

                            price = price.toInt()
                        )
                    )

                    onDismiss()
                }
            ) {

                Text(
                    text = "ثبت"
                )
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text(
                    text = "لغو"
                )
            }
        }
    )
}


// =====================================================
// معرفی برنامه‌نویس
// =====================================================

@SuppressLint("UseKtx")
@Composable
fun DeveloperInfoDialog(
    onDismiss: () -> Unit
) {

    val context = LocalContext.current

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "درباره برنامه‌نویس",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "همانگار",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },

        text = {

            Column(

                modifier =
                    Modifier.fillMaxWidth(),

                horizontalAlignment =
                    Alignment.CenterHorizontally,

                verticalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "علیرضا حلوایی فرد",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "توسعه‌دهنده نرم‌افزار و برنامه‌نویس اندروید",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                HorizontalDivider()

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "ایمیل",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "halvaei@gmail.com",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "شماره تماس",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "09360896001",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Button(

                        onClick = {

                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://github.com/android24h")
                            )

                            context.startActivity(intent)
                        }
                    ) {

                        Text(
                            text = "GitHub"
                        )
                    }

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Button(

                        onClick = {

                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(
                                    "https://www.linkedin.com/in/alirezahalvaei/"
                                )
                            )

                            context.startActivity(intent)
                        }
                    ) {

                        Text(
                            text = "LinkedIn"
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "نسخه 1",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
            }
        },

        confirmButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text(
                    text = "بستن"
                )
            }
        }
    )
}
package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.ui.AppScaffold

@Composable
fun NavGraph(
    darkTheme: Boolean,
    onThemeChange: () -> Unit
) {

    val navController = rememberNavController()

    AppScaffold(
        navController = navController,
        darkTheme = darkTheme,
        onThemeChange = onThemeChange
    )
}
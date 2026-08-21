package com.example.myapplication.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(

    primary = Primary,

    secondary = Secondary,

    background = Background,

    surface = CardBackground,

    onPrimary = CardBackground,

    onSecondary = CardBackground,

    onBackground = TextDark,

    onSurface = TextDark
)

private val DarkColorScheme = darkColorScheme(

    primary = PrimaryLight,

    secondary = Secondary,

    background = DarkBackground,

    surface = DarkCardBackground,

    onPrimary = DarkText,

    onSecondary = DarkText,

    onBackground = DarkText,

    onSurface = DarkText
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
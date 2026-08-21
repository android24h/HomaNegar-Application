package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myapplication.navigation.NavGraph
import com.example.myapplication.presentation.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            var darkTheme by rememberSaveable {
                mutableStateOf(false)
            }

            MyApplicationTheme(
                darkTheme = darkTheme
            ) {

                NavGraph(
                    darkTheme = darkTheme,
                    onThemeChange = {
                        darkTheme = !darkTheme
                    }
                )
            }
        }
    }
}
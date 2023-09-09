package com.example.valorant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.valorant.ui.navigation.Navigation
import com.example.valorant.ui.screen.HomeScreen
import com.example.valorant.ui.theme.ValorantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantTheme {
              Navigation()
            }
        }
    }
}
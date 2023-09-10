package com.example.valorant.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.valorant.ui.screen.HomeScreen
import com.example.valorant.ui.screen.SplashScreen
import com.example.valorant.ui.screen.detail.DetailAgents
import com.example.valorant.ui.screen.detail.DetailWeapon
import com.example.valorant.ui.viewmodel.HomeViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: HomeViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash"){
            SplashScreen(navHostController = navController)
        }
        composable("home") {
            HomeScreen(viewModel = viewModel, navHostController = navController)
        }
        composable("detail_agent") {
            DetailAgents(viewModel = viewModel, navHostController = navController)
        }
        composable("detail_weapon"){
            DetailWeapon(viewModel = viewModel, navHostController = navController )
        }
    }
}
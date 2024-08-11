package com.shivayogi.galaxygaze.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shivayogi.galaxygaze.ui.astronomy.screens.ApodScreen


@Composable
fun NavGraph(startDestination: String = "apod_screen") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("apod_screen") {
            ApodScreen()
        }
    }
}
package com.example.asist_derm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.asist_derm.ui.theme.auth.HomeScreen
import com.example.asist_derm.ui.theme.auth.WelcomeScreen

@Composable
fun NavGraph(){
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("home") { HomeScreen() }
    }
}
package com.example.asist_derm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.asist_derm.ui.theme.auth.ActionsScreen
import com.example.asist_derm.ui.theme.auth.CameraScreen
import com.example.asist_derm.ui.theme.auth.HomeScreen
import com.example.asist_derm.ui.theme.auth.LoginScreen
import com.example.asist_derm.ui.theme.auth.RegisterScreen
import com.example.asist_derm.ui.theme.auth.WelcomeScreen

@Composable
fun NavGraph(){
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("actions") { ActionsScreen(navController) }
        composable("camera") { CameraScreen(onBack = { navController.popBackStack() },
            onCapture = {
            }) }
    }
}
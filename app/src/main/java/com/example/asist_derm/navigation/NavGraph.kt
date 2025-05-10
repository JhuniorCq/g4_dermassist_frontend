package com.example.asist_derm.navigation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.asist_derm.ui.theme.auth.ActionsScreen
import com.example.asist_derm.ui.theme.auth.CameraScreen
import com.example.asist_derm.ui.theme.auth.ClinicsScreen
import com.example.asist_derm.ui.theme.auth.HistoryScreen
import com.example.asist_derm.ui.theme.auth.HomeScreen
import com.example.asist_derm.ui.theme.auth.LoginScreen
import com.example.asist_derm.ui.theme.auth.PredictScreen
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
            onCapture = {uri ->
                Log.d("CameraScreen", "Navegando a PredictScreen con URI: $uri")
                navController.navigate("predict/${Uri.encode(uri.toString())}")
            })
        }
        composable("predict/{uri}") { backStackEntry ->
            val uriString = backStackEntry.arguments?.getString("uri")
            val uri = Uri.parse(uriString)
            PredictScreen(navController = navController
                , uri = uri, onBack = { navController.popBackStack() })
        }
        composable("clinics") { ClinicsScreen(navController) }
        composable("history") { HistoryScreen(navController) }

    }
}
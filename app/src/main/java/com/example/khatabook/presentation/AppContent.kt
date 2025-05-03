package com.example.khatabook.presentation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.khatabook.presentation.login.LoginScreen
import com.example.khatabook.presentation.navigation.Screen
import com.example.khatabook.presentation.ui.HomeScreen
import com.example.khatabook.presentation.ui.ImageScreen
import com.example.khatabook.presentation.ui.ReportScreen
import com.example.khatabook.presentation.ui.SettingsScreen
import com.example.khatabook.presentation.viewmodal.UserViewModel

@Composable
fun AppContent(viewModel: UserViewModel = hiltViewModel()) {
    val user by viewModel.user.collectAsState(initial = null)
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (user == null) Screen.Login.route else Screen.Home.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(viewModel) {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToImage = { navController.navigate(Screen.Gallery.route) },
                onNavigateToGallery = { navController.navigate(Screen.Gallery.route) },
                onNavigateToPDF = { navController.navigate(Screen.PDF.route) },
                onNavigateToSettings = { navController.navigate(Screen.FCM.route) }
            )
        }
        composable(Screen.Gallery.route) {
            ImageScreen(onNext = {
                navController.popBackStack() // Go back to Home after image selection
            })
        }
        composable(Screen.PDF.route) {
            ReportScreen(onNext = {
                navController.popBackStack()
            })
        }
        composable(Screen.FCM.route) {
            SettingsScreen(onNext = {
                navController.popBackStack()
            })
        }
    }
}


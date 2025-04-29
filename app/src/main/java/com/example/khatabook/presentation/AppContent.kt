package com.example.khatabook.presentation

import SettingsScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.khatabook.presentation.login.LoginScreen
import com.example.khatabook.presentation.navigation.Screen
import com.example.khatabook.presentation.ui.ImageScreen
import com.example.khatabook.presentation.ui.ReportScreen
import com.example.khatabook.presentation.viewmodal.UserViewModel

@Composable
fun AppContent(viewModel: UserViewModel = hiltViewModel()) {
    val user by viewModel.user.collectAsState(initial = null)
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (user == null) Screen.Login.route else Screen.Gallery.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(viewModel) {
                navController.navigate(Screen.Gallery.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
        }
        composable(Screen.Gallery.route) {
            ImageScreen(onNext = {
                navController.navigate(Screen.PDF.route)
            })
        }
        composable(Screen.PDF.route) {
            ReportScreen(onNext = {
                navController.navigate(Screen.FCM.route)
            })
        }
        composable(Screen.FCM.route) {
            SettingsScreen()
        }
    }
}


package com.example.khatabook.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Gallery : Screen("gallery")
    object PDF : Screen("pdf")
    object FCM : Screen("fcm")
}
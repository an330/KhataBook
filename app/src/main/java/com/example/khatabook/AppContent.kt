package com.example.khatabook

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khatabook.presentation.login.LoginScreen

@Composable
fun AppContent(viewModel: UserViewModel = hiltViewModel()) {
    LoginScreen(viewModel)
}

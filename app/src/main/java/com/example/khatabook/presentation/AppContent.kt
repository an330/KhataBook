package com.example.khatabook.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khatabook.presentation.login.LoginScreen
import com.example.khatabook.presentation.viewmodal.UserViewModel

@Composable
fun AppContent(viewModel: UserViewModel = hiltViewModel()) {
    LoginScreen(viewModel)
}

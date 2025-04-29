package com.example.khatabook.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khatabook.presentation.login.LoginScreen
import com.example.khatabook.presentation.ui.ImageScreen
import com.example.khatabook.presentation.ui.ReportScreen
import com.example.khatabook.presentation.ui.SettingsScreen
import com.example.khatabook.presentation.viewmodal.UserViewModel

@Composable
fun AppContent(viewModel: UserViewModel = hiltViewModel()) {
    val user by viewModel.user.collectAsState(initial = null)

    if (user != null) {
        //ImageScreen() // Show PDF Screen
        SettingsScreen()
    } else {
        LoginScreen(viewModel)

    }
}


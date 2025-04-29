package com.example.khatabook.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khatabook.presentation.viewmodal.SettingsViewModel

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val isEnabled by viewModel.isNotificationEnabled.collectAsState(initial = true)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Enable Notifications",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.width(16.dp))
            Switch(
                checked = isEnabled,
                onCheckedChange = { viewModel.toggleNotifications(it) }
            )
        }
    }
}

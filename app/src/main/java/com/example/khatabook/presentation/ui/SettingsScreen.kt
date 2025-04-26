package com.example.khatabook.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
            .padding(16.dp)
    ) {
        Text(text = "Settings", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Enable Notifications")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isEnabled,
                onCheckedChange = { viewModel.toggleNotification(it) }
            )
        }
    }
}
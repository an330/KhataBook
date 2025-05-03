package com.example.khatabook.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToImage: () -> Unit,
    onNavigateToGallery: () -> Unit,
    onNavigateToPDF: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onNavigateToImage) {
            Text("Capture Image")
        }
        Button(onClick = onNavigateToGallery) {
            Text("Select from Gallery")
        }
        Button(onClick = onNavigateToPDF) {
            Text("Download/View PDF")
        }
        Button(onClick = onNavigateToSettings) {
            Text("Notification Settings")
        }
    }
}
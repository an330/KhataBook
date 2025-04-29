package com.example.khatabook.presentation.ui

import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import android.Manifest
import android.os.Environment
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khatabook.presentation.viewmodal.ImageViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImageScreen(viewModel: ImageViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val imageUri by viewModel.imageUri.collectAsState()
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val tempImageUri = remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.setImageUri(it) }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            tempImageUri.value?.let { viewModel.setImageUri(it) }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                if (permissionState.status.isGranted) {
                    val imageFile = File(context.cacheDir, "camera_image.jpg")
                    val uri = FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.provider",
                        imageFile
                    )
                    tempImageUri.value = uri
                    cameraLauncher.launch(uri)
                } else {
                    permissionState.launchPermissionRequest()
                }
            }) {
                Text("Capture Image")
            }

            Button(onClick = { galleryLauncher.launch("image/*") }) {
                Text("Select from Gallery")
            }

            Button(
                onClick = {
                    imageUri?.let {
                        // Add your desired action here
                    }
                },
                enabled = imageUri != null
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
    }
}

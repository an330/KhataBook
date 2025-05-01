package com.example.khatabook.presentation.ui



import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.example.khatabook.utils.downloadPdf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun ReportScreen(onNext: () -> Unit) {
    val context = LocalContext.current
    val pdfUrl = "https://fssservices.bookxpert.co/GeneratedPDF/Companies/nadc/2024-2025/BalanceSheet.pdf"
    var downloadedFile by remember { mutableStateOf<java.io.File?>(null) }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Button(onClick = {
            downloadPdf(context, pdfUrl) { file ->
                downloadedFile = file
                file?.let {
                    val uri = FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.provider",
                        it
                    )
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        setDataAndType(uri, "application/pdf")
                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }
                    try {
                        context.startActivity(intent)
                        onNext() // Navigate after opening the PDF
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }) {
            Text("Open PDF Report")
        }

        Button(
            onClick = {
                // Optional: trigger navigation on Add instead
                onNext()
            },
            enabled = downloadedFile != null
        ) {
            Text("Add")
        }
    }
}


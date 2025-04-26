package com.example.khatabook.presentation.ui



import android.content.Intent
import android.net.Uri
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.example.khatabook.utils.downloadPdf

@Composable
fun ReportScreen() {
    val context = LocalContext.current
    val pdfUrl = "https://fssservices.bookxpert.co/GeneratedPDF/Companies/nadc/2024-2025/BalanceSheet.pdf"

    Button(onClick = {
        downloadPdf(context, pdfUrl) { file ->
            file?.let {
                val uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider", // Defined in Manifest
                    it
                )
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(uri, "application/pdf")
                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                try {
                    context.startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }) {
        Text("Open PDF Report")
    }
}


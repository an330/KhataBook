package com.example.khatabook.utils

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

fun downloadPdf(context: Context, url: String, onDownloaded: (File?) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.connect()
            val inputStream = connection.inputStream
            val file = File(context.cacheDir, "report.pdf")
            val outputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()
            withContext(Dispatchers.Main) {
                onDownloaded(file)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                onDownloaded(null)
            }
        }
    }
}
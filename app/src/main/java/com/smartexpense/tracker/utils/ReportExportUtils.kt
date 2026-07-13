package com.smartexpense.tracker.utils

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import com.smartexpense.tracker.data.local.entity.ReportGroupSum
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object ReportExportUtils {

    fun exportToCsv(context: Context, data: List<ReportGroupSum>, title: String, share: Boolean = false): String {
        return try {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val fileName = "${title.replace(" ", "_")}_$timestamp.csv"
            
            val dir = if (share) context.cacheDir else Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(dir, fileName)
            
            val writer = FileWriter(file)
            writer.append("Group,Total Amount\n")
            data.forEach { item ->
                val group = item.groupName?.replace(",", " ") ?: "Unknown"
                writer.append("$group,${item.totalAmount}\n")
            }
            writer.flush()
            writer.close()
            
            if (share) {
                shareFile(context, file, "text/csv")
                "Shared CSV Report"
            } else {
                "Successfully exported to Downloads folder!"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Export failed: ${e.message}"
        }
    }

    fun exportToPdf(context: Context, data: List<ReportGroupSum>, title: String, share: Boolean = false): String {
        return try {
            val document = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4 Size
            val page = document.startPage(pageInfo)
            val canvas: Canvas = page.canvas
            
            val titlePaint = Paint().apply {
                color = Color.BLACK
                textSize = 24f
                isFakeBoldText = true
            }
            val textPaint = Paint().apply {
                color = Color.BLACK
                textSize = 16f
            }
            
            canvas.drawText(title, 50f, 50f, titlePaint)
            
            var yOffset = 100f
            canvas.drawText("Group", 50f, yOffset, titlePaint)
            canvas.drawText("Amount", 400f, yOffset, titlePaint)
            
            yOffset += 40f
            
            data.forEach { item ->
                val group = item.groupName ?: "Unknown"
                val amount = String.format(Locale.getDefault(), "%.2f", item.totalAmount)
                
                canvas.drawText(group, 50f, yOffset, textPaint)
                canvas.drawText(amount, 400f, yOffset, textPaint)
                yOffset += 30f
            }
            
            document.finishPage(page)
            
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val fileName = "${title.replace(" ", "_")}_$timestamp.pdf"
            
            val dir = if (share) context.cacheDir else Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(dir, fileName)
            
            document.writeTo(FileOutputStream(file))
            document.close()
            
            if (share) {
                shareFile(context, file, "application/pdf")
                "Shared PDF Report"
            } else {
                "Successfully exported PDF to Downloads folder!"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Export failed: ${e.message}"
        }
    }
    
    private fun shareFile(context: Context, file: File, mimeType: String) {
        val uri: Uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = mimeType
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(intent, "Share Report"))
    }
}

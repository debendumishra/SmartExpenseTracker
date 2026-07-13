package com.smartexpense.tracker.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import com.smartexpense.tracker.data.local.entity.ExportExpenseDTO
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object ExportHelper {

    fun exportExpensesToCsv(context: Context, expenses: List<ExportExpenseDTO>): Uri? {
        val fileName = "expenses_export_${System.currentTimeMillis()}.csv"
        val file = File(context.cacheDir, fileName)

        try {
            val writer = FileWriter(file)
            
            // Write CSV Header
            writer.append("ID,Date,Amount,Category,Payment Mode,Merchant,Bank Name,Mode Name,Latitude,Longitude,City,Address,State,Notes,Source,Paid By\n")
            
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            
            expenses.forEach { expense ->
                val dateStr = dateFormat.format(Date(expense.timestamp))
                
                // Escape commas in strings
                val category = escapeCsv(expense.purpose ?: "")
                val paymentMode = escapeCsv(expense.paymentMode)
                val merchant = escapeCsv(expense.merchant ?: "")
                val bankName = escapeCsv(expense.bankName ?: "")
                val modeName = escapeCsv(expense.modeName ?: "")
                val city = escapeCsv(expense.city ?: "")
                val address = escapeCsv(expense.address ?: "")
                val state = escapeCsv(expense.state ?: "")
                val notes = escapeCsv(expense.notes ?: "")
                val source = escapeCsv(expense.source)
                val paidBy = escapeCsv(expense.paidBy ?: "")
                
                writer.append("${expense.id},$dateStr,${expense.amount},$category,$paymentMode,$merchant,$bankName,$modeName,${expense.latitude ?: ""},${expense.longitude ?: ""},$city,$address,$state,$notes,$source,$paidBy\n")
            }
            
            writer.flush()
            writer.close()
            
            return FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
    
    private fun escapeCsv(value: String): String {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"${value.replace("\"", "\"\"")}\""
        }
        return value
    }
    
    fun shareCsvFile(context: Context, uri: Uri) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/csv"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share Expenses CSV"))
    }
}

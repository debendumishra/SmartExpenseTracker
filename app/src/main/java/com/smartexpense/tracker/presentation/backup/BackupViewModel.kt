package com.smartexpense.tracker.presentation.backup

import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartexpense.tracker.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class BackupViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val _exportStatus = MutableStateFlow<String?>(null)
    val exportStatus: StateFlow<String?> = _exportStatus.asStateFlow()

    fun exportToCsv() {
        viewModelScope.launch {
            _exportStatus.value = "Exporting..."
            try {
                val expenses = expenseRepository.getAllExpenses().first()
                if (expenses.isEmpty()) {
                    _exportStatus.value = "No expenses to export."
                    return@launch
                }

                val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
                val file = File(downloadsDir, "SmartExpense_Backup_$timestamp.csv")

                val writer = FileWriter(file)
                
                // CSV Header
                writer.append("ID,Date,Amount,Category,Payment Mode,Merchant,Bank ID,Mode ID,Latitude,Longitude,City,Address,State,Notes,Source,Paid By\n")

                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                
                expenses.forEach { exp ->
                    val dateStr = dateFormat.format(Date(exp.timestamp))
                    
                    val amount = exp.amount
                    val category = exp.purpose?.replace(",", " ") ?: ""
                    val mode = exp.paymentMode.replace(",", " ")
                    val merchant = exp.merchant?.replace(",", " ") ?: ""
                    val bankId = exp.bankId?.toString() ?: ""
                    val modeId = exp.expenseModeId?.toString() ?: ""
                    val lat = exp.latitude?.toString() ?: ""
                    val lng = exp.longitude?.toString() ?: ""
                    val city = exp.city?.replace(",", " ") ?: ""
                    val address = exp.address?.replace(",", " ")?.replace("\n", " ") ?: ""
                    val state = exp.state?.replace(",", " ") ?: ""
                    val notes = exp.notes?.replace(",", " ")?.replace("\n", " ") ?: ""
                    val source = exp.source.replace(",", " ")
                    val paidBy = exp.paidBy?.replace(",", " ") ?: ""
                    
                    writer.append("${exp.id},$dateStr,$amount,$category,$mode,$merchant,$bankId,$modeId,$lat,$lng,$city,$address,$state,$notes,$source,$paidBy\n")
                }
                
                writer.flush()
                writer.close()
                
                _exportStatus.value = "Successfully exported to Downloads folder!"
            } catch (e: Exception) {
                e.printStackTrace()
                _exportStatus.value = "Export failed: ${e.message}"
            }
        }
    }

    fun clearStatus() {
        _exportStatus.value = null
    }
}

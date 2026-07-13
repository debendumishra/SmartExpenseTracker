package com.smartexpense.tracker.presentation.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartexpense.tracker.data.local.entity.ReportGroupSum
import com.smartexpense.tracker.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.Calendar
import javax.inject.Inject

enum class ReportGrouping {
    PURPOSE, CATEGORY, MERCHANT, PAYMENT_MODE, LOCATION, EXPENSE_MODE, BANK
}

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val _fromDate = MutableStateFlow(getStartOfMonth())
    val fromDate: StateFlow<Long> = _fromDate.asStateFlow()

    private val _toDate = MutableStateFlow(System.currentTimeMillis())
    val toDate: StateFlow<Long> = _toDate.asStateFlow()

    private val _grouping = MutableStateFlow(ReportGrouping.CATEGORY)
    val grouping: StateFlow<ReportGrouping> = _grouping.asStateFlow()

    fun setFromDate(date: Long) {
        _fromDate.value = date
    }

    fun setToDate(date: Long) {
        _toDate.value = date
    }

    fun setGrouping(group: ReportGrouping) {
        _grouping.value = group
    }

    val reportData: StateFlow<List<ReportGroupSum>> = kotlinx.coroutines.flow.combine(_fromDate, _toDate, _grouping) { start, end, group ->
        Triple(start, end, group)
    }.flatMapLatest { (start, end, group) ->
        when (group) {
            ReportGrouping.PURPOSE -> expenseRepository.getReportByPurpose(start, end)
            ReportGrouping.CATEGORY -> expenseRepository.getReportByCategory(start, end)
            ReportGrouping.MERCHANT -> expenseRepository.getReportByMerchant(start, end)
            ReportGrouping.PAYMENT_MODE -> expenseRepository.getReportByPaymentMode(start, end)
            ReportGrouping.LOCATION -> expenseRepository.getReportByLocation(start, end)
            ReportGrouping.EXPENSE_MODE -> expenseRepository.getReportByExpenseMode(start, end)
            ReportGrouping.BANK -> expenseRepository.getReportByBank(start, end)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val rawExpenses: StateFlow<List<com.smartexpense.tracker.data.local.entity.ExportExpenseDTO>> = kotlinx.coroutines.flow.combine(_fromDate, _toDate) { start, end ->
        Pair(start, end)
    }.flatMapLatest { (start, end) ->
        expenseRepository.getExpensesForExport(start, end)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val totalSpent: StateFlow<Double> = reportData
        .map { list -> list.sumOf { it.totalAmount } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    private fun getStartOfMonth(): Long {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }
}

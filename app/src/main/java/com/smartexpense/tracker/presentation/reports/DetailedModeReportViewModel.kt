package com.smartexpense.tracker.presentation.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartexpense.tracker.data.local.entity.DetailedModeExpense
import com.smartexpense.tracker.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailedModeReportViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    val detailedModeReportData: StateFlow<Map<String, List<DetailedModeExpense>>> =
        expenseRepository.getDetailedModeExpenses()
            .map { list -> 
                list.groupBy { it.modeName ?: "General" }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyMap()
            )

    val rawExpenses: StateFlow<List<com.smartexpense.tracker.data.local.entity.ExportExpenseDTO>> = 
        expenseRepository.getExpensesForExport(0L, Long.MAX_VALUE)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
}

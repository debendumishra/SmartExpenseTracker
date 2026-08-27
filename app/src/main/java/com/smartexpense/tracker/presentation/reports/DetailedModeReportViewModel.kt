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

import androidx.lifecycle.SavedStateHandle

@HiltViewModel
class DetailedModeReportViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val startDate: Long = savedStateHandle.get<Long>("startDate") ?: 0L
    private val endDate: Long = savedStateHandle.get<Long>("endDate") ?: Long.MAX_VALUE

    val detailedModeReportData: StateFlow<Map<String, List<DetailedModeExpense>>> =
        expenseRepository.getDetailedModeExpenses(startDate, endDate)
            .map { list -> 
                list.groupBy { it.modeName ?: "General" }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyMap()
            )

    val rawExpenses: StateFlow<List<com.smartexpense.tracker.data.local.entity.ExportExpenseDTO>> = 
        expenseRepository.getExpensesForExport(startDate, endDate)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
}

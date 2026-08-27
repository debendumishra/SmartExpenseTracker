package com.smartexpense.tracker.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartexpense.tracker.data.local.entity.ExpenseEntity
import com.smartexpense.tracker.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.smartexpense.tracker.domain.repository.ExpenseModeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.Calendar

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    private val expenseModeRepository: ExpenseModeRepository
) : ViewModel() {

    val activeMode = expenseModeRepository.getActiveMode()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val recentExpenses: StateFlow<List<ExpenseEntity>> = expenseModeRepository.getActiveMode()
        .flatMapLatest { activeMode ->
            if (activeMode != null) {
                expenseRepository.getExpensesByMode(activeMode.id)
            } else {
                expenseRepository.getAllExpenses()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _totalSpent = MutableStateFlow(0.0)
    val totalSpent: StateFlow<Double> = _totalSpent.asStateFlow()

    private val _categoryData = MutableStateFlow<List<Pair<String, Float>>>(emptyList())
    val categoryData: StateFlow<List<Pair<String, Float>>> = _categoryData.asStateFlow()

    init {
        viewModelScope.launch {
            recentExpenses.collect { expenses ->
                // Filter for current month
                val calendar = Calendar.getInstance()
                val currentMonth = calendar.get(Calendar.MONTH)
                val currentYear = calendar.get(Calendar.YEAR)
                
                val currentMonthExpenses = expenses.filter { expense ->
                    val expenseCalendar = Calendar.getInstance()
                    expenseCalendar.timeInMillis = expense.timestamp
                    expenseCalendar.get(Calendar.MONTH) == currentMonth &&
                    expenseCalendar.get(Calendar.YEAR) == currentYear
                }

                _totalSpent.value = currentMonthExpenses.sumOf { it.amount }
                // Calculate totals by category for the pie chart using current month expenses
                val categoryMap = mutableMapOf<String, Float>()
                currentMonthExpenses.forEach { expense ->
                    val cat = expense.purpose ?: "Other"
                    categoryMap[cat] = categoryMap.getOrDefault(cat, 0f) + expense.amount.toFloat()
                }
                _categoryData.value = categoryMap.toList().sortedByDescending { it.second }
            }
        }
    }
}

package com.smartexpense.tracker.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartexpense.tracker.data.local.entity.ExpenseModeEntity
import com.smartexpense.tracker.domain.repository.ExpenseModeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseModeViewModel @Inject constructor(
    private val modeRepository: ExpenseModeRepository
) : ViewModel() {

    val activeMode: StateFlow<ExpenseModeEntity?> = modeRepository.getActiveMode()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val allModes: StateFlow<List<ExpenseModeEntity>> = modeRepository.getAllModes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _newModeName = MutableStateFlow("")
    val newModeName: StateFlow<String> = _newModeName.asStateFlow()

    fun updateNewModeName(name: String) {
        _newModeName.value = name
    }

    fun createNewMode() {
        val name = _newModeName.value.trim()
        if (name.isNotEmpty()) {
            viewModelScope.launch {
                modeRepository.deactivateCurrentMode(System.currentTimeMillis())
                modeRepository.insertMode(ExpenseModeEntity(
                    name = name, 
                    isActive = true,
                    createdAt = System.currentTimeMillis(),
                    endedAt = null
                ))
                _newModeName.value = "" // clear input
            }
        }
    }

    fun deleteExpenseMode(mode: ExpenseModeEntity) {
        viewModelScope.launch {
            modeRepository.deleteModeWithExpenses(mode)
        }
    }

    fun reenableMode(mode: ExpenseModeEntity) {
        viewModelScope.launch {
            modeRepository.deactivateCurrentMode(System.currentTimeMillis())
            modeRepository.updateMode(mode.copy(isActive = true, endedAt = null))
        }
    }

    fun deactivateCurrentMode() {
        viewModelScope.launch {
            modeRepository.deactivateCurrentMode(System.currentTimeMillis())
        }
    }
}

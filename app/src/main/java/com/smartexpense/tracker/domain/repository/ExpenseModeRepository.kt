package com.smartexpense.tracker.domain.repository

import com.smartexpense.tracker.data.local.entity.ExpenseModeEntity
import kotlinx.coroutines.flow.Flow

interface ExpenseModeRepository {
    suspend fun insertMode(mode: ExpenseModeEntity): Long
    suspend fun updateMode(mode: ExpenseModeEntity)
    suspend fun deleteModeWithExpenses(mode: ExpenseModeEntity)

    suspend fun deactivateCurrentMode(endTime: Long)
    fun getActiveMode(): Flow<ExpenseModeEntity?>
    fun getAllModes(): Flow<List<ExpenseModeEntity>>
}

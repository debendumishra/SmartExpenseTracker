package com.smartexpense.tracker.data.repository

import com.smartexpense.tracker.data.local.dao.ExpenseModeDao
import com.smartexpense.tracker.data.local.entity.ExpenseModeEntity
import com.smartexpense.tracker.domain.repository.ExpenseModeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

import com.smartexpense.tracker.data.local.dao.ExpenseDao

class ExpenseModeRepositoryImpl @Inject constructor(
    private val dao: ExpenseModeDao,
    private val expenseDao: ExpenseDao
) : ExpenseModeRepository {

    override suspend fun insertMode(mode: ExpenseModeEntity): Long {
        return dao.insertMode(mode)
    }

    override suspend fun updateMode(mode: ExpenseModeEntity) {
        dao.updateMode(mode)
    }

    override suspend fun deleteModeWithExpenses(mode: ExpenseModeEntity) {
        expenseDao.deleteExpensesByModeId(mode.id)
        dao.deleteMode(mode)
    }

    override suspend fun deactivateCurrentMode(endTime: Long) {
        dao.deactivateCurrentMode(endTime)
    }

    override fun getActiveMode(): Flow<ExpenseModeEntity?> {
        return dao.getActiveMode()
    }

    override fun getAllModes(): Flow<List<ExpenseModeEntity>> {
        return dao.getAllModes()
    }
}

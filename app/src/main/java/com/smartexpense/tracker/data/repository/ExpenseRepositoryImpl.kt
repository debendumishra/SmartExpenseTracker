package com.smartexpense.tracker.data.repository

import com.smartexpense.tracker.data.local.dao.ExpenseDao
import com.smartexpense.tracker.data.local.entity.ExpenseEntity
import com.smartexpense.tracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val dao: ExpenseDao
) : ExpenseRepository {

    override suspend fun insertExpense(expense: ExpenseEntity): Long {
        return dao.insertExpense(expense)
    }

    override suspend fun updateExpense(expense: ExpenseEntity) {
        dao.updateExpense(expense)
    }

    override suspend fun deleteExpense(expense: ExpenseEntity) {
        dao.deleteExpense(expense)
    }
    
    override suspend fun getExpenseById(id: Long): ExpenseEntity? {
        return dao.getExpenseById(id)
    }

    override fun getAllExpenses(): Flow<List<ExpenseEntity>> {
        return dao.getAllExpenses()
    }

    override fun getExpensesByMode(modeId: Long): Flow<List<ExpenseEntity>> {
        return dao.getExpensesByMode(modeId)
    }

    override fun getExpensesBetweenDates(startDate: Long, endDate: Long): Flow<List<ExpenseEntity>> {
        return dao.getExpensesBetweenDates(startDate, endDate)
    }

    override fun getExpensesForExport(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ExportExpenseDTO>> {
        return dao.getExpensesForExport(startDate, endDate)
    }

    override fun getDetailedModeExpenses(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.DetailedModeExpense>> {
        return dao.getDetailedModeExpenses(startDate, endDate)
    }

    override fun getTotalSpentBetween(startDate: Long, endDate: Long): Flow<Double?> {
        return dao.getTotalSpentBetween(startDate, endDate)
    }

    override fun getReportByCategory(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>> {
        return dao.getReportByCategory(startDate, endDate)
    }

    override fun getReportByPurpose(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>> {
        return dao.getReportByPurpose(startDate, endDate)
    }

    override fun getReportByMerchant(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>> {
        return dao.getReportByMerchant(startDate, endDate)
    }

    override fun getReportByPaymentMode(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>> {
        return dao.getReportByPaymentMode(startDate, endDate)
    }

    override fun getReportByLocation(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>> {
        return dao.getReportByLocation(startDate, endDate)
    }

    override fun getReportByExpenseMode(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>> {
        return dao.getReportByExpenseMode(startDate, endDate)
    }

    override fun getReportByBank(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>> {
        return dao.getReportByBank(startDate, endDate)
    }
}

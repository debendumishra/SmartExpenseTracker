package com.smartexpense.tracker.domain.repository

import com.smartexpense.tracker.data.local.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    suspend fun insertExpense(expense: ExpenseEntity): Long
    suspend fun updateExpense(expense: ExpenseEntity)
    suspend fun deleteExpense(expense: ExpenseEntity)
    suspend fun getExpenseById(id: Long): ExpenseEntity?
    fun getAllExpenses(): Flow<List<ExpenseEntity>>
    fun getExpensesByMode(modeId: Long): Flow<List<ExpenseEntity>>
    fun getExpensesBetweenDates(startDate: Long, endDate: Long): Flow<List<ExpenseEntity>>
    
    fun getExpensesForExport(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ExportExpenseDTO>>

    fun getDetailedModeExpenses(): Flow<List<com.smartexpense.tracker.data.local.entity.DetailedModeExpense>>
    fun getTotalSpentBetween(startDate: Long, endDate: Long): Flow<Double?>
    fun getReportByCategory(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>>
    fun getReportByPurpose(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>>
    fun getReportByMerchant(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>>
    fun getReportByPaymentMode(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>>
    fun getReportByLocation(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>>
    fun getReportByExpenseMode(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>>
    fun getReportByBank(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ReportGroupSum>>
}

package com.smartexpense.tracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.smartexpense.tracker.data.local.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow
import com.smartexpense.tracker.data.local.entity.ReportGroupSum

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity): Long

    @Update
    suspend fun updateExpense(expense: ExpenseEntity)

    @androidx.room.Delete
    suspend fun deleteExpense(expense: ExpenseEntity)

    @Query("DELETE FROM expenses WHERE expenseModeId = :modeId")
    suspend fun deleteExpensesByModeId(modeId: Long)

    @Query("SELECT * FROM expenses ORDER BY timestamp DESC")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expenses WHERE id = :id LIMIT 1")
    suspend fun getExpenseById(id: Long): ExpenseEntity?

    @Query("SELECT * FROM expenses WHERE expenseModeId = :modeId ORDER BY timestamp DESC")
    fun getExpensesByMode(modeId: Long): Flow<List<ExpenseEntity>>
    
    @Query("SELECT * FROM expenses WHERE timestamp >= :startDate AND timestamp <= :endDate ORDER BY timestamp DESC")
    fun getExpensesBetweenDates(startDate: Long, endDate: Long): Flow<List<ExpenseEntity>>
    
    @Query("""
        SELECT x.id, x.amount, x.purpose, b.name AS bankName, x.paymentMode, x.merchant, 
               e.name AS modeName, x.timestamp, x.latitude, x.longitude, 
               x.address, x.city, x.state, x.notes, x.source, x.paidBy 
        FROM expenses x 
        LEFT JOIN banks b ON x.bankId = b.id 
        LEFT JOIN expense_modes e ON x.expenseModeId = e.id 
        WHERE x.timestamp >= :startDate AND x.timestamp <= :endDate 
        ORDER BY x.timestamp DESC
    """)
    fun getExpensesForExport(startDate: Long, endDate: Long): Flow<List<com.smartexpense.tracker.data.local.entity.ExportExpenseDTO>>
    
    @Query("SELECT x.id, COALESCE(e.name, 'General') AS modeName, x.amount, x.purpose, x.merchant, x.city AS location, x.timestamp FROM expenses x LEFT JOIN expense_modes e ON x.expenseModeId = e.id ORDER BY COALESCE(e.name, 'General'), x.timestamp DESC")
    fun getDetailedModeExpenses(): Flow<List<com.smartexpense.tracker.data.local.entity.DetailedModeExpense>>
    
    @Query("SELECT SUM(amount) FROM expenses WHERE timestamp >= :startDate AND timestamp <= :endDate")
    fun getTotalSpentBetween(startDate: Long, endDate: Long): Flow<Double?>
    
    @Query("SELECT c.name AS groupName, SUM(x.amount) AS totalAmount FROM expenses x LEFT JOIN categories c ON x.categoryId = c.id WHERE x.timestamp >= :startDate AND x.timestamp <= :endDate GROUP BY c.name ORDER BY totalAmount DESC")
    fun getReportByCategory(startDate: Long, endDate: Long): Flow<List<ReportGroupSum>>

    @Query("SELECT purpose AS groupName, SUM(amount) AS totalAmount FROM expenses WHERE timestamp >= :startDate AND timestamp <= :endDate GROUP BY purpose ORDER BY totalAmount DESC")
    fun getReportByPurpose(startDate: Long, endDate: Long): Flow<List<ReportGroupSum>>

    @Query("SELECT merchant AS groupName, SUM(amount) AS totalAmount FROM expenses WHERE timestamp >= :startDate AND timestamp <= :endDate GROUP BY merchant ORDER BY totalAmount DESC")
    fun getReportByMerchant(startDate: Long, endDate: Long): Flow<List<ReportGroupSum>>

    @Query("SELECT paymentMode AS groupName, SUM(amount) AS totalAmount FROM expenses WHERE timestamp >= :startDate AND timestamp <= :endDate GROUP BY paymentMode ORDER BY totalAmount DESC")
    fun getReportByPaymentMode(startDate: Long, endDate: Long): Flow<List<ReportGroupSum>>

    @Query("SELECT city AS groupName, SUM(amount) AS totalAmount FROM expenses WHERE timestamp >= :startDate AND timestamp <= :endDate GROUP BY city ORDER BY totalAmount DESC")
    fun getReportByLocation(startDate: Long, endDate: Long): Flow<List<ReportGroupSum>>

    @Query("SELECT e.name AS groupName, SUM(x.amount) AS totalAmount FROM expenses x LEFT JOIN expense_modes e ON x.expenseModeId = e.id WHERE x.timestamp >= :startDate AND x.timestamp <= :endDate GROUP BY e.name ORDER BY totalAmount DESC")
    fun getReportByExpenseMode(startDate: Long, endDate: Long): Flow<List<ReportGroupSum>>

    @Query("SELECT b.name AS groupName, SUM(x.amount) AS totalAmount FROM expenses x LEFT JOIN banks b ON x.bankId = b.id WHERE x.timestamp >= :startDate AND x.timestamp <= :endDate GROUP BY b.name ORDER BY totalAmount DESC")
    fun getReportByBank(startDate: Long, endDate: Long): Flow<List<ReportGroupSum>>
}

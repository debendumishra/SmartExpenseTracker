package com.smartexpense.tracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val amount: Double,
    val purpose: String?,
    val categoryId: Long?,
    val bankId: Long?,
    val paymentMode: String, // Cash, UPI, Debit Card, etc.
    val merchant: String?,
    val expenseModeId: Long?, // Null if General Expense
    val timestamp: Long,      // Transaction Date/Time
    val entryTimestamp: Long, // When it was added to the app
    val latitude: Double?,
    val longitude: Double?,
    val address: String?,
    val city: String?,
    val state: String?,
    val notes: String?,
    val source: String,       // Manual, SMS, Imported
    val smsTimestamp: Long?,  // If source is SMS
    val paidBy: String? = null // Who paid the expense
)

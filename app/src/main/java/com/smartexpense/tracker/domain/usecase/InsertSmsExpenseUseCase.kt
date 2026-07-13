package com.smartexpense.tracker.domain.usecase

import com.smartexpense.tracker.data.local.entity.ExpenseEntity
import com.smartexpense.tracker.domain.repository.ExpenseRepository
import com.smartexpense.tracker.domain.repository.ExpenseModeRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class InsertSmsExpenseUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    private val modeRepository: ExpenseModeRepository
) {
    suspend operator fun invoke(
        amount: Double,
        merchant: String?,
        bank: String?,
        paidBy: String? = null,
        notes: String? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        city: String? = null,
        address: String? = null
    ) {
        val activeMode = modeRepository.getActiveMode().firstOrNull()
        
        val expense = ExpenseEntity(
            amount = amount,
            purpose = "General", // Default category for SMS
            categoryId = null,
            bankId = null,
            paymentMode = "Bank/Card", // Default for SMS
            merchant = merchant,
            expenseModeId = activeMode?.id,
            timestamp = System.currentTimeMillis(),
            entryTimestamp = System.currentTimeMillis(),
            latitude = latitude,
            longitude = longitude,
            address = address,
            city = city,
            state = null,
            notes = notes ?: "Auto-generated from SMS via $bank",
            source = "SMS",
            smsTimestamp = System.currentTimeMillis(),
            paidBy = paidBy
        )
        
        expenseRepository.insertExpense(expense)
    }
}

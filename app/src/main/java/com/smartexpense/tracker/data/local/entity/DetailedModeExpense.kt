package com.smartexpense.tracker.data.local.entity

data class DetailedModeExpense(
    val id: Long,
    val modeName: String?,
    val amount: Double,
    val purpose: String?,
    val merchant: String?,
    val location: String?, // mapped from city
    val timestamp: Long
)

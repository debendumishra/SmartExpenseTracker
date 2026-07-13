package com.smartexpense.tracker.data.local.entity

data class ExportExpenseDTO(
    val id: Long,
    val amount: Double,
    val purpose: String?,
    val bankName: String?,
    val paymentMode: String,
    val merchant: String?,
    val modeName: String?,
    val timestamp: Long,
    val latitude: Double?,
    val longitude: Double?,
    val address: String?,
    val city: String?,
    val state: String?,
    val notes: String?,
    val source: String,
    val paidBy: String?
)

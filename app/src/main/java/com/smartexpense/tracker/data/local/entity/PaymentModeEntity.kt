package com.smartexpense.tracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payment_modes")
data class PaymentModeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
)

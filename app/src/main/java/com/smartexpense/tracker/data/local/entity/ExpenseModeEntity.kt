package com.smartexpense.tracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_modes")
data class ExpenseModeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,         // e.g., "Tour to Delhi", "Picnic"
    val isActive: Boolean,    // Only one can be active at a time
    val createdAt: Long,
    val endedAt: Long?
)

package com.smartexpense.tracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.smartexpense.tracker.data.local.entity.ExpenseModeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseModeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMode(mode: ExpenseModeEntity): Long

    @Update
    suspend fun updateMode(mode: ExpenseModeEntity)

    @Query("SELECT * FROM expense_modes WHERE isActive = 1 LIMIT 1")
    fun getActiveMode(): Flow<ExpenseModeEntity?>

    @Query("UPDATE expense_modes SET isActive = 0, endedAt = :endTime WHERE isActive = 1")
    suspend fun deactivateCurrentMode(endTime: Long)
    
    @Query("SELECT * FROM expense_modes ORDER BY createdAt DESC")
    fun getAllModes(): Flow<List<ExpenseModeEntity>>
}

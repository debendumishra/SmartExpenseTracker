package com.smartexpense.tracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smartexpense.tracker.data.local.entity.BankEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BankDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBank(bank: BankEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanks(banks: List<BankEntity>)

    @Query("SELECT * FROM banks ORDER BY name ASC")
    fun getAllBanks(): Flow<List<BankEntity>>
}

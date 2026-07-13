package com.smartexpense.tracker.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.smartexpense.tracker.data.local.entity.PaymentModeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentModeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaymentMode(paymentMode: PaymentModeEntity): Long

    @Update
    suspend fun updatePaymentMode(paymentMode: PaymentModeEntity)

    @Delete
    suspend fun deletePaymentMode(paymentMode: PaymentModeEntity)

    @Query("SELECT * FROM payment_modes ORDER BY name ASC")
    fun getAllPaymentModes(): Flow<List<PaymentModeEntity>>
    
    @Query("SELECT * FROM payment_modes WHERE name = :name LIMIT 1")
    suspend fun getPaymentModeByName(name: String): PaymentModeEntity?
}

package com.smartexpense.tracker.domain.repository

import com.smartexpense.tracker.data.local.dao.PaymentModeDao
import com.smartexpense.tracker.data.local.entity.PaymentModeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PaymentModeRepository @Inject constructor(
    private val paymentModeDao: PaymentModeDao
) {
    fun getAllPaymentModes(): Flow<List<PaymentModeEntity>> = paymentModeDao.getAllPaymentModes()

    suspend fun insertPaymentMode(name: String) {
        paymentModeDao.insertPaymentMode(PaymentModeEntity(name = name))
    }

    suspend fun deletePaymentMode(paymentMode: PaymentModeEntity) {
        paymentModeDao.deletePaymentMode(paymentMode)
    }
}

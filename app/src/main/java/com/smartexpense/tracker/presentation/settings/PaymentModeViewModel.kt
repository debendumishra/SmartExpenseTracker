package com.smartexpense.tracker.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartexpense.tracker.data.local.entity.PaymentModeEntity
import com.smartexpense.tracker.domain.repository.PaymentModeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentModeViewModel @Inject constructor(
    private val repository: PaymentModeRepository
) : ViewModel() {

    private val _paymentModes = MutableStateFlow<List<PaymentModeEntity>>(emptyList())
    val paymentModes: StateFlow<List<PaymentModeEntity>> = _paymentModes

    init {
        loadPaymentModes()
    }

    private fun loadPaymentModes() {
        viewModelScope.launch {
            repository.getAllPaymentModes().collectLatest {
                _paymentModes.value = it
            }
        }
    }

    fun addPaymentMode(name: String) {
        if (name.isNotBlank()) {
            viewModelScope.launch {
                repository.insertPaymentMode(name.trim())
            }
        }
    }

    fun deletePaymentMode(paymentMode: PaymentModeEntity) {
        viewModelScope.launch {
            repository.deletePaymentMode(paymentMode)
        }
    }
}

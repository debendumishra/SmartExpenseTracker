package com.smartexpense.tracker.presentation.add_expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartexpense.tracker.data.local.entity.ExpenseEntity
import com.smartexpense.tracker.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

import android.content.Context
import android.location.Geocoder
import dagger.hilt.android.qualifiers.ApplicationContext
import com.smartexpense.tracker.domain.repository.ExpenseModeRepository
import com.smartexpense.tracker.domain.repository.CategoryRepository
import com.smartexpense.tracker.domain.repository.PaymentModeRepository
import com.google.android.gms.location.FusedLocationProviderClient
import android.annotation.SuppressLint
import android.location.Location
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import com.smartexpense.tracker.data.local.entity.CategoryEntity
import com.smartexpense.tracker.data.local.entity.ExpenseModeEntity
import com.smartexpense.tracker.data.local.entity.PaymentModeEntity

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    private val modeRepository: ExpenseModeRepository,
    private val categoryRepository: CategoryRepository,
    private val paymentModeRepository: PaymentModeRepository,
    private val fusedLocationClient: FusedLocationProviderClient,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private var editingExpense: ExpenseEntity? = null

    val categories: StateFlow<List<CategoryEntity>> = categoryRepository.getAllCategories()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val activeMode: StateFlow<ExpenseModeEntity?> = modeRepository.getActiveMode()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    private val _amount = MutableStateFlow("")
    val amount: StateFlow<String> = _amount.asStateFlow()
    
    private val _merchant = MutableStateFlow("")
    val merchant: StateFlow<String> = _merchant.asStateFlow()
    
    private val _category = MutableStateFlow("Food") // Default
    val category: StateFlow<String> = _category.asStateFlow()

    private val _paymentMode = MutableStateFlow("Cash") // Default
    val paymentMode: StateFlow<String> = _paymentMode.asStateFlow()

    private val _paidBy = MutableStateFlow("Me")
    val paidBy: StateFlow<String> = _paidBy.asStateFlow()

    val paymentModes: StateFlow<List<PaymentModeEntity>> = paymentModeRepository.getAllPaymentModes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun updateAmount(newAmount: String) {
        _amount.value = newAmount
    }
    
    fun updateMerchant(newMerchant: String) {
        _merchant.value = newMerchant
    }
    
    fun updateCategory(newCategory: String) {
        _category.value = newCategory
    }

    fun updatePaymentMode(newPaymentMode: String) {
        _paymentMode.value = newPaymentMode
    }

    fun updatePaidBy(newPaidBy: String) {
        _paidBy.value = newPaidBy
    }

    fun loadExpense(id: Long) {
        viewModelScope.launch {
            val expense = expenseRepository.getExpenseById(id)
            if (expense != null) {
                editingExpense = expense
                _amount.value = expense.amount.toString()
                _merchant.value = expense.merchant ?: ""
                _category.value = expense.purpose ?: "Food"
                _paymentMode.value = expense.paymentMode ?: "Cash"
                _paidBy.value = expense.paidBy ?: "Me"
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun saveExpense(onSuccess: () -> Unit) {
        val currentAmount = amount.value.toDoubleOrNull()
        if (currentAmount != null && currentAmount > 0) {
            viewModelScope.launch {
                var latitude: Double? = null
                var longitude: Double? = null
                var city: String? = null
                var address: String? = null
                
                try {
                    val location: Location? = fusedLocationClient.lastLocation.await()
                    if (location != null) {
                        latitude = location.latitude
                        longitude = location.longitude
                        try {
                            val geocoder = Geocoder(context, java.util.Locale.getDefault())
                            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                            if (!addresses.isNullOrEmpty()) {
                                val addr = addresses[0]
                                city = addr.locality ?: addr.subAdminArea ?: addr.adminArea
                                address = addr.featureName ?: addr.subLocality ?: addr.thoroughfare ?: addr.getAddressLine(0)
                            }
                        } catch (e: Exception) {
                            // Ignore geocoder exception
                        }
                    }
                } catch (e: Exception) {
                    // Ignore location exception, fallback to null
                }

                val activeMode = activeMode.value
                val existing = editingExpense
                if (existing != null) {
                    val updated = existing.copy(
                        amount = currentAmount,
                        purpose = category.value,
                        paymentMode = paymentMode.value,
                        merchant = merchant.value,
                        paidBy = paidBy.value.takeIf { it.isNotBlank() },
                        // Optionally update location if requested, but let's just keep original location or overwrite? 
                        // Overwrite with new location if available
                        latitude = latitude ?: existing.latitude,
                        longitude = longitude ?: existing.longitude,
                        city = city ?: existing.city,
                        address = address ?: existing.address
                    )
                    expenseRepository.updateExpense(updated)
                } else {
                    val expense = ExpenseEntity(
                        amount = currentAmount,
                        purpose = category.value,
                        categoryId = null, // simplified for now
                        bankId = null,
                        paymentMode = paymentMode.value,
                        merchant = merchant.value,
                        expenseModeId = activeMode?.id,
                        timestamp = System.currentTimeMillis(),
                        entryTimestamp = System.currentTimeMillis(),
                        latitude = latitude,
                        longitude = longitude,
                        address = address,
                        city = city,
                        state = null,
                        notes = null,
                        source = "Manual",
                        smsTimestamp = null,
                        paidBy = paidBy.value.takeIf { it.isNotBlank() }
                    )
                    expenseRepository.insertExpense(expense)
                }
                onSuccess()
            }
        }
    }
}

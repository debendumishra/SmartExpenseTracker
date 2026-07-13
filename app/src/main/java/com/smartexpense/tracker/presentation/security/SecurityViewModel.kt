package com.smartexpense.tracker.presentation.security

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SecurityViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val sharedPrefs = context.getSharedPreferences("SecurityPrefs", Context.MODE_PRIVATE)

    private val _isPinEnabled = MutableStateFlow(sharedPrefs.getBoolean("is_pin_enabled", false))
    val isPinEnabled: StateFlow<Boolean> = _isPinEnabled.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    fun setPin(pin: String) {
        if (pin.length == 4) {
            sharedPrefs.edit().putString("app_pin", pin).putBoolean("is_pin_enabled", true).apply()
            _isPinEnabled.value = true
            _message.value = "PIN set successfully."
        } else {
            _message.value = "PIN must be 4 digits."
        }
    }

    fun disablePin() {
        sharedPrefs.edit().putBoolean("is_pin_enabled", false).remove("app_pin").apply()
        _isPinEnabled.value = false
        _message.value = "PIN lock disabled."
    }
    
    fun clearMessage() {
        _message.value = null
    }

    fun verifyPin(pin: String): Boolean {
        val savedPin = sharedPrefs.getString("app_pin", "")
        return savedPin == pin
    }
}

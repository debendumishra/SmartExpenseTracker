package com.smartexpense.tracker.presentation.security

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun AppLockScreen(
    onAuthenticated: () -> Unit,
    viewModel: SecurityViewModel = hiltViewModel()
) {
    var pinInput by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(64.dp), tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(16.dp))
            Text("App Locked", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Please enter your PIN to continue.", style = MaterialTheme.typography.bodyMedium)
            
            Spacer(modifier = Modifier.height(32.dp))
            
            OutlinedTextField(
                value = pinInput,
                onValueChange = { 
                    if (it.length <= 4) {
                        pinInput = it
                        showError = false
                    }
                },
                label = { Text("PIN") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                singleLine = true,
                isError = showError
            )
            
            if (showError) {
                Text("Incorrect PIN", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = {
                    if (viewModel.verifyPin(pinInput)) {
                        onAuthenticated()
                    } else {
                        showError = true
                        pinInput = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(0.5f),
                enabled = pinInput.length == 4
            ) {
                Text("Unlock")
            }
        }
    }
}

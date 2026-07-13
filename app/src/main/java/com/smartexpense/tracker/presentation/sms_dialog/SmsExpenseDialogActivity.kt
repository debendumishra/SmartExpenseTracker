package com.smartexpense.tracker.presentation.sms_dialog

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dagger.hilt.android.AndroidEntryPoint
import com.smartexpense.tracker.ui.theme.SmartExpenseTrackerTheme
import com.smartexpense.tracker.domain.usecase.InsertSmsExpenseUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.google.android.gms.location.FusedLocationProviderClient
import android.annotation.SuppressLint
import android.location.Location
import android.location.Geocoder
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.tasks.await
import android.text.InputType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

@AndroidEntryPoint
class SmsExpenseDialogActivity : ComponentActivity() {

    @Inject
    lateinit var insertSmsExpenseUseCase: InsertSmsExpenseUseCase

    @Inject
    lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val amount = intent.getDoubleExtra("sms_amount", 0.0)
        val merchant = intent.getStringExtra("sms_merchant")
        val bank = intent.getStringExtra("sms_bank")

        if (amount <= 0.0) {
            finish()
            return
        }

        setContent {
            SmartExpenseTrackerTheme {
                var editAmount by remember { mutableStateOf(amount.toString()) }
                var editMerchant by remember { mutableStateOf(merchant ?: "") }
                var editBank by remember { mutableStateOf(bank ?: "") }
                var paidBy by remember { mutableStateOf("Me") }
                var narration by remember { mutableStateOf("SMS Expense via ${bank ?: "Unknown Bank"}") }
                val scope = rememberCoroutineScope()

                Dialog(onDismissRequest = { finish() }) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "New SMS Expense",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = editAmount,
                                onValueChange = { editAmount = it },
                                label = { Text("Amount") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = editMerchant,
                                onValueChange = { editMerchant = it },
                                label = { Text("Merchant") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            OutlinedTextField(
                                value = editBank,
                                onValueChange = { editBank = it },
                                label = { Text("Bank") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = paidBy,
                                onValueChange = { paidBy = it },
                                label = { Text("Paid By") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = narration,
                                onValueChange = { narration = it },
                                label = { Text("Narration / Notes") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = { 
                                    val notificationId = intent.getIntExtra("notification_id", -1)
                                    if (notificationId != -1) {
                                        NotificationManagerCompat.from(this@SmsExpenseDialogActivity).cancel(notificationId)
                                    }
                                    finish() 
                                }) {
                                    Text("Cancel")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        scope.launch {
                                            var latitude: Double? = null
                                            var longitude: Double? = null
                                            var city: String? = null
                                            var address: String? = null
                                            
                                            try {
                                                @SuppressLint("MissingPermission")
                                                val location: Location? = fusedLocationClient.lastLocation.await()
                                                if (location != null) {
                                                    latitude = location.latitude
                                                    longitude = location.longitude
                                                    
                                                    try {
                                                        val geocoder = Geocoder(this@SmsExpenseDialogActivity, java.util.Locale.getDefault())
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
                                                // Ignore location exception
                                            }

                                            val finalAmount = editAmount.toDoubleOrNull() ?: 0.0
                                            insertSmsExpenseUseCase(finalAmount, editMerchant, editBank, paidBy, narration, latitude, longitude, city, address)
                                            
                                            val notificationId = intent.getIntExtra("notification_id", -1)
                                            if (notificationId != -1) {
                                                NotificationManagerCompat.from(this@SmsExpenseDialogActivity).cancel(notificationId)
                                            }
                                            
                                            Toast.makeText(this@SmsExpenseDialogActivity, "Expense Saved", Toast.LENGTH_SHORT).show()
                                            finish()
                                        }
                                    }
                                ) {
                                    Text("Save")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

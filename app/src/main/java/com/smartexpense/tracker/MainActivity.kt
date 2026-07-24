package com.smartexpense.tracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import com.smartexpense.tracker.ui.theme.SmartExpenseTrackerTheme
import com.smartexpense.tracker.presentation.main.MainScreen
import com.smartexpense.tracker.presentation.security.AppLockScreen
import com.smartexpense.tracker.presentation.security.SecurityViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import android.Manifest
import android.app.PendingIntent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.smartexpense.tracker.utils.SmsParser
import com.smartexpense.tracker.domain.usecase.InsertSmsExpenseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@OptIn(ExperimentalPermissionsApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var insertSmsExpenseUseCase: InsertSmsExpenseUseCase

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Handle shared SMS text (e.g. from default SMS app)
        handleSharedIntent(intent)

        setContent {
            SmartExpenseTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val securityViewModel: SecurityViewModel = hiltViewModel()
                    val isPinEnabled by securityViewModel.isPinEnabled.collectAsState()
                    var isAuthenticated by remember { mutableStateOf(false) }

                    // Request Permissions
                    val permissions = mutableListOf(
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        permissions.add(Manifest.permission.POST_NOTIFICATIONS)
                    }

                    val permissionsState = rememberMultiplePermissionsState(permissions = permissions)

                    LaunchedEffect(Unit) {
                        if (!permissionsState.allPermissionsGranted) {
                            permissionsState.launchMultiplePermissionRequest()
                        }
                    }

                    if (isPinEnabled && !isAuthenticated) {
                        AppLockScreen(onAuthenticated = { isAuthenticated = true })
                    } else {
                        MainScreen()
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // Handle when app is already open and a new share arrives
        handleSharedIntent(intent)
    }

    private fun handleSharedIntent(intent: Intent?) {
        if (intent?.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT) ?: return
            Log.d("MainActivity", "Shared SMS text: $sharedText")

            if (SmsParser.isExpenseSms(sharedText)) {
                val amount = SmsParser.extractAmount(sharedText)
                val merchant = SmsParser.extractMerchant(sharedText)
                val bank = SmsParser.extractBankName(sharedText)

                Log.d("MainActivity", "Expense detected from shared SMS: amount=$amount merchant=$merchant bank=$bank")

                if (amount != null) {
                    sendExpenseNotification(amount, merchant, bank)
                    Toast.makeText(
                        this,
                        "Expense detected: ₹$amount${if (merchant != null) " at $merchant" else ""}",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "SMS received but no expense amount found. Please add manually.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "Text received but doesn't look like an expense SMS.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun sendExpenseNotification(amount: Double, merchant: String?, bank: String) {
        val notificationId = System.currentTimeMillis().toInt()
        val uriString = "smartexpense://add_expense?amount=$amount" +
                (merchant?.let { "&merchant=${android.net.Uri.encode(it)}" } ?: "") +
                "&bank=${android.net.Uri.encode(bank)}&source=Share"
        val activityIntent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(uriString)).apply {
            setPackage(packageName)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            this, notificationId, activityIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val action = NotificationCompat.Action.Builder(
            android.R.drawable.ic_input_add,
            "Add Details",
            pendingIntent
        ).build()

        val notification = NotificationCompat.Builder(this, "expense_channel")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("New Expense Detected")
            .setContentText("₹$amount from $bank at ${merchant ?: "Unknown Merchant"}")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .addAction(action)
            .setAutoCancel(true)
            .build()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            NotificationManagerCompat.from(this).notify(notificationId, notification)
        }
    }
}

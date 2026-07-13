package com.smartexpense.tracker.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import com.smartexpense.tracker.utils.SmsParser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import android.app.PendingIntent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.smartexpense.tracker.MainActivity
import com.smartexpense.tracker.R
import com.smartexpense.tracker.domain.usecase.InsertSmsExpenseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@AndroidEntryPoint
class SmsReceiver : BroadcastReceiver() {

    @Inject
    lateinit var insertSmsExpenseUseCase: InsertSmsExpenseUseCase

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            for (sms in smsMessages) {
                val messageBody = sms.displayMessageBody
                val sender = sms.displayOriginatingAddress ?: ""
                
                Log.d("SmsReceiver", "Received SMS from: $sender")
                
                if (SmsParser.isExpenseSms(messageBody)) {
                    val amount = SmsParser.extractAmount(messageBody)
                    val merchant = SmsParser.extractMerchant(messageBody)
                    val bank = SmsParser.extractBankName(sender)
                    
                    Log.d("SmsReceiver", "Expense detected: $amount to $merchant from $bank")
                    
                    if (amount != null) {
                        val notificationId = System.currentTimeMillis().toInt()
                        val activityIntent = Intent(context, com.smartexpense.tracker.presentation.sms_dialog.SmsExpenseDialogActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            putExtra("sms_amount", amount)
                            putExtra("sms_merchant", merchant)
                            putExtra("sms_bank", bank)
                            putExtra("notification_id", notificationId)
                        }
                        
                        val pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            activityIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                        )

                        val action = NotificationCompat.Action.Builder(
                            android.R.drawable.ic_input_add,
                            "Add Details",
                            pendingIntent
                        ).build()

                        val notification = NotificationCompat.Builder(context, "expense_channel")
                            .setSmallIcon(android.R.drawable.ic_dialog_info)
                            .setContentTitle("New Expense Detected")
                            .setContentText("₹$amount from $bank at ${merchant ?: "Unknown Merchant"}")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setContentIntent(pendingIntent)
                            .addAction(action)
                            .setAutoCancel(true)
                            .build()

                        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                            with(NotificationManagerCompat.from(context)) {
                                notify(notificationId, notification)
                            }
                        }

                        // No longer auto-inserting directly; wait for user interaction in Dialog
                    }
                }
            }
        }
    }
}

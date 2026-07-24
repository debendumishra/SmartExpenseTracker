package com.smartexpense.tracker.services

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

import com.smartexpense.tracker.utils.SmsParser

class EmailNotificationListenerService : NotificationListenerService() {
    private val TAG = "EmailNotifService"

    private val targetPackages = listOf(
        "com.google.android.gm", // Gmail
        "com.microsoft.office.outlook", // Outlook
        "com.yahoo.mobile.client.android.mail" // Yahoo Mail
    )

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        sbn ?: return

        // Check if the setting is enabled. We will use SharedPreferences for this.
        val prefs = getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        val readEmailsEnabled = prefs.getBoolean("read_email_notifications", false)
        if (!readEmailsEnabled) return

        val packageName = sbn.packageName
        if (targetPackages.contains(packageName)) {
            val extras = sbn.notification.extras
            val title = extras.getString(Notification.EXTRA_TITLE) ?: ""
            val text = extras.getCharSequence(Notification.EXTRA_TEXT)?.toString() ?: ""
            
            // Sometimes email apps put the body in EXTRA_BIG_TEXT
            val bigText = extras.getCharSequence(Notification.EXTRA_BIG_TEXT)?.toString() ?: ""
            
            val fullText = "$title $text $bigText"

            Log.d(TAG, "Notification received from $packageName: $fullText")

            if (SmsParser.isExpenseSms(fullText)) {
                val amount = SmsParser.extractAmount(fullText)
                if (amount != null) {
                    val merchant = SmsParser.extractMerchant(fullText) ?: "Unknown Merchant"
                    val bankName = SmsParser.extractBankName(fullText)
                    val paymentMode = SmsParser.extractPaymentMode(fullText)

                    val uriString = "smartexpense://add_expense?amount=$amount" +
                            "&merchant=${android.net.Uri.encode(merchant)}" +
                            "&bank=${android.net.Uri.encode(bankName)}&source=Email"
                    val activityIntent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(uriString)).apply {
                        setPackage(packageName)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }

                    val notificationId = System.currentTimeMillis().toInt()
                    val pendingIntent = android.app.PendingIntent.getActivity(
                        this, notificationId, activityIntent,
                        android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
                    )

                    val action = androidx.core.app.NotificationCompat.Action.Builder(
                        android.R.drawable.ic_input_add,
                        "Add Details",
                        pendingIntent
                    ).build()

                    val notification = androidx.core.app.NotificationCompat.Builder(this, "expense_channel")
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("New Expense Detected (Email)")
                        .setContentText("₹$amount from $bankName at $merchant")
                        .setPriority(androidx.core.app.NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent)
                        .addAction(action)
                        .setAutoCancel(true)
                        .build()

                    if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                        try {
                            androidx.core.app.NotificationManagerCompat.from(this).notify(notificationId, notification)
                        } catch (e: Exception) {
                            Log.e(TAG, "Failed to post notification: ${e.message}")
                        }
                    }
                }
            }
        }
    }
}

package com.smartexpense.tracker.utils

import android.util.Log

object SmsParser {

    private const val TAG = "SmsParser"

    // Amount patterns - covers Rs., INR, ₹, debited/credited with amounts
    private val amountRegex = Regex(
        "(?i)(?:rs\\.?|inr|₹)\\s*([0-9,]+\\.?[0-9]*)" +
        "|(?i)(?:debited|credited|paid|sent|deducted)\\s+(?:for|of|with|by|:)?\\s*(?:rs\\.?|inr|₹)?\\s*([0-9,]+\\.?[0-9]*)"
    )

    // Keywords indicating a debit transaction
    private val debitKeywords = listOf(
        "debited", "spent", "paid", "sent", "deducted", "withdrawn",
        "purchase", "transaction", "payment", "transfer", "charged"
    )

    // Keywords to ignore (OTPs, promotions, etc.)
    private val ignoreKeywords = listOf(
        "otp", "one time password", "verification code", "login code",
        "promotional", "offer", "discount", "recharge", "cashback received",
        "credited to your", "deposited"
    )

    fun isExpenseSms(messageBody: String): Boolean {
        val lowerCaseMsg = messageBody.lowercase()

        // Filter out OTPs and promotional messages
        for (keyword in ignoreKeywords) {
            if (lowerCaseMsg.contains(keyword)) {
                Log.d(TAG, "Ignored SMS: contains '$keyword'")
                return false
            }
        }

        // Check if it's a debit transaction
        val isDebit = debitKeywords.any { lowerCaseMsg.contains(it) }
        if (!isDebit) return false

        // Ensure there is an amount mentioned
        val hasAmount = extractAmount(messageBody) != null
        return hasAmount
    }

    fun extractAmount(messageBody: String): Double? {
        // Try ₹ / Rs. / INR prefix pattern
        val prefixPattern = Regex("(?i)(?:rs\\.?|inr|₹)\\s*([0-9,]+\\.?[0-9]*)")
        prefixPattern.find(messageBody)?.groupValues?.get(1)?.replace(",", "")?.toDoubleOrNull()
            ?.let { return it }

        // Try amount after debit keyword
        val debitAmountPattern = Regex(
            "(?i)(?:debited|paid|sent|deducted|withdrawn|charged)[^0-9₹]*(?:rs\\.?|inr|₹)?\\s*([0-9,]+\\.?[0-9]*)"
        )
        debitAmountPattern.find(messageBody)?.groupValues?.get(1)?.replace(",", "")?.toDoubleOrNull()
            ?.let { return it }

        return null
    }

    fun extractMerchant(messageBody: String): String? {
        // "to MERCHANT_NAME" or "at MERCHANT_NAME"
        val patterns = listOf(
            Regex("(?i)(?:paid to|sent to|transferred to|to)\\s+([A-Za-z0-9\\s@.&_-]+?)(?:\\s+on|\\s+via|\\s+ref|\\s+for|\\s+upi|\\.|,|$)"),
            Regex("(?i)(?:at|for)\\s+([A-Za-z0-9\\s@.&_-]+?)(?:\\s+on|\\s+via|\\s+ref|\\s+upi|\\.|,|$)"),
            Regex("(?i)merchant\\s*(?:name)?\\s*:?\\s*([A-Za-z0-9\\s@.&_-]+?)(?:\\.|,|$)")
        )
        for (pattern in patterns) {
            val match = pattern.find(messageBody)
            val result = match?.groupValues?.get(1)?.trim()
            if (!result.isNullOrBlank() && result.length > 2) return result
        }
        return null
    }

    fun extractPaymentMode(messageBody: String): String {
        val lower = messageBody.lowercase()
        return when {
            lower.contains("upi") -> "UPI"
            lower.contains("neft") -> "NEFT"
            lower.contains("imps") -> "IMPS"
            lower.contains("rtgs") -> "RTGS"
            lower.contains("credit card") || lower.contains("cc ") -> "Credit Card"
            lower.contains("debit card") || lower.contains("dc ") -> "Debit Card"
            lower.contains("netbanking") || lower.contains("net banking") -> "Net Banking"
            lower.contains("atm") -> "ATM"
            else -> "Cash"
        }
    }

    fun extractBankName(senderOrBody: String): String {
        val lower = senderOrBody.lowercase()
        return when {
            lower.contains("hdfc") -> "HDFC Bank"
            lower.contains("sbi") || lower.contains("state bank") -> "SBI"
            lower.contains("icici") -> "ICICI Bank"
            lower.contains("axis") -> "Axis Bank"
            lower.contains("pnb") || lower.contains("punjab national") -> "PNB"
            lower.contains("kotak") -> "Kotak Mahindra Bank"
            lower.contains("idbi") -> "IDBI Bank"
            lower.contains("canara") -> "Canara Bank"
            lower.contains("union bank") -> "Union Bank"
            lower.contains("bob") || lower.contains("bank of baroda") -> "Bank of Baroda"
            lower.contains("indusind") -> "IndusInd Bank"
            lower.contains("yes bank") -> "Yes Bank"
            lower.contains("paytm") -> "Paytm"
            lower.contains("phonepe") -> "PhonePe"
            lower.contains("gpay") || lower.contains("google pay") -> "Google Pay"
            else -> "Unknown Bank"
        }
    }
}

import java.util.regex.Regex

fun main() {
    val messageBody = "Rs.130.00 spent on your SBI Credit Card ending with 6040 at DEBARAJBEHERA on 26-08-26 via UPI (Ref No. 182624272494). Trxn.. not done by you? Report at https://sbicard.com/Dispute . If you have not authorized this transaction please contact the SBI Card helpline at 39 02 02 02 (prefix local STD code) or 1860 180 1290."

    val debitKeywords = listOf(
        "debited", "spent", "paid", "sent", "deducted", "withdrawn",
        "purchase", "transaction", "payment", "transfer", "charged", "made on"
    )

    val ignoreKeywords = listOf(
        "otp", "one time password", "verification code", "login code",
        "credited to your", "deposited", "received"
    )
    
    val lowerCaseMsg = messageBody.lowercase()
    var isIgnored = false
    for (keyword in ignoreKeywords) {
        if (lowerCaseMsg.contains(keyword)) {
            println("Ignored due to: $keyword")
            isIgnored = true
        }
    }
    
    val isDebit = debitKeywords.any { lowerCaseMsg.contains(it) }
    println("Is Debit: $isDebit")

    val prefixPattern = Regex("(?i)(?:rs\\.?|inr|₹)\\s*([0-9,]+\\.?[0-9]*)")
    val amountMatch = prefixPattern.find(messageBody)?.groupValues?.get(1)?.replace(",", "")?.toDoubleOrNull()
    println("Amount: $amountMatch")
    
    val patterns = listOf(
        Regex("(?i)(?:paid to|sent to|transferred to|to)\\s+([A-Za-z0-9\\s@.&_-]+?)(?:\\s+on|\\s+via|\\s+ref|\\s+for|\\s+upi|\\.|,|$)"),
        Regex("(?i)(?:at|for)\\s+([A-Za-z0-9\\s@.&_-]+?)(?:\\s+on|\\s+via|\\s+ref|\\s+upi|\\.|,|$)"),
        Regex("(?i)merchant\\s*(?:name)?\\s*:?\\s*([A-Za-z0-9\\s@.&_-]+?)(?:\\.|,|$)")
    )
    var merchantResult: String? = null
    for (pattern in patterns) {
        val match = pattern.find(messageBody)
        val result = match?.groupValues?.get(1)?.trim()
        if (!result.isNullOrBlank() && result.length > 2) {
            merchantResult = result
            break
        }
    }
    println("Merchant: $merchantResult")
}

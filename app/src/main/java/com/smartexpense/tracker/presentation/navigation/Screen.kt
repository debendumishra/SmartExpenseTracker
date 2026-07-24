package com.smartexpense.tracker.presentation.navigation

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard_screen")
    object AddExpense : Screen("add_expense_screen")
    object Transactions : Screen("transactions_screen")
    object Settings : Screen("settings_screen")
    object ExpenseModes : Screen("expense_modes_screen")
    object ManageCategories : Screen("manage_categories_screen")
    object Reports : Screen("reports_screen")
    object DetailedModeReport : Screen("detailed_mode_report_screen")
    object DataBackup : Screen("data_backup_screen")
    object Security : Screen("security_screen")
    object ManagePaymentModes : Screen("manage_payment_modes_screen")
    object UserInstructions : Screen("user_instructions_screen")
    object Automation : Screen("automation_screen")
}

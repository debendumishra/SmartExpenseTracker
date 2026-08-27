package com.smartexpense.tracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.smartexpense.tracker.presentation.add_expense.AddExpenseScreen
import com.smartexpense.tracker.presentation.dashboard.DashboardScreen
import com.smartexpense.tracker.presentation.settings.SettingsScreen
import com.smartexpense.tracker.presentation.settings.ExpenseModeScreen
import com.smartexpense.tracker.presentation.settings.ManageCategoriesScreen
import com.smartexpense.tracker.presentation.settings.ManagePaymentModesScreen
import com.smartexpense.tracker.presentation.settings.AutomationScreen
import com.smartexpense.tracker.presentation.transactions.TransactionsScreen
import com.smartexpense.tracker.presentation.reports.ReportsScreen
import com.smartexpense.tracker.presentation.reports.DetailedModeReportScreen
import com.smartexpense.tracker.presentation.backup.BackupScreen
import com.smartexpense.tracker.presentation.security.SecurityScreen
import com.smartexpense.tracker.presentation.help.UserInstructionsScreen



@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Dashboard.route) {
        composable(Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
        composable(Screen.Transactions.route) {
            TransactionsScreen(navController = navController)
        }
        composable(
            route = Screen.AddExpense.route + "?expenseId={expenseId}&amount={amount}&merchant={merchant}&bank={bank}&source={source}",
            arguments = listOf(
                navArgument("expenseId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("amount") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("merchant") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("bank") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("source") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            ),
            deepLinks = listOf(
                androidx.navigation.navDeepLink {
                    uriPattern = "smartexpense://add_expense?amount={amount}&merchant={merchant}&bank={bank}&source={source}"
                }
            )
        ) { backStackEntry ->
            val expenseIdStr = backStackEntry.arguments?.getString("expenseId")
            val expenseId = expenseIdStr?.toLongOrNull()
            val autoAmount = backStackEntry.arguments?.getString("amount")
            val autoMerchant = backStackEntry.arguments?.getString("merchant")
            val autoBank = backStackEntry.arguments?.getString("bank")
            val autoSource = backStackEntry.arguments?.getString("source")

            AddExpenseScreen(
                navController = navController, 
                expenseId = expenseId,
                autoAmount = autoAmount,
                autoMerchant = autoMerchant,
                autoBank = autoBank,
                autoSource = autoSource
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
        composable(Screen.ExpenseModes.route) {
            ExpenseModeScreen(navController = navController)
        }
        composable(Screen.ManageCategories.route) {
            ManageCategoriesScreen(navController = navController)
        }
        composable(Screen.ManagePaymentModes.route) {
            ManagePaymentModesScreen(navController = navController)
        }
        composable(Screen.Reports.route) {
            ReportsScreen(navController = navController)
        }
        composable(
            route = Screen.DetailedModeReport.route + "?startDate={startDate}&endDate={endDate}",
            arguments = listOf(
                navArgument("startDate") {
                    type = NavType.LongType
                    defaultValue = 0L
                },
                navArgument("endDate") {
                    type = NavType.LongType
                    defaultValue = Long.MAX_VALUE
                }
            )
        ) { backStackEntry ->
            DetailedModeReportScreen(navController = navController)
        }
        composable(Screen.DataBackup.route) {
            BackupScreen(navController = navController)
        }
        composable(Screen.Security.route) {
            SecurityScreen(navController = navController)
        }
        composable(Screen.UserInstructions.route) {
            UserInstructionsScreen(navController = navController)
        }
        composable(Screen.Automation.route) {
            AutomationScreen(navController = navController)
        }
    }
}

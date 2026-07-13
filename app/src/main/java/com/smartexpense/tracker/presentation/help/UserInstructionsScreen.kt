package com.smartexpense.tracker.presentation.help

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInstructionsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("How to Use") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                InstructionSection(
                    title = "📊 Dashboard",
                    steps = listOf(
                        "View your recent expenses at a glance.",
                        "If an Expense Mode is active, only that mode's expenses are shown.",
                        "Otherwise, all expenses are displayed.",
                        "Tap the + button or go to Add Expense to record a new expense."
                    )
                )
            }
            item {
                InstructionSection(
                    title = "➕ Adding an Expense",
                    steps = listOf(
                        "Enter the Amount in ₹.",
                        "Enter the Merchant or payee name.",
                        "Tap a colorful Category button to tag the expense.",
                        "Press Save Expense to store it.",
                        "If a Mode is active, it is shown at the top and auto-tagged."
                    )
                )
            }
            item {
                InstructionSection(
                    title = "🏷️ Expense Modes",
                    steps = listOf(
                        "Go to Settings → Manage Expense Modes.",
                        "Enter a name (e.g., Goa Trip) and press Start.",
                        "All new expenses will be tagged to this mode.",
                        "Tap Deactivate Mode to stop grouping.",
                        "Tap Restart on a past mode to re-enable it.",
                        "On Dashboard, only the active mode's expenses are shown."
                    )
                )
            }
            item {
                InstructionSection(
                    title = "📁 Categories",
                    steps = listOf(
                        "Go to Settings → Manage Categories.",
                        "Tap Add to create a new category.",
                        "Use the Edit icon to rename a category.",
                        "Use the Delete icon to remove it.",
                        "Categories appear as colorful buttons in Add Expense."
                    )
                )
            }
            item {
                InstructionSection(
                    title = "📈 Reports",
                    steps = listOf(
                        "Go to the Reports tab.",
                        "Choose a time period: Daily, Weekly, Monthly, or Yearly.",
                        "Select a report type: Category, Purpose, Merchant, Bank, Payment Mode, or Location.",
                        "Tap View Detailed Mode-wise Report for a full breakdown of expenses by mode with location.",
                        "Use the Export button to export data as CSV."
                    )
                )
            }
            item {
                InstructionSection(
                    title = "📲 SMS Detection",
                    steps = listOf(
                        "Grant SMS permission when prompted.",
                        "The app automatically reads bank SMS messages.",
                        "Detected transactions create a notification.",
                        "Tap the notification to review or confirm the expense."
                    )
                )
            }
            item {
                InstructionSection(
                    title = "🔒 Privacy & Security",
                    steps = listOf(
                        "Go to Settings → Privacy & Security.",
                        "Enter a 4-digit PIN and tap Enable PIN Lock.",
                        "The PIN is required on every app open.",
                        "Tap Disable PIN Lock to remove it."
                    )
                )
            }
            item {
                InstructionSection(
                    title = "💾 Data & Backup",
                    steps = listOf(
                        "Go to Settings → Data & Backup.",
                        "Tap Export to CSV to save all expenses.",
                        "The file is saved to your Downloads folder.",
                        "Open it in Excel or Google Sheets for analysis."
                    )
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun InstructionSection(title: String, steps: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            steps.forEachIndexed { index, step ->
                Text(
                    text = "${index + 1}. $step",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}

package com.smartexpense.tracker.presentation.add_expense

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(
    navController: NavController,
    viewModel: AddExpenseViewModel = hiltViewModel(),
    expenseId: Long? = null,
    autoAmount: String? = null,
    autoMerchant: String? = null,
    autoBank: String? = null,
    autoSource: String? = null
) {
    val amount by viewModel.amount.collectAsState()
    val merchant by viewModel.merchant.collectAsState()
    val note by viewModel.note.collectAsState()
    val date by viewModel.date.collectAsState()
    val category by viewModel.category.collectAsState()
    val paymentMode by viewModel.paymentMode.collectAsState()
    val paidBy by viewModel.paidBy.collectAsState()
    val paymentModes by viewModel.paymentModes.collectAsState()
    val allExpenseModes by viewModel.allExpenseModes.collectAsState()
    val selectedExpenseMode by viewModel.selectedExpenseMode.collectAsState()

    LaunchedEffect(expenseId) {
        if (expenseId != null && expenseId != 0L) {
            viewModel.loadExpense(expenseId)
        } else if (autoAmount != null) {
            viewModel.populateFromAutoSync(autoAmount, autoMerchant, autoBank, autoSource)
        }
    }

    // Always default to Manual Entry (0) because Auto-Sync (1) is a settings page
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (expenseId != null) "Edit Expense" else "Add Expense") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    text = { Text("Manual Entry", style = MaterialTheme.typography.titleSmall) }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    text = { Text("Auto-Sync (Email)", style = MaterialTheme.typography.titleSmall) }
                )
            }
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp) // Fiscal Precision container-margin
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(24.dp)) // section-padding
                
                if (selectedTabIndex == 0) {
                    // --- Manual Entry View ---
                    
                    var modeExpanded by remember { mutableStateOf(false) }
                    ExposedDropdownMenuBox(
                        expanded = modeExpanded,
                        onExpandedChange = { modeExpanded = !modeExpanded }
                    ) {
                        OutlinedTextField(
                            value = selectedExpenseMode?.name ?: "General (No Mode)",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Expense Mode") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = modeExpanded) },
                            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                            modifier = Modifier.fillMaxWidth().menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = modeExpanded,
                            onDismissRequest = { modeExpanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("General (No Mode)") },
                                onClick = {
                                    viewModel.updateSelectedExpenseMode(null)
                                    modeExpanded = false
                                }
                            )
                            allExpenseModes.forEach { mode ->
                                DropdownMenuItem(
                                    text = { Text(mode.name) },
                                    onClick = {
                                        viewModel.updateSelectedExpenseMode(mode)
                                        modeExpanded = false
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    if (autoSource != null) {
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Automatic Tracking ($autoSource)",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Expense details parsed automatically. Please review and save.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                    }

                    // Prominent Amount Input
                    OutlinedTextField(
                        value = amount,
                        onValueChange = viewModel::updateAmount,
                        label = { Text("Amount") },
                        prefix = { Text("₹") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                        )
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    val datePickerState = rememberDatePickerState()
                    var showDatePicker by remember { mutableStateOf(false) }

                    if (showDatePicker) {
                        DatePickerDialog(
                            onDismissRequest = { showDatePicker = false },
                            confirmButton = {
                                TextButton(onClick = {
                                    showDatePicker = false
                                    datePickerState.selectedDateMillis?.let { millis ->
                                        val formatter = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                                        viewModel.updateDate(formatter.format(java.util.Date(millis)))
                                    }
                                }) {
                                    Text("OK")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showDatePicker = false }) {
                                    Text("Cancel")
                                }
                            }
                        ) {
                            DatePicker(state = datePickerState)
                        }
                    }

                    // Date
                    Box(modifier = Modifier.fillMaxWidth().clickable { showDatePicker = true }) {
                        OutlinedTextField(
                            value = date,
                            onValueChange = {},
                            label = { Text("Date") },
                            readOnly = true,
                            enabled = false,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledBorderColor = MaterialTheme.colorScheme.outlineVariant,
                                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    OutlinedTextField(
                        value = merchant,
                        onValueChange = viewModel::updateMerchant,
                        label = { Text("Merchant") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                        )
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = note,
                        onValueChange = viewModel::updateNote,
                        label = { Text("Note") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                        )
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    val categories by viewModel.categories.collectAsState()
                    
                    Text("Category", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.heightIn(max = 150.dp)
                    ) {
                        items(categories) { cat ->
                            val isSelected = cat.name == category
                            FilterChip(
                                selected = isSelected,
                                onClick = { viewModel.updateCategory(cat.name) },
                                label = { Text(cat.name, maxLines = 1, style = MaterialTheme.typography.bodySmall) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    Button(
                        onClick = {
                            viewModel.saveExpense {
                                navController.navigate("dashboard") {
                                    popUpTo("dashboard") { inclusive = false }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp), // Touch target
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary, // Emerald Green
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        ),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            text = if (expenseId != null) "Update Expense" else "Save Expense",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                } else {
                    // --- Auto-Sync (Email) View ---
                    
                    val context = androidx.compose.ui.platform.LocalContext.current
                    val prefs = context.getSharedPreferences("app_settings", android.content.Context.MODE_PRIVATE)
                    var readEmailsEnabled by remember { mutableStateOf(prefs.getBoolean("read_email_notifications", false)) }
                    
                    Text(
                        text = "Automatic Tracking",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "SmartExpenseTracker can securely read your transaction emails to automatically populate expense forms. You review every entry before it is saved.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Read Email & SMS Notifications", style = MaterialTheme.typography.titleMedium)
                                Text(
                                    "Automatically capture expenses from credit card and bank alerts.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Switch(
                                checked = readEmailsEnabled,
                                onCheckedChange = { isChecked ->
                                    if (isChecked) {
                                        val intent = android.content.Intent(android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
                                        context.startActivity(intent)
                                        android.widget.Toast.makeText(context, "Please enable Notification Access for Smart Expense Tracker", android.widget.Toast.LENGTH_LONG).show()
                                        prefs.edit().putBoolean("read_email_notifications", true).apply()
                                        readEmailsEnabled = true
                                    } else {
                                        prefs.edit().putBoolean("read_email_notifications", false).apply()
                                        readEmailsEnabled = false
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

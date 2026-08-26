package com.smartexpense.tracker.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseModeScreen(
    navController: NavController,
    viewModel: ExpenseModeViewModel = hiltViewModel()
) {
    val activeMode by viewModel.activeMode.collectAsState()
    val allModes by viewModel.allModes.collectAsState()
    val newModeName by viewModel.newModeName.collectAsState()
    val showDeleteDialog = remember { mutableStateOf(false) }
    val modeToDelete = remember { mutableStateOf<com.smartexpense.tracker.data.local.entity.ExpenseModeEntity?>(null) }

    if (showDeleteDialog.value && modeToDelete.value != null) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog.value = false
                modeToDelete.value = null
            },
            title = { Text("Delete Expense Mode?") },
            text = { Text("Are you sure you want to delete '${modeToDelete.value?.name}'? This will permanently delete ALL expenses associated with this mode. This action cannot be undone.") },
            confirmButton = {
                Button(
                    onClick = {
                        modeToDelete.value?.let { viewModel.deleteExpenseMode(it) }
                        showDeleteDialog.value = false
                        modeToDelete.value = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteDialog.value = false
                    modeToDelete.value = null
                }) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expense Modes") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                "When an Expense Mode is active, all new expenses are automatically tagged to it.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Active Mode Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (activeMode != null) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    if (activeMode != null) {
                        Text("Currently Active:", style = MaterialTheme.typography.labelMedium)
                        Text(activeMode!!.name, style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.deactivateCurrentMode() }) {
                            Text("Deactivate Mode")
                        }
                    } else {
                        Text("No Active Mode", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Add New Mode
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = newModeName,
                    onValueChange = viewModel::updateNewModeName,
                    label = { Text("New Mode Name (e.g. Goa Trip)") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { viewModel.createNewMode() },
                    enabled = newModeName.isNotBlank()
                ) {
                    Text("Start")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Mode History", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(allModes, key = { it.id }) { mode ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(mode.name, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    "Created: ${SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(mode.createdAt))}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            if (mode.isActive) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = "Active", tint = MaterialTheme.colorScheme.primary)
                                    IconButton(onClick = {
                                        modeToDelete.value = mode
                                        showDeleteDialog.value = true
                                    }) {
                                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                                    }
                                }
                            } else {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    TextButton(onClick = { viewModel.reenableMode(mode) }) {
                                        Text("Restart")
                                    }
                                    IconButton(onClick = {
                                        modeToDelete.value = mode
                                        showDeleteDialog.value = true
                                    }) {
                                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCategoriesScreen(
    navController: NavController,
    viewModel: ManageCategoriesViewModel = hiltViewModel()
) {
    val categories by viewModel.categories.collectAsState()
    val showAddDialog = remember { mutableStateOf(false) }
    val editCategory = remember { mutableStateOf<com.smartexpense.tracker.data.local.entity.CategoryEntity?>(null) }
    val categoryNameInput = remember { mutableStateOf("") }

    if (showAddDialog.value || editCategory.value != null) {
        AlertDialog(
            onDismissRequest = {
                showAddDialog.value = false
                editCategory.value = null
                categoryNameInput.value = ""
            },
            title = { Text(if (editCategory.value != null) "Edit Category" else "Add Category") },
            text = {
                OutlinedTextField(
                    value = categoryNameInput.value,
                    onValueChange = { categoryNameInput.value = it },
                    label = { Text("Category Name") },
                    singleLine = true
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        val name = categoryNameInput.value.trim()
                        if (name.isNotEmpty()) {
                            if (editCategory.value != null) {
                                viewModel.updateCategory(editCategory.value!!.copy(name = name))
                            } else {
                                viewModel.addCategory(name)
                            }
                        }
                        showAddDialog.value = false
                        editCategory.value = null
                        categoryNameInput.value = ""
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showAddDialog.value = false
                    editCategory.value = null
                    categoryNameInput.value = ""
                }) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Categories") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Button(
                        onClick = { showAddDialog.value = true },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Add")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(categories, key = { it.id }) { category ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(category.name, style = MaterialTheme.typography.titleMedium)
                            Row {
                                IconButton(onClick = {
                                    editCategory.value = category
                                    categoryNameInput.value = category.name
                                }) {
                                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                                }
                                IconButton(onClick = { viewModel.deleteCategory(category) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagePaymentModesScreen(
    navController: NavController,
    viewModel: PaymentModeViewModel = hiltViewModel()
) {
    val paymentModes by viewModel.paymentModes.collectAsState()
    val showAddDialog = remember { mutableStateOf(false) }
    val paymentModeNameInput = remember { mutableStateOf("") }

    if (showAddDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showAddDialog.value = false
                paymentModeNameInput.value = ""
            },
            title = { Text("Add Payment Mode") },
            text = {
                OutlinedTextField(
                    value = paymentModeNameInput.value,
                    onValueChange = { paymentModeNameInput.value = it },
                    label = { Text("Payment Mode (e.g. Cash, Credit Card)") },
                    singleLine = true
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        val name = paymentModeNameInput.value.trim()
                        if (name.isNotEmpty()) {
                            viewModel.addPaymentMode(name)
                        }
                        showAddDialog.value = false
                        paymentModeNameInput.value = ""
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showAddDialog.value = false
                    paymentModeNameInput.value = ""
                }) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Payment Modes") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Button(
                        onClick = { showAddDialog.value = true },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Add")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(paymentModes, key = { it.id }) { mode ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(mode.name, style = MaterialTheme.typography.titleMedium)
                            IconButton(onClick = { viewModel.deletePaymentMode(mode) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutomationScreen(
    navController: NavController
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val prefs = context.getSharedPreferences("app_settings", android.content.Context.MODE_PRIVATE)
    var readEmailsEnabled by remember { mutableStateOf(prefs.getBoolean("read_email_notifications", false)) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Automation") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Automate your expense tracking by allowing the app to read notifications.",
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
                        Text("Read Email Notifications", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "Automatically capture expenses from credit card email alerts.",
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

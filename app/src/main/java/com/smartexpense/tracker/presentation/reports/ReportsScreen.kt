package com.smartexpense.tracker.presentation.reports

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.smartexpense.tracker.utils.ExportHelper
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    navController: NavController,
    viewModel: ReportsViewModel = hiltViewModel()
) {
    val reportData by viewModel.reportData.collectAsState()
    val totalSpent by viewModel.totalSpent.collectAsState()
    val rawExpenses by viewModel.rawExpenses.collectAsState()
    val fromDate by viewModel.fromDate.collectAsState()
    val toDate by viewModel.toDate.collectAsState()
    val selectedGrouping by viewModel.grouping.collectAsState()
    
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Analytics & Reports") },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Export")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Export to CSV") },
                            onClick = {
                                showMenu = false
                                coroutineScope.launch {
                                    val uri = ExportHelper.exportExpensesToCsv(context, rawExpenses)
                                    if (uri != null) {
                                        ExportHelper.shareCsvFile(context, uri)
                                    } else {
                                        Toast.makeText(context, "Export Failed", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        )
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
            // Filters
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Date Pickers
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                
                var showFromDatePicker by remember { mutableStateOf(false) }
                if (showFromDatePicker) {
                    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = fromDate)
                    DatePickerDialog(
                        onDismissRequest = { showFromDatePicker = false },
                        confirmButton = {
                            TextButton(onClick = {
                                datePickerState.selectedDateMillis?.let { viewModel.setFromDate(it) }
                                showFromDatePicker = false
                            }) { Text("OK") }
                        },
                        dismissButton = {
                            TextButton(onClick = { showFromDatePicker = false }) { Text("Cancel") }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }

                var showToDatePicker by remember { mutableStateOf(false) }
                if (showToDatePicker) {
                    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = toDate)
                    DatePickerDialog(
                        onDismissRequest = { showToDatePicker = false },
                        confirmButton = {
                            TextButton(onClick = {
                                datePickerState.selectedDateMillis?.let { viewModel.setToDate(it) }
                                showToDatePicker = false
                            }) { Text("OK") }
                        },
                        dismissButton = {
                            TextButton(onClick = { showToDatePicker = false }) { Text("Cancel") }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }

                OutlinedButton(
                    onClick = { showFromDatePicker = true },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(dateFormat.format(Date(fromDate)))
                }

                OutlinedButton(
                    onClick = { showToDatePicker = true },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(dateFormat.format(Date(toDate)))
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                // Grouping Dropdown
                var groupExpanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = groupExpanded,
                    onExpandedChange = { groupExpanded = it },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = selectedGrouping.name,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Group By") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = groupExpanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = groupExpanded,
                        onDismissRequest = { groupExpanded = false }
                    ) {
                        ReportGrouping.values().forEach { group ->
                            DropdownMenuItem(
                                text = { Text(group.name) },
                                onClick = {
                                    viewModel.setGrouping(group)
                                    groupExpanded = false
                                }
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { 
                    navController.navigate(com.smartexpense.tracker.presentation.navigation.Screen.DetailedModeReport.route + "?startDate=$fromDate&endDate=$toDate") 
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Detailed Mode-wise Report")
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (totalSpent > 0 && reportData.isNotEmpty()) {
                Text("Total Spend: ₹$totalSpent", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text("Group", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                    Text("Amount (₹)", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, textAlign = androidx.compose.ui.text.style.TextAlign.End)
                }
                Divider()

                LazyColumn {
                    items(reportData) { item ->
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
                            Text(item.groupName ?: "Unknown", modifier = Modifier.weight(1f))
                            Text(String.format("%.2f", item.totalAmount), modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.End)
                        }
                        Divider(color = MaterialTheme.colorScheme.surfaceVariant)
                    }
                    
                    item {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
                            Text("Total", modifier = Modifier.weight(1f), fontWeight = FontWeight.ExtraBold)
                            Text(String.format("%.2f", totalSpent), modifier = Modifier.weight(1f), fontWeight = FontWeight.ExtraBold, textAlign = androidx.compose.ui.text.style.TextAlign.End)
                        }
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No data to display.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

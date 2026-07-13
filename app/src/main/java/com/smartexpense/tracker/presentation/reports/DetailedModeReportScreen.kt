package com.smartexpense.tracker.presentation.reports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import com.smartexpense.tracker.presentation.navigation.Screen
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.smartexpense.tracker.utils.ExportHelper
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.compose.material.icons.filled.MoreVert
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedModeReportScreen(
    navController: NavController,
    viewModel: DetailedModeReportViewModel = hiltViewModel()
) {
    val groupedData by viewModel.detailedModeReportData.collectAsState()
    val rawExpenses by viewModel.rawExpenses.collectAsState()
    
    val context = LocalContext.current
    val coroutineScope = androidx.compose.runtime.rememberCoroutineScope()
    var showMenu by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detailed Mode-wise Report") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
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
    ) { padding ->
        if (groupedData.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text(
                    text = "No detail report found",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                groupedData.forEach { (modeName, expenses) ->
                    item {
                        Text(
                            text = modeName,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                            Text("Date", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                            Text("Merchant", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                            Text("Location", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                            Text("Amount (₹)", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, textAlign = androidx.compose.ui.text.style.TextAlign.End)
                        }
                        Divider()
                    }
                    
                    items(expenses, key = { it.id }) { expense ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navController.navigate(Screen.AddExpense.route + "?expenseId=${expense.id}") }
                                .padding(vertical = 12.dp)
                        ) {
                            Text(SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(Date(expense.timestamp)), modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                            Text(expense.merchant ?: expense.purpose ?: "-", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                            Text(expense.location ?: "-", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                            Text(String.format("%.2f", expense.amount), modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.End, style = MaterialTheme.typography.bodySmall)
                        }
                        Divider(color = MaterialTheme.colorScheme.surfaceVariant)
                    }
                    
                    item {
                        val modeTotal = expenses.sumOf { it.amount }
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
                            Text("Total for $modeName", modifier = Modifier.weight(3f), fontWeight = FontWeight.ExtraBold)
                            Text(String.format("%.2f", modeTotal), modifier = Modifier.weight(1f), fontWeight = FontWeight.ExtraBold, textAlign = androidx.compose.ui.text.style.TextAlign.End)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                
                item {
                    val grandTotal = groupedData.values.flatten().sumOf { it.amount }
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
                        Text("GRAND TOTAL", modifier = Modifier.weight(3f), fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.titleMedium)
                        Text("₹${String.format("%.2f", grandTotal)}", modifier = Modifier.weight(1f), fontWeight = FontWeight.ExtraBold, textAlign = androidx.compose.ui.text.style.TextAlign.End, style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}

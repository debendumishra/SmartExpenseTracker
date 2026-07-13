package com.smartexpense.tracker.presentation.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import com.smartexpense.tracker.presentation.navigation.Screen

@Composable
fun DashboardScreen(
    navController: NavController? = null,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val expenses by viewModel.recentExpenses.collectAsState()
    val totalSpent by viewModel.totalSpent.collectAsState()
    val categoryData by viewModel.categoryData.collectAsState()
    val activeMode by viewModel.activeMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (activeMode != null) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Text(
                    text = "Active Mode: ${activeMode!!.name}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Text(
            text = "Total Spent",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "₹${String.format(Locale.getDefault(), "%.2f", totalSpent)}",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Expenses by Category", style = MaterialTheme.typography.titleMedium)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            if (categoryData.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth().height(150.dp), contentAlignment = Alignment.Center) {
                    Text("No expenses available")
                }
            } else {
                val totalPie = categoryData.sumOf { it.second.toDouble() }.toFloat()
                val colors = listOf(Color(0xFFE57373), Color(0xFF81C784), Color(0xFF64B5F6), Color(0xFFFFD54F), Color(0xFFBA68C8), Color(0xFF4DB6AC))
                
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Canvas(modifier = Modifier.size(120.dp)) {
                        var startAngle = 0f
                        categoryData.forEachIndexed { index, pair ->
                            val sweepAngle = (pair.second / totalPie) * 360f
                            drawArc(
                                color = colors[index % colors.size],
                                startAngle = startAngle,
                                sweepAngle = sweepAngle,
                                useCenter = true,
                                size = Size(size.width, size.height)
                            )
                            startAngle += sweepAngle
                        }
                    }
                    
                    Spacer(modifier = Modifier.width(16.dp))
                    
                    Column {
                        categoryData.take(5).forEachIndexed { index, pair -> // limit legend to top 5
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(12.dp).background(colors[index % colors.size]))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("${pair.first}: ₹${String.format(Locale.getDefault(), "%.1f", pair.second)}", style = MaterialTheme.typography.bodySmall)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Recent Transactions", style = MaterialTheme.typography.titleMedium)
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(expenses) { expense ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            navController?.navigate(Screen.AddExpense.route + "?expenseId=${expense.id}")
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(expense.merchant ?: "Unknown Merchant", fontWeight = FontWeight.SemiBold)
                            Text(
                                text = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(expense.timestamp)),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text(
                            text = "₹${expense.amount}",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}

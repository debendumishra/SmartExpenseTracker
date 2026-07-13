package com.smartexpense.tracker.presentation.transactions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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

import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import com.smartexpense.tracker.presentation.navigation.Screen

@Composable
fun TransactionsScreen(
    navController: NavController? = null,
    viewModel: TransactionsViewModel = hiltViewModel()
) {
    val expenses by viewModel.filteredExpenses.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Transactions History",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = searchQuery,
            onValueChange = viewModel::onSearchQueryChange,
            label = { Text("Search by merchant or category...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        if (expenses.isEmpty()) {
            Text("No transactions found.")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(expenses, key = { it.id }) { expense ->
                    TransactionItem(
                        expense = expense,
                        onDeleteClick = { viewModel.deleteExpense(expense) },
                        onClick = { navController?.navigate(Screen.AddExpense.route + "?expenseId=${expense.id}") }
                    )
                }
            }
        }
    }
}

@Composable
fun TransactionItem(
    expense: com.smartexpense.tracker.data.local.entity.ExpenseEntity,
    onDeleteClick: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(expense.merchant ?: "Unknown Merchant", fontWeight = FontWeight.SemiBold)
                Text(expense.purpose ?: "General", style = MaterialTheme.typography.bodySmall)
                Text(
                    text = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(expense.timestamp)),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Text(
                    text = "₹${expense.amount}",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Delete,
                        contentDescription = "Delete Expense",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

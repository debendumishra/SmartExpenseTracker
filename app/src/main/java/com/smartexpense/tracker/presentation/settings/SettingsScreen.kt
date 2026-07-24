package com.smartexpense.tracker.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.smartexpense.tracker.presentation.navigation.Screen

@Composable
fun SettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            item {
                SettingsItem(
                    title = "Manage Expense Modes",
                    subtitle = "Create tours, picnics, or projects",
                    icon = Icons.Default.List,
                    onClick = { navController.navigate(Screen.ExpenseModes.route) }
                )
            }
            item {
                SettingsItem(
                    title = "Manage Categories",
                    subtitle = "Add or edit expense categories",
                    icon = Icons.Default.Settings,
                    onClick = { navController.navigate(Screen.ManageCategories.route) }
                )
            }
            item {
                SettingsItem(
                    title = "Manage Payment Modes",
                    subtitle = "Add or edit payment methods (e.g. UPI, Cash)",
                    icon = Icons.Default.ShoppingCart,
                    onClick = { navController.navigate(Screen.ManagePaymentModes.route) }
                )
            }
            item {
                SettingsItem(
                    title = "Automation",
                    subtitle = "Read SMS & Email notifications",
                    icon = Icons.Default.Settings,
                    onClick = { navController.navigate(Screen.Automation.route) }
                )
            }
            item {
                SettingsItem(
                    icon = Icons.Default.Share,
                    title = "Data & Backup",
                    subtitle = "Export or import your data",
                    onClick = { navController.navigate(Screen.DataBackup.route) }
                )
            }
            item {
                SettingsItem(
                    icon = Icons.Default.Lock,
                    title = "Privacy & Security",
                    subtitle = "App lock and permissions",
                    onClick = { navController.navigate(Screen.Security.route) }
                )
            }
            item {
                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "How to Use",
                    subtitle = "User guide and instructions",
                    onClick = { navController.navigate(Screen.UserInstructions.route) }
                )
            }
        }
    }
}

@Composable
fun SettingsItem(title: String, subtitle: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

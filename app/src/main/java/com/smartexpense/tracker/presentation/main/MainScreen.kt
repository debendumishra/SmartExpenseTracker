package com.smartexpense.tracker.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.smartexpense.tracker.presentation.navigation.AppNavigation
import com.smartexpense.tracker.presentation.navigation.Screen

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object Dashboard : BottomNavItem("Home", Icons.Default.Home, Screen.Dashboard.route)
    object Transactions : BottomNavItem("History", Icons.Default.List, Screen.Transactions.route)
    object Reports : BottomNavItem("Reports", Icons.Default.Info, Screen.Reports.route)
    object Add : BottomNavItem("Add", Icons.Default.AddCircle, Screen.AddExpense.route)
    object Settings : BottomNavItem("Settings", Icons.Default.Settings, Screen.Settings.route)
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Dashboard,
        BottomNavItem.Transactions,
        BottomNavItem.Reports,
        BottomNavItem.Add,
        BottomNavItem.Settings
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        androidx.compose.foundation.layout.Box(modifier = Modifier.padding(paddingValues)) {
            AppNavigation(navController = navController)
        }
    }
}

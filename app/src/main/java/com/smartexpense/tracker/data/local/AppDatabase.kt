package com.smartexpense.tracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smartexpense.tracker.data.local.dao.BankDao
import com.smartexpense.tracker.data.local.dao.CategoryDao
import com.smartexpense.tracker.data.local.dao.ExpenseDao
import com.smartexpense.tracker.data.local.dao.ExpenseModeDao
import com.smartexpense.tracker.data.local.dao.PaymentModeDao
import com.smartexpense.tracker.data.local.entity.BankEntity
import com.smartexpense.tracker.data.local.entity.CategoryEntity
import com.smartexpense.tracker.data.local.entity.ExpenseEntity
import com.smartexpense.tracker.data.local.entity.ExpenseModeEntity
import com.smartexpense.tracker.data.local.entity.PaymentModeEntity
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration

@Database(
    entities = [
        ExpenseEntity::class,
        CategoryEntity::class,
        ExpenseModeEntity::class,
        BankEntity::class,
        PaymentModeEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseModeDao(): ExpenseModeDao
    abstract fun bankDao(): BankDao
    abstract fun paymentModeDao(): PaymentModeDao
    
    companion object {
        const val DATABASE_NAME = "smart_expense_db"
        
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create payment_modes table
                database.execSQL("CREATE TABLE IF NOT EXISTS `payment_modes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL)")
                
                // Add default payment modes
                database.execSQL("INSERT INTO `payment_modes` (name) VALUES ('Cash'), ('Credit Card'), ('Debit Card'), ('UPI'), ('Net Banking')")
                
                // Add new column to expenses table
                database.execSQL("ALTER TABLE `expenses` ADD COLUMN `paidBy` TEXT")
            }
        }
    }
}

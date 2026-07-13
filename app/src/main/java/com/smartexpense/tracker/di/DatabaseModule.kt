package com.smartexpense.tracker.di

import android.content.Context
import androidx.room.Room
import com.smartexpense.tracker.data.local.AppDatabase
import com.smartexpense.tracker.data.local.dao.BankDao
import com.smartexpense.tracker.data.local.dao.CategoryDao
import com.smartexpense.tracker.data.local.dao.ExpenseDao
import com.smartexpense.tracker.data.local.dao.ExpenseModeDao
import com.smartexpense.tracker.data.local.dao.PaymentModeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
        .addMigrations(AppDatabase.MIGRATION_1_2)
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    @Singleton
    fun provideExpenseDao(database: AppDatabase): ExpenseDao {
        return database.expenseDao()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: AppDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    @Singleton
    fun provideExpenseModeDao(database: AppDatabase): ExpenseModeDao {
        return database.expenseModeDao()
    }

    @Provides
    @Singleton
    fun provideBankDao(database: AppDatabase): BankDao {
        return database.bankDao()
    }

    @Provides
    @Singleton
    fun providePaymentModeDao(database: AppDatabase): PaymentModeDao {
        return database.paymentModeDao()
    }
}

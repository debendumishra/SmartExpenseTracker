package com.smartexpense.tracker.di

import com.smartexpense.tracker.data.repository.ExpenseModeRepositoryImpl
import com.smartexpense.tracker.data.repository.ExpenseRepositoryImpl
import com.smartexpense.tracker.data.repository.CategoryRepositoryImpl
import com.smartexpense.tracker.domain.repository.ExpenseModeRepository
import com.smartexpense.tracker.domain.repository.ExpenseRepository
import com.smartexpense.tracker.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExpenseRepository(
        expenseRepositoryImpl: ExpenseRepositoryImpl
    ): ExpenseRepository

    @Binds
    @Singleton
    abstract fun bindExpenseModeRepository(
        expenseModeRepositoryImpl: ExpenseModeRepositoryImpl
    ): ExpenseModeRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository
}

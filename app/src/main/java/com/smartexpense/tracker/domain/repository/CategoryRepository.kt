package com.smartexpense.tracker.domain.repository

import com.smartexpense.tracker.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun insertCategory(category: CategoryEntity): Long
    suspend fun updateCategory(category: CategoryEntity)
    suspend fun deleteCategory(category: CategoryEntity)
    fun getAllCategories(): Flow<List<CategoryEntity>>
}

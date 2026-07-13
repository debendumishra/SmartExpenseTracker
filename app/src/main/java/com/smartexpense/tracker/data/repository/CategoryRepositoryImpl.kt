package com.smartexpense.tracker.data.repository

import com.smartexpense.tracker.data.local.dao.CategoryDao
import com.smartexpense.tracker.data.local.entity.CategoryEntity
import com.smartexpense.tracker.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dao: CategoryDao
) : CategoryRepository {
    override suspend fun insertCategory(category: CategoryEntity): Long {
        return dao.insertCategory(category)
    }

    override suspend fun updateCategory(category: CategoryEntity) {
        dao.updateCategory(category)
    }

    override suspend fun deleteCategory(category: CategoryEntity) {
        dao.deleteCategory(category)
    }

    override fun getAllCategories(): Flow<List<CategoryEntity>> {
        return dao.getAllCategories()
    }
}

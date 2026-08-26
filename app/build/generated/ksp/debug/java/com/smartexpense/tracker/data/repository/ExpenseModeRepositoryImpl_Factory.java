package com.smartexpense.tracker.data.repository;

import com.smartexpense.tracker.data.local.dao.ExpenseDao;
import com.smartexpense.tracker.data.local.dao.ExpenseModeDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ExpenseModeRepositoryImpl_Factory implements Factory<ExpenseModeRepositoryImpl> {
  private final Provider<ExpenseModeDao> daoProvider;

  private final Provider<ExpenseDao> expenseDaoProvider;

  public ExpenseModeRepositoryImpl_Factory(Provider<ExpenseModeDao> daoProvider,
      Provider<ExpenseDao> expenseDaoProvider) {
    this.daoProvider = daoProvider;
    this.expenseDaoProvider = expenseDaoProvider;
  }

  @Override
  public ExpenseModeRepositoryImpl get() {
    return newInstance(daoProvider.get(), expenseDaoProvider.get());
  }

  public static ExpenseModeRepositoryImpl_Factory create(Provider<ExpenseModeDao> daoProvider,
      Provider<ExpenseDao> expenseDaoProvider) {
    return new ExpenseModeRepositoryImpl_Factory(daoProvider, expenseDaoProvider);
  }

  public static ExpenseModeRepositoryImpl newInstance(ExpenseModeDao dao, ExpenseDao expenseDao) {
    return new ExpenseModeRepositoryImpl(dao, expenseDao);
  }
}

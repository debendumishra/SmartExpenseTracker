package com.smartexpense.tracker.presentation.dashboard;

import com.smartexpense.tracker.domain.repository.ExpenseModeRepository;
import com.smartexpense.tracker.domain.repository.ExpenseRepository;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<ExpenseRepository> expenseRepositoryProvider;

  private final Provider<ExpenseModeRepository> expenseModeRepositoryProvider;

  public DashboardViewModel_Factory(Provider<ExpenseRepository> expenseRepositoryProvider,
      Provider<ExpenseModeRepository> expenseModeRepositoryProvider) {
    this.expenseRepositoryProvider = expenseRepositoryProvider;
    this.expenseModeRepositoryProvider = expenseModeRepositoryProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(expenseRepositoryProvider.get(), expenseModeRepositoryProvider.get());
  }

  public static DashboardViewModel_Factory create(
      Provider<ExpenseRepository> expenseRepositoryProvider,
      Provider<ExpenseModeRepository> expenseModeRepositoryProvider) {
    return new DashboardViewModel_Factory(expenseRepositoryProvider, expenseModeRepositoryProvider);
  }

  public static DashboardViewModel newInstance(ExpenseRepository expenseRepository,
      ExpenseModeRepository expenseModeRepository) {
    return new DashboardViewModel(expenseRepository, expenseModeRepository);
  }
}

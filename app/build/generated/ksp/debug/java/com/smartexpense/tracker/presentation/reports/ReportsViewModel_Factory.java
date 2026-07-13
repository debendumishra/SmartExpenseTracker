package com.smartexpense.tracker.presentation.reports;

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
public final class ReportsViewModel_Factory implements Factory<ReportsViewModel> {
  private final Provider<ExpenseRepository> expenseRepositoryProvider;

  public ReportsViewModel_Factory(Provider<ExpenseRepository> expenseRepositoryProvider) {
    this.expenseRepositoryProvider = expenseRepositoryProvider;
  }

  @Override
  public ReportsViewModel get() {
    return newInstance(expenseRepositoryProvider.get());
  }

  public static ReportsViewModel_Factory create(
      Provider<ExpenseRepository> expenseRepositoryProvider) {
    return new ReportsViewModel_Factory(expenseRepositoryProvider);
  }

  public static ReportsViewModel newInstance(ExpenseRepository expenseRepository) {
    return new ReportsViewModel(expenseRepository);
  }
}

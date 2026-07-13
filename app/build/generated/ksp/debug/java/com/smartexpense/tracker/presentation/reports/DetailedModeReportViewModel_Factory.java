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
public final class DetailedModeReportViewModel_Factory implements Factory<DetailedModeReportViewModel> {
  private final Provider<ExpenseRepository> expenseRepositoryProvider;

  public DetailedModeReportViewModel_Factory(
      Provider<ExpenseRepository> expenseRepositoryProvider) {
    this.expenseRepositoryProvider = expenseRepositoryProvider;
  }

  @Override
  public DetailedModeReportViewModel get() {
    return newInstance(expenseRepositoryProvider.get());
  }

  public static DetailedModeReportViewModel_Factory create(
      Provider<ExpenseRepository> expenseRepositoryProvider) {
    return new DetailedModeReportViewModel_Factory(expenseRepositoryProvider);
  }

  public static DetailedModeReportViewModel newInstance(ExpenseRepository expenseRepository) {
    return new DetailedModeReportViewModel(expenseRepository);
  }
}

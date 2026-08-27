package com.smartexpense.tracker.presentation.reports;

import androidx.lifecycle.SavedStateHandle;
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

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public DetailedModeReportViewModel_Factory(Provider<ExpenseRepository> expenseRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.expenseRepositoryProvider = expenseRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public DetailedModeReportViewModel get() {
    return newInstance(expenseRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static DetailedModeReportViewModel_Factory create(
      Provider<ExpenseRepository> expenseRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new DetailedModeReportViewModel_Factory(expenseRepositoryProvider, savedStateHandleProvider);
  }

  public static DetailedModeReportViewModel newInstance(ExpenseRepository expenseRepository,
      SavedStateHandle savedStateHandle) {
    return new DetailedModeReportViewModel(expenseRepository, savedStateHandle);
  }
}

package com.smartexpense.tracker.presentation.backup;

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
public final class BackupViewModel_Factory implements Factory<BackupViewModel> {
  private final Provider<ExpenseRepository> expenseRepositoryProvider;

  public BackupViewModel_Factory(Provider<ExpenseRepository> expenseRepositoryProvider) {
    this.expenseRepositoryProvider = expenseRepositoryProvider;
  }

  @Override
  public BackupViewModel get() {
    return newInstance(expenseRepositoryProvider.get());
  }

  public static BackupViewModel_Factory create(
      Provider<ExpenseRepository> expenseRepositoryProvider) {
    return new BackupViewModel_Factory(expenseRepositoryProvider);
  }

  public static BackupViewModel newInstance(ExpenseRepository expenseRepository) {
    return new BackupViewModel(expenseRepository);
  }
}

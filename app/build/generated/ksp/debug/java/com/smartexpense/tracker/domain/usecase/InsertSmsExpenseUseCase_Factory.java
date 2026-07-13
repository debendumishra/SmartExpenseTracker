package com.smartexpense.tracker.domain.usecase;

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
public final class InsertSmsExpenseUseCase_Factory implements Factory<InsertSmsExpenseUseCase> {
  private final Provider<ExpenseRepository> expenseRepositoryProvider;

  private final Provider<ExpenseModeRepository> modeRepositoryProvider;

  public InsertSmsExpenseUseCase_Factory(Provider<ExpenseRepository> expenseRepositoryProvider,
      Provider<ExpenseModeRepository> modeRepositoryProvider) {
    this.expenseRepositoryProvider = expenseRepositoryProvider;
    this.modeRepositoryProvider = modeRepositoryProvider;
  }

  @Override
  public InsertSmsExpenseUseCase get() {
    return newInstance(expenseRepositoryProvider.get(), modeRepositoryProvider.get());
  }

  public static InsertSmsExpenseUseCase_Factory create(
      Provider<ExpenseRepository> expenseRepositoryProvider,
      Provider<ExpenseModeRepository> modeRepositoryProvider) {
    return new InsertSmsExpenseUseCase_Factory(expenseRepositoryProvider, modeRepositoryProvider);
  }

  public static InsertSmsExpenseUseCase newInstance(ExpenseRepository expenseRepository,
      ExpenseModeRepository modeRepository) {
    return new InsertSmsExpenseUseCase(expenseRepository, modeRepository);
  }
}

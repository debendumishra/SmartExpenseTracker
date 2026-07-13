package com.smartexpense.tracker.presentation.transactions;

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
public final class TransactionsViewModel_Factory implements Factory<TransactionsViewModel> {
  private final Provider<ExpenseRepository> expenseRepositoryProvider;

  public TransactionsViewModel_Factory(Provider<ExpenseRepository> expenseRepositoryProvider) {
    this.expenseRepositoryProvider = expenseRepositoryProvider;
  }

  @Override
  public TransactionsViewModel get() {
    return newInstance(expenseRepositoryProvider.get());
  }

  public static TransactionsViewModel_Factory create(
      Provider<ExpenseRepository> expenseRepositoryProvider) {
    return new TransactionsViewModel_Factory(expenseRepositoryProvider);
  }

  public static TransactionsViewModel newInstance(ExpenseRepository expenseRepository) {
    return new TransactionsViewModel(expenseRepository);
  }
}

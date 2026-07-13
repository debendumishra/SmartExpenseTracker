package com.smartexpense.tracker.presentation.add_expense;

import android.content.Context;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.smartexpense.tracker.domain.repository.CategoryRepository;
import com.smartexpense.tracker.domain.repository.ExpenseModeRepository;
import com.smartexpense.tracker.domain.repository.ExpenseRepository;
import com.smartexpense.tracker.domain.repository.PaymentModeRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AddExpenseViewModel_Factory implements Factory<AddExpenseViewModel> {
  private final Provider<ExpenseRepository> expenseRepositoryProvider;

  private final Provider<ExpenseModeRepository> modeRepositoryProvider;

  private final Provider<CategoryRepository> categoryRepositoryProvider;

  private final Provider<PaymentModeRepository> paymentModeRepositoryProvider;

  private final Provider<FusedLocationProviderClient> fusedLocationClientProvider;

  private final Provider<Context> contextProvider;

  public AddExpenseViewModel_Factory(Provider<ExpenseRepository> expenseRepositoryProvider,
      Provider<ExpenseModeRepository> modeRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<PaymentModeRepository> paymentModeRepositoryProvider,
      Provider<FusedLocationProviderClient> fusedLocationClientProvider,
      Provider<Context> contextProvider) {
    this.expenseRepositoryProvider = expenseRepositoryProvider;
    this.modeRepositoryProvider = modeRepositoryProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
    this.paymentModeRepositoryProvider = paymentModeRepositoryProvider;
    this.fusedLocationClientProvider = fusedLocationClientProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public AddExpenseViewModel get() {
    return newInstance(expenseRepositoryProvider.get(), modeRepositoryProvider.get(), categoryRepositoryProvider.get(), paymentModeRepositoryProvider.get(), fusedLocationClientProvider.get(), contextProvider.get());
  }

  public static AddExpenseViewModel_Factory create(
      Provider<ExpenseRepository> expenseRepositoryProvider,
      Provider<ExpenseModeRepository> modeRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<PaymentModeRepository> paymentModeRepositoryProvider,
      Provider<FusedLocationProviderClient> fusedLocationClientProvider,
      Provider<Context> contextProvider) {
    return new AddExpenseViewModel_Factory(expenseRepositoryProvider, modeRepositoryProvider, categoryRepositoryProvider, paymentModeRepositoryProvider, fusedLocationClientProvider, contextProvider);
  }

  public static AddExpenseViewModel newInstance(ExpenseRepository expenseRepository,
      ExpenseModeRepository modeRepository, CategoryRepository categoryRepository,
      PaymentModeRepository paymentModeRepository, FusedLocationProviderClient fusedLocationClient,
      Context context) {
    return new AddExpenseViewModel(expenseRepository, modeRepository, categoryRepository, paymentModeRepository, fusedLocationClient, context);
  }
}

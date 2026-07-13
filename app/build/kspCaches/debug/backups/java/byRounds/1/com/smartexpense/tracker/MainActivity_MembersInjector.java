package com.smartexpense.tracker;

import com.smartexpense.tracker.domain.usecase.InsertSmsExpenseUseCase;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<InsertSmsExpenseUseCase> insertSmsExpenseUseCaseProvider;

  public MainActivity_MembersInjector(
      Provider<InsertSmsExpenseUseCase> insertSmsExpenseUseCaseProvider) {
    this.insertSmsExpenseUseCaseProvider = insertSmsExpenseUseCaseProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<InsertSmsExpenseUseCase> insertSmsExpenseUseCaseProvider) {
    return new MainActivity_MembersInjector(insertSmsExpenseUseCaseProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectInsertSmsExpenseUseCase(instance, insertSmsExpenseUseCaseProvider.get());
  }

  @InjectedFieldSignature("com.smartexpense.tracker.MainActivity.insertSmsExpenseUseCase")
  public static void injectInsertSmsExpenseUseCase(MainActivity instance,
      InsertSmsExpenseUseCase insertSmsExpenseUseCase) {
    instance.insertSmsExpenseUseCase = insertSmsExpenseUseCase;
  }
}

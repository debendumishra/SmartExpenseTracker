package com.smartexpense.tracker.services;

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
public final class SmsReceiver_MembersInjector implements MembersInjector<SmsReceiver> {
  private final Provider<InsertSmsExpenseUseCase> insertSmsExpenseUseCaseProvider;

  public SmsReceiver_MembersInjector(
      Provider<InsertSmsExpenseUseCase> insertSmsExpenseUseCaseProvider) {
    this.insertSmsExpenseUseCaseProvider = insertSmsExpenseUseCaseProvider;
  }

  public static MembersInjector<SmsReceiver> create(
      Provider<InsertSmsExpenseUseCase> insertSmsExpenseUseCaseProvider) {
    return new SmsReceiver_MembersInjector(insertSmsExpenseUseCaseProvider);
  }

  @Override
  public void injectMembers(SmsReceiver instance) {
    injectInsertSmsExpenseUseCase(instance, insertSmsExpenseUseCaseProvider.get());
  }

  @InjectedFieldSignature("com.smartexpense.tracker.services.SmsReceiver.insertSmsExpenseUseCase")
  public static void injectInsertSmsExpenseUseCase(SmsReceiver instance,
      InsertSmsExpenseUseCase insertSmsExpenseUseCase) {
    instance.insertSmsExpenseUseCase = insertSmsExpenseUseCase;
  }
}

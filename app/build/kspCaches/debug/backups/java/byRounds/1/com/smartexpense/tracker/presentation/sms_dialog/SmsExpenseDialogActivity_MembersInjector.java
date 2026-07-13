package com.smartexpense.tracker.presentation.sms_dialog;

import com.google.android.gms.location.FusedLocationProviderClient;
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
public final class SmsExpenseDialogActivity_MembersInjector implements MembersInjector<SmsExpenseDialogActivity> {
  private final Provider<InsertSmsExpenseUseCase> insertSmsExpenseUseCaseProvider;

  private final Provider<FusedLocationProviderClient> fusedLocationClientProvider;

  public SmsExpenseDialogActivity_MembersInjector(
      Provider<InsertSmsExpenseUseCase> insertSmsExpenseUseCaseProvider,
      Provider<FusedLocationProviderClient> fusedLocationClientProvider) {
    this.insertSmsExpenseUseCaseProvider = insertSmsExpenseUseCaseProvider;
    this.fusedLocationClientProvider = fusedLocationClientProvider;
  }

  public static MembersInjector<SmsExpenseDialogActivity> create(
      Provider<InsertSmsExpenseUseCase> insertSmsExpenseUseCaseProvider,
      Provider<FusedLocationProviderClient> fusedLocationClientProvider) {
    return new SmsExpenseDialogActivity_MembersInjector(insertSmsExpenseUseCaseProvider, fusedLocationClientProvider);
  }

  @Override
  public void injectMembers(SmsExpenseDialogActivity instance) {
    injectInsertSmsExpenseUseCase(instance, insertSmsExpenseUseCaseProvider.get());
    injectFusedLocationClient(instance, fusedLocationClientProvider.get());
  }

  @InjectedFieldSignature("com.smartexpense.tracker.presentation.sms_dialog.SmsExpenseDialogActivity.insertSmsExpenseUseCase")
  public static void injectInsertSmsExpenseUseCase(SmsExpenseDialogActivity instance,
      InsertSmsExpenseUseCase insertSmsExpenseUseCase) {
    instance.insertSmsExpenseUseCase = insertSmsExpenseUseCase;
  }

  @InjectedFieldSignature("com.smartexpense.tracker.presentation.sms_dialog.SmsExpenseDialogActivity.fusedLocationClient")
  public static void injectFusedLocationClient(SmsExpenseDialogActivity instance,
      FusedLocationProviderClient fusedLocationClient) {
    instance.fusedLocationClient = fusedLocationClient;
  }
}

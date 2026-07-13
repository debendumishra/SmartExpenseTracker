package com.smartexpense.tracker.presentation.settings;

import com.smartexpense.tracker.domain.repository.PaymentModeRepository;
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
public final class PaymentModeViewModel_Factory implements Factory<PaymentModeViewModel> {
  private final Provider<PaymentModeRepository> repositoryProvider;

  public PaymentModeViewModel_Factory(Provider<PaymentModeRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public PaymentModeViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static PaymentModeViewModel_Factory create(
      Provider<PaymentModeRepository> repositoryProvider) {
    return new PaymentModeViewModel_Factory(repositoryProvider);
  }

  public static PaymentModeViewModel newInstance(PaymentModeRepository repository) {
    return new PaymentModeViewModel(repository);
  }
}

package com.smartexpense.tracker.domain.repository;

import com.smartexpense.tracker.data.local.dao.PaymentModeDao;
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
public final class PaymentModeRepository_Factory implements Factory<PaymentModeRepository> {
  private final Provider<PaymentModeDao> paymentModeDaoProvider;

  public PaymentModeRepository_Factory(Provider<PaymentModeDao> paymentModeDaoProvider) {
    this.paymentModeDaoProvider = paymentModeDaoProvider;
  }

  @Override
  public PaymentModeRepository get() {
    return newInstance(paymentModeDaoProvider.get());
  }

  public static PaymentModeRepository_Factory create(
      Provider<PaymentModeDao> paymentModeDaoProvider) {
    return new PaymentModeRepository_Factory(paymentModeDaoProvider);
  }

  public static PaymentModeRepository newInstance(PaymentModeDao paymentModeDao) {
    return new PaymentModeRepository(paymentModeDao);
  }
}

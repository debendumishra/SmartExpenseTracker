package com.smartexpense.tracker.di;

import com.smartexpense.tracker.data.local.AppDatabase;
import com.smartexpense.tracker.data.local.dao.PaymentModeDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvidePaymentModeDaoFactory implements Factory<PaymentModeDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvidePaymentModeDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PaymentModeDao get() {
    return providePaymentModeDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvidePaymentModeDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvidePaymentModeDaoFactory(databaseProvider);
  }

  public static PaymentModeDao providePaymentModeDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePaymentModeDao(database));
  }
}

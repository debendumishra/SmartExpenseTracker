package com.smartexpense.tracker.di;

import com.smartexpense.tracker.data.local.AppDatabase;
import com.smartexpense.tracker.data.local.dao.BankDao;
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
public final class DatabaseModule_ProvideBankDaoFactory implements Factory<BankDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideBankDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public BankDao get() {
    return provideBankDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideBankDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideBankDaoFactory(databaseProvider);
  }

  public static BankDao provideBankDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideBankDao(database));
  }
}

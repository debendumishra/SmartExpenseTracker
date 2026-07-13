package com.smartexpense.tracker.di;

import com.smartexpense.tracker.data.local.AppDatabase;
import com.smartexpense.tracker.data.local.dao.ExpenseModeDao;
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
public final class DatabaseModule_ProvideExpenseModeDaoFactory implements Factory<ExpenseModeDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideExpenseModeDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ExpenseModeDao get() {
    return provideExpenseModeDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideExpenseModeDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideExpenseModeDaoFactory(databaseProvider);
  }

  public static ExpenseModeDao provideExpenseModeDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideExpenseModeDao(database));
  }
}

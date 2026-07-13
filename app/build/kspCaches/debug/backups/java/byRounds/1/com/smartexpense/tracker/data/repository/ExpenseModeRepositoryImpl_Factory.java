package com.smartexpense.tracker.data.repository;

import com.smartexpense.tracker.data.local.dao.ExpenseModeDao;
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
public final class ExpenseModeRepositoryImpl_Factory implements Factory<ExpenseModeRepositoryImpl> {
  private final Provider<ExpenseModeDao> daoProvider;

  public ExpenseModeRepositoryImpl_Factory(Provider<ExpenseModeDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public ExpenseModeRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static ExpenseModeRepositoryImpl_Factory create(Provider<ExpenseModeDao> daoProvider) {
    return new ExpenseModeRepositoryImpl_Factory(daoProvider);
  }

  public static ExpenseModeRepositoryImpl newInstance(ExpenseModeDao dao) {
    return new ExpenseModeRepositoryImpl(dao);
  }
}

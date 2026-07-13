package com.smartexpense.tracker.presentation.settings;

import com.smartexpense.tracker.domain.repository.ExpenseModeRepository;
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
public final class ExpenseModeViewModel_Factory implements Factory<ExpenseModeViewModel> {
  private final Provider<ExpenseModeRepository> modeRepositoryProvider;

  public ExpenseModeViewModel_Factory(Provider<ExpenseModeRepository> modeRepositoryProvider) {
    this.modeRepositoryProvider = modeRepositoryProvider;
  }

  @Override
  public ExpenseModeViewModel get() {
    return newInstance(modeRepositoryProvider.get());
  }

  public static ExpenseModeViewModel_Factory create(
      Provider<ExpenseModeRepository> modeRepositoryProvider) {
    return new ExpenseModeViewModel_Factory(modeRepositoryProvider);
  }

  public static ExpenseModeViewModel newInstance(ExpenseModeRepository modeRepository) {
    return new ExpenseModeViewModel(modeRepository);
  }
}

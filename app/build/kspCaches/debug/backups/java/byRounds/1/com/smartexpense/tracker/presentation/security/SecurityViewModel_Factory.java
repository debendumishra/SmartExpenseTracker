package com.smartexpense.tracker.presentation.security;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class SecurityViewModel_Factory implements Factory<SecurityViewModel> {
  private final Provider<Context> contextProvider;

  public SecurityViewModel_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SecurityViewModel get() {
    return newInstance(contextProvider.get());
  }

  public static SecurityViewModel_Factory create(Provider<Context> contextProvider) {
    return new SecurityViewModel_Factory(contextProvider);
  }

  public static SecurityViewModel newInstance(Context context) {
    return new SecurityViewModel(context);
  }
}

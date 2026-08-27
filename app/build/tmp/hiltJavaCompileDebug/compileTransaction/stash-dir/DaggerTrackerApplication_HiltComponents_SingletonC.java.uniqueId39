package com.smartexpense.tracker;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.smartexpense.tracker.data.local.AppDatabase;
import com.smartexpense.tracker.data.local.dao.CategoryDao;
import com.smartexpense.tracker.data.local.dao.ExpenseDao;
import com.smartexpense.tracker.data.local.dao.ExpenseModeDao;
import com.smartexpense.tracker.data.local.dao.PaymentModeDao;
import com.smartexpense.tracker.data.repository.CategoryRepositoryImpl;
import com.smartexpense.tracker.data.repository.ExpenseModeRepositoryImpl;
import com.smartexpense.tracker.data.repository.ExpenseRepositoryImpl;
import com.smartexpense.tracker.di.AppModule_ProvideFusedLocationProviderClientFactory;
import com.smartexpense.tracker.di.DatabaseModule_ProvideAppDatabaseFactory;
import com.smartexpense.tracker.di.DatabaseModule_ProvideCategoryDaoFactory;
import com.smartexpense.tracker.di.DatabaseModule_ProvideExpenseDaoFactory;
import com.smartexpense.tracker.di.DatabaseModule_ProvideExpenseModeDaoFactory;
import com.smartexpense.tracker.di.DatabaseModule_ProvidePaymentModeDaoFactory;
import com.smartexpense.tracker.domain.repository.CategoryRepository;
import com.smartexpense.tracker.domain.repository.ExpenseModeRepository;
import com.smartexpense.tracker.domain.repository.ExpenseRepository;
import com.smartexpense.tracker.domain.repository.PaymentModeRepository;
import com.smartexpense.tracker.domain.usecase.InsertSmsExpenseUseCase;
import com.smartexpense.tracker.presentation.add_expense.AddExpenseViewModel;
import com.smartexpense.tracker.presentation.add_expense.AddExpenseViewModel_HiltModules_KeyModule_ProvideFactory;
import com.smartexpense.tracker.presentation.backup.BackupViewModel;
import com.smartexpense.tracker.presentation.backup.BackupViewModel_HiltModules_KeyModule_ProvideFactory;
import com.smartexpense.tracker.presentation.dashboard.DashboardViewModel;
import com.smartexpense.tracker.presentation.dashboard.DashboardViewModel_HiltModules_KeyModule_ProvideFactory;
import com.smartexpense.tracker.presentation.reports.DetailedModeReportViewModel;
import com.smartexpense.tracker.presentation.reports.DetailedModeReportViewModel_HiltModules_KeyModule_ProvideFactory;
import com.smartexpense.tracker.presentation.reports.ReportsViewModel;
import com.smartexpense.tracker.presentation.reports.ReportsViewModel_HiltModules_KeyModule_ProvideFactory;
import com.smartexpense.tracker.presentation.security.SecurityViewModel;
import com.smartexpense.tracker.presentation.security.SecurityViewModel_HiltModules_KeyModule_ProvideFactory;
import com.smartexpense.tracker.presentation.settings.ExpenseModeViewModel;
import com.smartexpense.tracker.presentation.settings.ExpenseModeViewModel_HiltModules_KeyModule_ProvideFactory;
import com.smartexpense.tracker.presentation.settings.ManageCategoriesViewModel;
import com.smartexpense.tracker.presentation.settings.ManageCategoriesViewModel_HiltModules_KeyModule_ProvideFactory;
import com.smartexpense.tracker.presentation.settings.PaymentModeViewModel;
import com.smartexpense.tracker.presentation.settings.PaymentModeViewModel_HiltModules_KeyModule_ProvideFactory;
import com.smartexpense.tracker.presentation.transactions.TransactionsViewModel;
import com.smartexpense.tracker.presentation.transactions.TransactionsViewModel_HiltModules_KeyModule_ProvideFactory;
import com.smartexpense.tracker.services.OverlayService;
import com.smartexpense.tracker.services.SmsReceiver;
import com.smartexpense.tracker.services.SmsReceiver_MembersInjector;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SetBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerTrackerApplication_HiltComponents_SingletonC {
  private DaggerTrackerApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public TrackerApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements TrackerApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public TrackerApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements TrackerApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public TrackerApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements TrackerApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public TrackerApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements TrackerApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public TrackerApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements TrackerApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public TrackerApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements TrackerApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public TrackerApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements TrackerApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public TrackerApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends TrackerApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends TrackerApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends TrackerApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends TrackerApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
      injectMainActivity2(mainActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return SetBuilder.<String>newSetBuilder(10).add(AddExpenseViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(BackupViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(DashboardViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(DetailedModeReportViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ExpenseModeViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ManageCategoriesViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(PaymentModeViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ReportsViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SecurityViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(TransactionsViewModel_HiltModules_KeyModule_ProvideFactory.provide()).build();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectInsertSmsExpenseUseCase(instance, singletonCImpl.insertSmsExpenseUseCase());
      return instance;
    }
  }

  private static final class ViewModelCImpl extends TrackerApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AddExpenseViewModel> addExpenseViewModelProvider;

    private Provider<BackupViewModel> backupViewModelProvider;

    private Provider<DashboardViewModel> dashboardViewModelProvider;

    private Provider<DetailedModeReportViewModel> detailedModeReportViewModelProvider;

    private Provider<ExpenseModeViewModel> expenseModeViewModelProvider;

    private Provider<ManageCategoriesViewModel> manageCategoriesViewModelProvider;

    private Provider<PaymentModeViewModel> paymentModeViewModelProvider;

    private Provider<ReportsViewModel> reportsViewModelProvider;

    private Provider<SecurityViewModel> securityViewModelProvider;

    private Provider<TransactionsViewModel> transactionsViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private PaymentModeRepository paymentModeRepository() {
      return new PaymentModeRepository(singletonCImpl.providePaymentModeDaoProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.addExpenseViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.backupViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.dashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.detailedModeReportViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.expenseModeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.manageCategoriesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.paymentModeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.reportsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.securityViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.transactionsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
    }

    @Override
    public Map<String, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(10).put("com.smartexpense.tracker.presentation.add_expense.AddExpenseViewModel", ((Provider) addExpenseViewModelProvider)).put("com.smartexpense.tracker.presentation.backup.BackupViewModel", ((Provider) backupViewModelProvider)).put("com.smartexpense.tracker.presentation.dashboard.DashboardViewModel", ((Provider) dashboardViewModelProvider)).put("com.smartexpense.tracker.presentation.reports.DetailedModeReportViewModel", ((Provider) detailedModeReportViewModelProvider)).put("com.smartexpense.tracker.presentation.settings.ExpenseModeViewModel", ((Provider) expenseModeViewModelProvider)).put("com.smartexpense.tracker.presentation.settings.ManageCategoriesViewModel", ((Provider) manageCategoriesViewModelProvider)).put("com.smartexpense.tracker.presentation.settings.PaymentModeViewModel", ((Provider) paymentModeViewModelProvider)).put("com.smartexpense.tracker.presentation.reports.ReportsViewModel", ((Provider) reportsViewModelProvider)).put("com.smartexpense.tracker.presentation.security.SecurityViewModel", ((Provider) securityViewModelProvider)).put("com.smartexpense.tracker.presentation.transactions.TransactionsViewModel", ((Provider) transactionsViewModelProvider)).build();
    }

    @Override
    public Map<String, Object> getHiltViewModelAssistedMap() {
      return Collections.<String, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.smartexpense.tracker.presentation.add_expense.AddExpenseViewModel 
          return (T) new AddExpenseViewModel(singletonCImpl.bindExpenseRepositoryProvider.get(), singletonCImpl.bindExpenseModeRepositoryProvider.get(), singletonCImpl.bindCategoryRepositoryProvider.get(), viewModelCImpl.paymentModeRepository(), singletonCImpl.provideFusedLocationProviderClientProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 1: // com.smartexpense.tracker.presentation.backup.BackupViewModel 
          return (T) new BackupViewModel(singletonCImpl.bindExpenseRepositoryProvider.get());

          case 2: // com.smartexpense.tracker.presentation.dashboard.DashboardViewModel 
          return (T) new DashboardViewModel(singletonCImpl.bindExpenseRepositoryProvider.get(), singletonCImpl.bindExpenseModeRepositoryProvider.get());

          case 3: // com.smartexpense.tracker.presentation.reports.DetailedModeReportViewModel 
          return (T) new DetailedModeReportViewModel(singletonCImpl.bindExpenseRepositoryProvider.get());

          case 4: // com.smartexpense.tracker.presentation.settings.ExpenseModeViewModel 
          return (T) new ExpenseModeViewModel(singletonCImpl.bindExpenseModeRepositoryProvider.get());

          case 5: // com.smartexpense.tracker.presentation.settings.ManageCategoriesViewModel 
          return (T) new ManageCategoriesViewModel(singletonCImpl.bindCategoryRepositoryProvider.get());

          case 6: // com.smartexpense.tracker.presentation.settings.PaymentModeViewModel 
          return (T) new PaymentModeViewModel(viewModelCImpl.paymentModeRepository());

          case 7: // com.smartexpense.tracker.presentation.reports.ReportsViewModel 
          return (T) new ReportsViewModel(singletonCImpl.bindExpenseRepositoryProvider.get());

          case 8: // com.smartexpense.tracker.presentation.security.SecurityViewModel 
          return (T) new SecurityViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 9: // com.smartexpense.tracker.presentation.transactions.TransactionsViewModel 
          return (T) new TransactionsViewModel(singletonCImpl.bindExpenseRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends TrackerApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends TrackerApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }

    @Override
    public void injectOverlayService(OverlayService overlayService) {
    }
  }

  private static final class SingletonCImpl extends TrackerApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<AppDatabase> provideAppDatabaseProvider;

    private Provider<ExpenseDao> provideExpenseDaoProvider;

    private Provider<ExpenseRepositoryImpl> expenseRepositoryImplProvider;

    private Provider<ExpenseRepository> bindExpenseRepositoryProvider;

    private Provider<ExpenseModeDao> provideExpenseModeDaoProvider;

    private Provider<ExpenseModeRepositoryImpl> expenseModeRepositoryImplProvider;

    private Provider<ExpenseModeRepository> bindExpenseModeRepositoryProvider;

    private Provider<CategoryDao> provideCategoryDaoProvider;

    private Provider<CategoryRepositoryImpl> categoryRepositoryImplProvider;

    private Provider<CategoryRepository> bindCategoryRepositoryProvider;

    private Provider<PaymentModeDao> providePaymentModeDaoProvider;

    private Provider<FusedLocationProviderClient> provideFusedLocationProviderClientProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private InsertSmsExpenseUseCase insertSmsExpenseUseCase() {
      return new InsertSmsExpenseUseCase(bindExpenseRepositoryProvider.get(), bindExpenseModeRepositoryProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideAppDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 2));
      this.provideExpenseDaoProvider = DoubleCheck.provider(new SwitchingProvider<ExpenseDao>(singletonCImpl, 1));
      this.expenseRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 0);
      this.bindExpenseRepositoryProvider = DoubleCheck.provider((Provider) expenseRepositoryImplProvider);
      this.provideExpenseModeDaoProvider = DoubleCheck.provider(new SwitchingProvider<ExpenseModeDao>(singletonCImpl, 4));
      this.expenseModeRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 3);
      this.bindExpenseModeRepositoryProvider = DoubleCheck.provider((Provider) expenseModeRepositoryImplProvider);
      this.provideCategoryDaoProvider = DoubleCheck.provider(new SwitchingProvider<CategoryDao>(singletonCImpl, 6));
      this.categoryRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 5);
      this.bindCategoryRepositoryProvider = DoubleCheck.provider((Provider) categoryRepositoryImplProvider);
      this.providePaymentModeDaoProvider = DoubleCheck.provider(new SwitchingProvider<PaymentModeDao>(singletonCImpl, 7));
      this.provideFusedLocationProviderClientProvider = DoubleCheck.provider(new SwitchingProvider<FusedLocationProviderClient>(singletonCImpl, 8));
    }

    @Override
    public void injectTrackerApplication(TrackerApplication trackerApplication) {
    }

    @Override
    public void injectSmsReceiver(SmsReceiver smsReceiver) {
      injectSmsReceiver2(smsReceiver);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private SmsReceiver injectSmsReceiver2(SmsReceiver instance) {
      SmsReceiver_MembersInjector.injectInsertSmsExpenseUseCase(instance, insertSmsExpenseUseCase());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.smartexpense.tracker.data.repository.ExpenseRepositoryImpl 
          return (T) new ExpenseRepositoryImpl(singletonCImpl.provideExpenseDaoProvider.get());

          case 1: // com.smartexpense.tracker.data.local.dao.ExpenseDao 
          return (T) DatabaseModule_ProvideExpenseDaoFactory.provideExpenseDao(singletonCImpl.provideAppDatabaseProvider.get());

          case 2: // com.smartexpense.tracker.data.local.AppDatabase 
          return (T) DatabaseModule_ProvideAppDatabaseFactory.provideAppDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.smartexpense.tracker.data.repository.ExpenseModeRepositoryImpl 
          return (T) new ExpenseModeRepositoryImpl(singletonCImpl.provideExpenseModeDaoProvider.get(), singletonCImpl.provideExpenseDaoProvider.get());

          case 4: // com.smartexpense.tracker.data.local.dao.ExpenseModeDao 
          return (T) DatabaseModule_ProvideExpenseModeDaoFactory.provideExpenseModeDao(singletonCImpl.provideAppDatabaseProvider.get());

          case 5: // com.smartexpense.tracker.data.repository.CategoryRepositoryImpl 
          return (T) new CategoryRepositoryImpl(singletonCImpl.provideCategoryDaoProvider.get());

          case 6: // com.smartexpense.tracker.data.local.dao.CategoryDao 
          return (T) DatabaseModule_ProvideCategoryDaoFactory.provideCategoryDao(singletonCImpl.provideAppDatabaseProvider.get());

          case 7: // com.smartexpense.tracker.data.local.dao.PaymentModeDao 
          return (T) DatabaseModule_ProvidePaymentModeDaoFactory.providePaymentModeDao(singletonCImpl.provideAppDatabaseProvider.get());

          case 8: // com.google.android.gms.location.FusedLocationProviderClient 
          return (T) AppModule_ProvideFusedLocationProviderClientFactory.provideFusedLocationProviderClient(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}

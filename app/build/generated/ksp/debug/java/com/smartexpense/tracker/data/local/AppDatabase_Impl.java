package com.smartexpense.tracker.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.smartexpense.tracker.data.local.dao.BankDao;
import com.smartexpense.tracker.data.local.dao.BankDao_Impl;
import com.smartexpense.tracker.data.local.dao.CategoryDao;
import com.smartexpense.tracker.data.local.dao.CategoryDao_Impl;
import com.smartexpense.tracker.data.local.dao.ExpenseDao;
import com.smartexpense.tracker.data.local.dao.ExpenseDao_Impl;
import com.smartexpense.tracker.data.local.dao.ExpenseModeDao;
import com.smartexpense.tracker.data.local.dao.ExpenseModeDao_Impl;
import com.smartexpense.tracker.data.local.dao.PaymentModeDao;
import com.smartexpense.tracker.data.local.dao.PaymentModeDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ExpenseDao _expenseDao;

  private volatile CategoryDao _categoryDao;

  private volatile ExpenseModeDao _expenseModeDao;

  private volatile BankDao _bankDao;

  private volatile PaymentModeDao _paymentModeDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `expenses` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `amount` REAL NOT NULL, `purpose` TEXT, `categoryId` INTEGER, `bankId` INTEGER, `paymentMode` TEXT NOT NULL, `merchant` TEXT, `expenseModeId` INTEGER, `timestamp` INTEGER NOT NULL, `entryTimestamp` INTEGER NOT NULL, `latitude` REAL, `longitude` REAL, `address` TEXT, `city` TEXT, `state` TEXT, `notes` TEXT, `source` TEXT NOT NULL, `smsTimestamp` INTEGER, `paidBy` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `categories` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `iconName` TEXT, `isDefault` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `expense_modes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `isActive` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `endedAt` INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `banks` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `isDefault` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `payment_modes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'fb7f70e431b7f0d6a0346cb369335d15')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `expenses`");
        db.execSQL("DROP TABLE IF EXISTS `categories`");
        db.execSQL("DROP TABLE IF EXISTS `expense_modes`");
        db.execSQL("DROP TABLE IF EXISTS `banks`");
        db.execSQL("DROP TABLE IF EXISTS `payment_modes`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsExpenses = new HashMap<String, TableInfo.Column>(19);
        _columnsExpenses.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("purpose", new TableInfo.Column("purpose", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("categoryId", new TableInfo.Column("categoryId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("bankId", new TableInfo.Column("bankId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("paymentMode", new TableInfo.Column("paymentMode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("merchant", new TableInfo.Column("merchant", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("expenseModeId", new TableInfo.Column("expenseModeId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("entryTimestamp", new TableInfo.Column("entryTimestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("latitude", new TableInfo.Column("latitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("longitude", new TableInfo.Column("longitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("address", new TableInfo.Column("address", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("city", new TableInfo.Column("city", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("state", new TableInfo.Column("state", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("source", new TableInfo.Column("source", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("smsTimestamp", new TableInfo.Column("smsTimestamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("paidBy", new TableInfo.Column("paidBy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExpenses = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExpenses = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoExpenses = new TableInfo("expenses", _columnsExpenses, _foreignKeysExpenses, _indicesExpenses);
        final TableInfo _existingExpenses = TableInfo.read(db, "expenses");
        if (!_infoExpenses.equals(_existingExpenses)) {
          return new RoomOpenHelper.ValidationResult(false, "expenses(com.smartexpense.tracker.data.local.entity.ExpenseEntity).\n"
                  + " Expected:\n" + _infoExpenses + "\n"
                  + " Found:\n" + _existingExpenses);
        }
        final HashMap<String, TableInfo.Column> _columnsCategories = new HashMap<String, TableInfo.Column>(4);
        _columnsCategories.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("iconName", new TableInfo.Column("iconName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("isDefault", new TableInfo.Column("isDefault", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCategories = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCategories = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCategories = new TableInfo("categories", _columnsCategories, _foreignKeysCategories, _indicesCategories);
        final TableInfo _existingCategories = TableInfo.read(db, "categories");
        if (!_infoCategories.equals(_existingCategories)) {
          return new RoomOpenHelper.ValidationResult(false, "categories(com.smartexpense.tracker.data.local.entity.CategoryEntity).\n"
                  + " Expected:\n" + _infoCategories + "\n"
                  + " Found:\n" + _existingCategories);
        }
        final HashMap<String, TableInfo.Column> _columnsExpenseModes = new HashMap<String, TableInfo.Column>(5);
        _columnsExpenseModes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenseModes.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenseModes.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenseModes.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenseModes.put("endedAt", new TableInfo.Column("endedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExpenseModes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExpenseModes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoExpenseModes = new TableInfo("expense_modes", _columnsExpenseModes, _foreignKeysExpenseModes, _indicesExpenseModes);
        final TableInfo _existingExpenseModes = TableInfo.read(db, "expense_modes");
        if (!_infoExpenseModes.equals(_existingExpenseModes)) {
          return new RoomOpenHelper.ValidationResult(false, "expense_modes(com.smartexpense.tracker.data.local.entity.ExpenseModeEntity).\n"
                  + " Expected:\n" + _infoExpenseModes + "\n"
                  + " Found:\n" + _existingExpenseModes);
        }
        final HashMap<String, TableInfo.Column> _columnsBanks = new HashMap<String, TableInfo.Column>(3);
        _columnsBanks.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBanks.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBanks.put("isDefault", new TableInfo.Column("isDefault", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBanks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBanks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBanks = new TableInfo("banks", _columnsBanks, _foreignKeysBanks, _indicesBanks);
        final TableInfo _existingBanks = TableInfo.read(db, "banks");
        if (!_infoBanks.equals(_existingBanks)) {
          return new RoomOpenHelper.ValidationResult(false, "banks(com.smartexpense.tracker.data.local.entity.BankEntity).\n"
                  + " Expected:\n" + _infoBanks + "\n"
                  + " Found:\n" + _existingBanks);
        }
        final HashMap<String, TableInfo.Column> _columnsPaymentModes = new HashMap<String, TableInfo.Column>(2);
        _columnsPaymentModes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentModes.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPaymentModes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPaymentModes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPaymentModes = new TableInfo("payment_modes", _columnsPaymentModes, _foreignKeysPaymentModes, _indicesPaymentModes);
        final TableInfo _existingPaymentModes = TableInfo.read(db, "payment_modes");
        if (!_infoPaymentModes.equals(_existingPaymentModes)) {
          return new RoomOpenHelper.ValidationResult(false, "payment_modes(com.smartexpense.tracker.data.local.entity.PaymentModeEntity).\n"
                  + " Expected:\n" + _infoPaymentModes + "\n"
                  + " Found:\n" + _existingPaymentModes);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "fb7f70e431b7f0d6a0346cb369335d15", "57340608b700f306453e044f2f722d94");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "expenses","categories","expense_modes","banks","payment_modes");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `expenses`");
      _db.execSQL("DELETE FROM `categories`");
      _db.execSQL("DELETE FROM `expense_modes`");
      _db.execSQL("DELETE FROM `banks`");
      _db.execSQL("DELETE FROM `payment_modes`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ExpenseDao.class, ExpenseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CategoryDao.class, CategoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExpenseModeDao.class, ExpenseModeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BankDao.class, BankDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PaymentModeDao.class, PaymentModeDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ExpenseDao expenseDao() {
    if (_expenseDao != null) {
      return _expenseDao;
    } else {
      synchronized(this) {
        if(_expenseDao == null) {
          _expenseDao = new ExpenseDao_Impl(this);
        }
        return _expenseDao;
      }
    }
  }

  @Override
  public CategoryDao categoryDao() {
    if (_categoryDao != null) {
      return _categoryDao;
    } else {
      synchronized(this) {
        if(_categoryDao == null) {
          _categoryDao = new CategoryDao_Impl(this);
        }
        return _categoryDao;
      }
    }
  }

  @Override
  public ExpenseModeDao expenseModeDao() {
    if (_expenseModeDao != null) {
      return _expenseModeDao;
    } else {
      synchronized(this) {
        if(_expenseModeDao == null) {
          _expenseModeDao = new ExpenseModeDao_Impl(this);
        }
        return _expenseModeDao;
      }
    }
  }

  @Override
  public BankDao bankDao() {
    if (_bankDao != null) {
      return _bankDao;
    } else {
      synchronized(this) {
        if(_bankDao == null) {
          _bankDao = new BankDao_Impl(this);
        }
        return _bankDao;
      }
    }
  }

  @Override
  public PaymentModeDao paymentModeDao() {
    if (_paymentModeDao != null) {
      return _paymentModeDao;
    } else {
      synchronized(this) {
        if(_paymentModeDao == null) {
          _paymentModeDao = new PaymentModeDao_Impl(this);
        }
        return _paymentModeDao;
      }
    }
  }
}

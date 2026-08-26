package com.smartexpense.tracker.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.smartexpense.tracker.data.local.entity.DetailedModeExpense;
import com.smartexpense.tracker.data.local.entity.ExpenseEntity;
import com.smartexpense.tracker.data.local.entity.ExportExpenseDTO;
import com.smartexpense.tracker.data.local.entity.ReportGroupSum;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ExpenseDao_Impl implements ExpenseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExpenseEntity> __insertionAdapterOfExpenseEntity;

  private final EntityDeletionOrUpdateAdapter<ExpenseEntity> __deletionAdapterOfExpenseEntity;

  private final EntityDeletionOrUpdateAdapter<ExpenseEntity> __updateAdapterOfExpenseEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteExpensesByModeId;

  public ExpenseDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExpenseEntity = new EntityInsertionAdapter<ExpenseEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `expenses` (`id`,`amount`,`purpose`,`categoryId`,`bankId`,`paymentMode`,`merchant`,`expenseModeId`,`timestamp`,`entryTimestamp`,`latitude`,`longitude`,`address`,`city`,`state`,`notes`,`source`,`smsTimestamp`,`paidBy`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExpenseEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindDouble(2, entity.getAmount());
        if (entity.getPurpose() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPurpose());
        }
        if (entity.getCategoryId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getCategoryId());
        }
        if (entity.getBankId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getBankId());
        }
        statement.bindString(6, entity.getPaymentMode());
        if (entity.getMerchant() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getMerchant());
        }
        if (entity.getExpenseModeId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getExpenseModeId());
        }
        statement.bindLong(9, entity.getTimestamp());
        statement.bindLong(10, entity.getEntryTimestamp());
        if (entity.getLatitude() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(12);
        } else {
          statement.bindDouble(12, entity.getLongitude());
        }
        if (entity.getAddress() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getAddress());
        }
        if (entity.getCity() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getCity());
        }
        if (entity.getState() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getState());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getNotes());
        }
        statement.bindString(17, entity.getSource());
        if (entity.getSmsTimestamp() == null) {
          statement.bindNull(18);
        } else {
          statement.bindLong(18, entity.getSmsTimestamp());
        }
        if (entity.getPaidBy() == null) {
          statement.bindNull(19);
        } else {
          statement.bindString(19, entity.getPaidBy());
        }
      }
    };
    this.__deletionAdapterOfExpenseEntity = new EntityDeletionOrUpdateAdapter<ExpenseEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `expenses` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExpenseEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfExpenseEntity = new EntityDeletionOrUpdateAdapter<ExpenseEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `expenses` SET `id` = ?,`amount` = ?,`purpose` = ?,`categoryId` = ?,`bankId` = ?,`paymentMode` = ?,`merchant` = ?,`expenseModeId` = ?,`timestamp` = ?,`entryTimestamp` = ?,`latitude` = ?,`longitude` = ?,`address` = ?,`city` = ?,`state` = ?,`notes` = ?,`source` = ?,`smsTimestamp` = ?,`paidBy` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExpenseEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindDouble(2, entity.getAmount());
        if (entity.getPurpose() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPurpose());
        }
        if (entity.getCategoryId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getCategoryId());
        }
        if (entity.getBankId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getBankId());
        }
        statement.bindString(6, entity.getPaymentMode());
        if (entity.getMerchant() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getMerchant());
        }
        if (entity.getExpenseModeId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getExpenseModeId());
        }
        statement.bindLong(9, entity.getTimestamp());
        statement.bindLong(10, entity.getEntryTimestamp());
        if (entity.getLatitude() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(12);
        } else {
          statement.bindDouble(12, entity.getLongitude());
        }
        if (entity.getAddress() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getAddress());
        }
        if (entity.getCity() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getCity());
        }
        if (entity.getState() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getState());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getNotes());
        }
        statement.bindString(17, entity.getSource());
        if (entity.getSmsTimestamp() == null) {
          statement.bindNull(18);
        } else {
          statement.bindLong(18, entity.getSmsTimestamp());
        }
        if (entity.getPaidBy() == null) {
          statement.bindNull(19);
        } else {
          statement.bindString(19, entity.getPaidBy());
        }
        statement.bindLong(20, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteExpensesByModeId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM expenses WHERE expenseModeId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertExpense(final ExpenseEntity expense,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfExpenseEntity.insertAndReturnId(expense);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteExpense(final ExpenseEntity expense,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfExpenseEntity.handle(expense);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateExpense(final ExpenseEntity expense,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfExpenseEntity.handle(expense);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteExpensesByModeId(final long modeId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteExpensesByModeId.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, modeId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteExpensesByModeId.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ExpenseEntity>> getAllExpenses() {
    final String _sql = "SELECT * FROM expenses ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expenses"}, new Callable<List<ExpenseEntity>>() {
      @Override
      @NonNull
      public List<ExpenseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfPurpose = CursorUtil.getColumnIndexOrThrow(_cursor, "purpose");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfBankId = CursorUtil.getColumnIndexOrThrow(_cursor, "bankId");
          final int _cursorIndexOfPaymentMode = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMode");
          final int _cursorIndexOfMerchant = CursorUtil.getColumnIndexOrThrow(_cursor, "merchant");
          final int _cursorIndexOfExpenseModeId = CursorUtil.getColumnIndexOrThrow(_cursor, "expenseModeId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfEntryTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "entryTimestamp");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfSmsTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "smsTimestamp");
          final int _cursorIndexOfPaidBy = CursorUtil.getColumnIndexOrThrow(_cursor, "paidBy");
          final List<ExpenseEntity> _result = new ArrayList<ExpenseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExpenseEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpPurpose;
            if (_cursor.isNull(_cursorIndexOfPurpose)) {
              _tmpPurpose = null;
            } else {
              _tmpPurpose = _cursor.getString(_cursorIndexOfPurpose);
            }
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final Long _tmpBankId;
            if (_cursor.isNull(_cursorIndexOfBankId)) {
              _tmpBankId = null;
            } else {
              _tmpBankId = _cursor.getLong(_cursorIndexOfBankId);
            }
            final String _tmpPaymentMode;
            _tmpPaymentMode = _cursor.getString(_cursorIndexOfPaymentMode);
            final String _tmpMerchant;
            if (_cursor.isNull(_cursorIndexOfMerchant)) {
              _tmpMerchant = null;
            } else {
              _tmpMerchant = _cursor.getString(_cursorIndexOfMerchant);
            }
            final Long _tmpExpenseModeId;
            if (_cursor.isNull(_cursorIndexOfExpenseModeId)) {
              _tmpExpenseModeId = null;
            } else {
              _tmpExpenseModeId = _cursor.getLong(_cursorIndexOfExpenseModeId);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpEntryTimestamp;
            _tmpEntryTimestamp = _cursor.getLong(_cursorIndexOfEntryTimestamp);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpState;
            if (_cursor.isNull(_cursorIndexOfState)) {
              _tmpState = null;
            } else {
              _tmpState = _cursor.getString(_cursorIndexOfState);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpSource;
            _tmpSource = _cursor.getString(_cursorIndexOfSource);
            final Long _tmpSmsTimestamp;
            if (_cursor.isNull(_cursorIndexOfSmsTimestamp)) {
              _tmpSmsTimestamp = null;
            } else {
              _tmpSmsTimestamp = _cursor.getLong(_cursorIndexOfSmsTimestamp);
            }
            final String _tmpPaidBy;
            if (_cursor.isNull(_cursorIndexOfPaidBy)) {
              _tmpPaidBy = null;
            } else {
              _tmpPaidBy = _cursor.getString(_cursorIndexOfPaidBy);
            }
            _item = new ExpenseEntity(_tmpId,_tmpAmount,_tmpPurpose,_tmpCategoryId,_tmpBankId,_tmpPaymentMode,_tmpMerchant,_tmpExpenseModeId,_tmpTimestamp,_tmpEntryTimestamp,_tmpLatitude,_tmpLongitude,_tmpAddress,_tmpCity,_tmpState,_tmpNotes,_tmpSource,_tmpSmsTimestamp,_tmpPaidBy);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getExpenseById(final long id,
      final Continuation<? super ExpenseEntity> $completion) {
    final String _sql = "SELECT * FROM expenses WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ExpenseEntity>() {
      @Override
      @Nullable
      public ExpenseEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfPurpose = CursorUtil.getColumnIndexOrThrow(_cursor, "purpose");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfBankId = CursorUtil.getColumnIndexOrThrow(_cursor, "bankId");
          final int _cursorIndexOfPaymentMode = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMode");
          final int _cursorIndexOfMerchant = CursorUtil.getColumnIndexOrThrow(_cursor, "merchant");
          final int _cursorIndexOfExpenseModeId = CursorUtil.getColumnIndexOrThrow(_cursor, "expenseModeId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfEntryTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "entryTimestamp");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfSmsTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "smsTimestamp");
          final int _cursorIndexOfPaidBy = CursorUtil.getColumnIndexOrThrow(_cursor, "paidBy");
          final ExpenseEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpPurpose;
            if (_cursor.isNull(_cursorIndexOfPurpose)) {
              _tmpPurpose = null;
            } else {
              _tmpPurpose = _cursor.getString(_cursorIndexOfPurpose);
            }
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final Long _tmpBankId;
            if (_cursor.isNull(_cursorIndexOfBankId)) {
              _tmpBankId = null;
            } else {
              _tmpBankId = _cursor.getLong(_cursorIndexOfBankId);
            }
            final String _tmpPaymentMode;
            _tmpPaymentMode = _cursor.getString(_cursorIndexOfPaymentMode);
            final String _tmpMerchant;
            if (_cursor.isNull(_cursorIndexOfMerchant)) {
              _tmpMerchant = null;
            } else {
              _tmpMerchant = _cursor.getString(_cursorIndexOfMerchant);
            }
            final Long _tmpExpenseModeId;
            if (_cursor.isNull(_cursorIndexOfExpenseModeId)) {
              _tmpExpenseModeId = null;
            } else {
              _tmpExpenseModeId = _cursor.getLong(_cursorIndexOfExpenseModeId);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpEntryTimestamp;
            _tmpEntryTimestamp = _cursor.getLong(_cursorIndexOfEntryTimestamp);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpState;
            if (_cursor.isNull(_cursorIndexOfState)) {
              _tmpState = null;
            } else {
              _tmpState = _cursor.getString(_cursorIndexOfState);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpSource;
            _tmpSource = _cursor.getString(_cursorIndexOfSource);
            final Long _tmpSmsTimestamp;
            if (_cursor.isNull(_cursorIndexOfSmsTimestamp)) {
              _tmpSmsTimestamp = null;
            } else {
              _tmpSmsTimestamp = _cursor.getLong(_cursorIndexOfSmsTimestamp);
            }
            final String _tmpPaidBy;
            if (_cursor.isNull(_cursorIndexOfPaidBy)) {
              _tmpPaidBy = null;
            } else {
              _tmpPaidBy = _cursor.getString(_cursorIndexOfPaidBy);
            }
            _result = new ExpenseEntity(_tmpId,_tmpAmount,_tmpPurpose,_tmpCategoryId,_tmpBankId,_tmpPaymentMode,_tmpMerchant,_tmpExpenseModeId,_tmpTimestamp,_tmpEntryTimestamp,_tmpLatitude,_tmpLongitude,_tmpAddress,_tmpCity,_tmpState,_tmpNotes,_tmpSource,_tmpSmsTimestamp,_tmpPaidBy);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ExpenseEntity>> getExpensesByMode(final long modeId) {
    final String _sql = "SELECT * FROM expenses WHERE expenseModeId = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, modeId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expenses"}, new Callable<List<ExpenseEntity>>() {
      @Override
      @NonNull
      public List<ExpenseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfPurpose = CursorUtil.getColumnIndexOrThrow(_cursor, "purpose");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfBankId = CursorUtil.getColumnIndexOrThrow(_cursor, "bankId");
          final int _cursorIndexOfPaymentMode = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMode");
          final int _cursorIndexOfMerchant = CursorUtil.getColumnIndexOrThrow(_cursor, "merchant");
          final int _cursorIndexOfExpenseModeId = CursorUtil.getColumnIndexOrThrow(_cursor, "expenseModeId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfEntryTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "entryTimestamp");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfSmsTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "smsTimestamp");
          final int _cursorIndexOfPaidBy = CursorUtil.getColumnIndexOrThrow(_cursor, "paidBy");
          final List<ExpenseEntity> _result = new ArrayList<ExpenseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExpenseEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpPurpose;
            if (_cursor.isNull(_cursorIndexOfPurpose)) {
              _tmpPurpose = null;
            } else {
              _tmpPurpose = _cursor.getString(_cursorIndexOfPurpose);
            }
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final Long _tmpBankId;
            if (_cursor.isNull(_cursorIndexOfBankId)) {
              _tmpBankId = null;
            } else {
              _tmpBankId = _cursor.getLong(_cursorIndexOfBankId);
            }
            final String _tmpPaymentMode;
            _tmpPaymentMode = _cursor.getString(_cursorIndexOfPaymentMode);
            final String _tmpMerchant;
            if (_cursor.isNull(_cursorIndexOfMerchant)) {
              _tmpMerchant = null;
            } else {
              _tmpMerchant = _cursor.getString(_cursorIndexOfMerchant);
            }
            final Long _tmpExpenseModeId;
            if (_cursor.isNull(_cursorIndexOfExpenseModeId)) {
              _tmpExpenseModeId = null;
            } else {
              _tmpExpenseModeId = _cursor.getLong(_cursorIndexOfExpenseModeId);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpEntryTimestamp;
            _tmpEntryTimestamp = _cursor.getLong(_cursorIndexOfEntryTimestamp);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpState;
            if (_cursor.isNull(_cursorIndexOfState)) {
              _tmpState = null;
            } else {
              _tmpState = _cursor.getString(_cursorIndexOfState);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpSource;
            _tmpSource = _cursor.getString(_cursorIndexOfSource);
            final Long _tmpSmsTimestamp;
            if (_cursor.isNull(_cursorIndexOfSmsTimestamp)) {
              _tmpSmsTimestamp = null;
            } else {
              _tmpSmsTimestamp = _cursor.getLong(_cursorIndexOfSmsTimestamp);
            }
            final String _tmpPaidBy;
            if (_cursor.isNull(_cursorIndexOfPaidBy)) {
              _tmpPaidBy = null;
            } else {
              _tmpPaidBy = _cursor.getString(_cursorIndexOfPaidBy);
            }
            _item = new ExpenseEntity(_tmpId,_tmpAmount,_tmpPurpose,_tmpCategoryId,_tmpBankId,_tmpPaymentMode,_tmpMerchant,_tmpExpenseModeId,_tmpTimestamp,_tmpEntryTimestamp,_tmpLatitude,_tmpLongitude,_tmpAddress,_tmpCity,_tmpState,_tmpNotes,_tmpSource,_tmpSmsTimestamp,_tmpPaidBy);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ExpenseEntity>> getExpensesBetweenDates(final long startDate,
      final long endDate) {
    final String _sql = "SELECT * FROM expenses WHERE timestamp >= ? AND timestamp <= ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expenses"}, new Callable<List<ExpenseEntity>>() {
      @Override
      @NonNull
      public List<ExpenseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfPurpose = CursorUtil.getColumnIndexOrThrow(_cursor, "purpose");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfBankId = CursorUtil.getColumnIndexOrThrow(_cursor, "bankId");
          final int _cursorIndexOfPaymentMode = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMode");
          final int _cursorIndexOfMerchant = CursorUtil.getColumnIndexOrThrow(_cursor, "merchant");
          final int _cursorIndexOfExpenseModeId = CursorUtil.getColumnIndexOrThrow(_cursor, "expenseModeId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfEntryTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "entryTimestamp");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfSmsTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "smsTimestamp");
          final int _cursorIndexOfPaidBy = CursorUtil.getColumnIndexOrThrow(_cursor, "paidBy");
          final List<ExpenseEntity> _result = new ArrayList<ExpenseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExpenseEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpPurpose;
            if (_cursor.isNull(_cursorIndexOfPurpose)) {
              _tmpPurpose = null;
            } else {
              _tmpPurpose = _cursor.getString(_cursorIndexOfPurpose);
            }
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final Long _tmpBankId;
            if (_cursor.isNull(_cursorIndexOfBankId)) {
              _tmpBankId = null;
            } else {
              _tmpBankId = _cursor.getLong(_cursorIndexOfBankId);
            }
            final String _tmpPaymentMode;
            _tmpPaymentMode = _cursor.getString(_cursorIndexOfPaymentMode);
            final String _tmpMerchant;
            if (_cursor.isNull(_cursorIndexOfMerchant)) {
              _tmpMerchant = null;
            } else {
              _tmpMerchant = _cursor.getString(_cursorIndexOfMerchant);
            }
            final Long _tmpExpenseModeId;
            if (_cursor.isNull(_cursorIndexOfExpenseModeId)) {
              _tmpExpenseModeId = null;
            } else {
              _tmpExpenseModeId = _cursor.getLong(_cursorIndexOfExpenseModeId);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpEntryTimestamp;
            _tmpEntryTimestamp = _cursor.getLong(_cursorIndexOfEntryTimestamp);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpState;
            if (_cursor.isNull(_cursorIndexOfState)) {
              _tmpState = null;
            } else {
              _tmpState = _cursor.getString(_cursorIndexOfState);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpSource;
            _tmpSource = _cursor.getString(_cursorIndexOfSource);
            final Long _tmpSmsTimestamp;
            if (_cursor.isNull(_cursorIndexOfSmsTimestamp)) {
              _tmpSmsTimestamp = null;
            } else {
              _tmpSmsTimestamp = _cursor.getLong(_cursorIndexOfSmsTimestamp);
            }
            final String _tmpPaidBy;
            if (_cursor.isNull(_cursorIndexOfPaidBy)) {
              _tmpPaidBy = null;
            } else {
              _tmpPaidBy = _cursor.getString(_cursorIndexOfPaidBy);
            }
            _item = new ExpenseEntity(_tmpId,_tmpAmount,_tmpPurpose,_tmpCategoryId,_tmpBankId,_tmpPaymentMode,_tmpMerchant,_tmpExpenseModeId,_tmpTimestamp,_tmpEntryTimestamp,_tmpLatitude,_tmpLongitude,_tmpAddress,_tmpCity,_tmpState,_tmpNotes,_tmpSource,_tmpSmsTimestamp,_tmpPaidBy);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ExportExpenseDTO>> getExpensesForExport(final long startDate,
      final long endDate) {
    final String _sql = "\n"
            + "        SELECT x.id, x.amount, x.purpose, b.name AS bankName, x.paymentMode, x.merchant, \n"
            + "               e.name AS modeName, x.timestamp, x.latitude, x.longitude, \n"
            + "               x.address, x.city, x.state, x.notes, x.source, x.paidBy \n"
            + "        FROM expenses x \n"
            + "        LEFT JOIN banks b ON x.bankId = b.id \n"
            + "        LEFT JOIN expense_modes e ON x.expenseModeId = e.id \n"
            + "        WHERE x.timestamp >= ? AND x.timestamp <= ? \n"
            + "        ORDER BY x.timestamp DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expenses", "banks",
        "expense_modes"}, new Callable<List<ExportExpenseDTO>>() {
      @Override
      @NonNull
      public List<ExportExpenseDTO> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfAmount = 1;
          final int _cursorIndexOfPurpose = 2;
          final int _cursorIndexOfBankName = 3;
          final int _cursorIndexOfPaymentMode = 4;
          final int _cursorIndexOfMerchant = 5;
          final int _cursorIndexOfModeName = 6;
          final int _cursorIndexOfTimestamp = 7;
          final int _cursorIndexOfLatitude = 8;
          final int _cursorIndexOfLongitude = 9;
          final int _cursorIndexOfAddress = 10;
          final int _cursorIndexOfCity = 11;
          final int _cursorIndexOfState = 12;
          final int _cursorIndexOfNotes = 13;
          final int _cursorIndexOfSource = 14;
          final int _cursorIndexOfPaidBy = 15;
          final List<ExportExpenseDTO> _result = new ArrayList<ExportExpenseDTO>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExportExpenseDTO _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpPurpose;
            if (_cursor.isNull(_cursorIndexOfPurpose)) {
              _tmpPurpose = null;
            } else {
              _tmpPurpose = _cursor.getString(_cursorIndexOfPurpose);
            }
            final String _tmpBankName;
            if (_cursor.isNull(_cursorIndexOfBankName)) {
              _tmpBankName = null;
            } else {
              _tmpBankName = _cursor.getString(_cursorIndexOfBankName);
            }
            final String _tmpPaymentMode;
            _tmpPaymentMode = _cursor.getString(_cursorIndexOfPaymentMode);
            final String _tmpMerchant;
            if (_cursor.isNull(_cursorIndexOfMerchant)) {
              _tmpMerchant = null;
            } else {
              _tmpMerchant = _cursor.getString(_cursorIndexOfMerchant);
            }
            final String _tmpModeName;
            if (_cursor.isNull(_cursorIndexOfModeName)) {
              _tmpModeName = null;
            } else {
              _tmpModeName = _cursor.getString(_cursorIndexOfModeName);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpState;
            if (_cursor.isNull(_cursorIndexOfState)) {
              _tmpState = null;
            } else {
              _tmpState = _cursor.getString(_cursorIndexOfState);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpSource;
            _tmpSource = _cursor.getString(_cursorIndexOfSource);
            final String _tmpPaidBy;
            if (_cursor.isNull(_cursorIndexOfPaidBy)) {
              _tmpPaidBy = null;
            } else {
              _tmpPaidBy = _cursor.getString(_cursorIndexOfPaidBy);
            }
            _item = new ExportExpenseDTO(_tmpId,_tmpAmount,_tmpPurpose,_tmpBankName,_tmpPaymentMode,_tmpMerchant,_tmpModeName,_tmpTimestamp,_tmpLatitude,_tmpLongitude,_tmpAddress,_tmpCity,_tmpState,_tmpNotes,_tmpSource,_tmpPaidBy);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<DetailedModeExpense>> getDetailedModeExpenses() {
    final String _sql = "SELECT x.id, COALESCE(e.name, 'General') AS modeName, x.amount, x.purpose, x.merchant, x.city AS location, x.timestamp FROM expenses x LEFT JOIN expense_modes e ON x.expenseModeId = e.id ORDER BY COALESCE(e.name, 'General'), x.timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expenses",
        "expense_modes"}, new Callable<List<DetailedModeExpense>>() {
      @Override
      @NonNull
      public List<DetailedModeExpense> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfModeName = 1;
          final int _cursorIndexOfAmount = 2;
          final int _cursorIndexOfPurpose = 3;
          final int _cursorIndexOfMerchant = 4;
          final int _cursorIndexOfLocation = 5;
          final int _cursorIndexOfTimestamp = 6;
          final List<DetailedModeExpense> _result = new ArrayList<DetailedModeExpense>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DetailedModeExpense _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpModeName;
            if (_cursor.isNull(_cursorIndexOfModeName)) {
              _tmpModeName = null;
            } else {
              _tmpModeName = _cursor.getString(_cursorIndexOfModeName);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpPurpose;
            if (_cursor.isNull(_cursorIndexOfPurpose)) {
              _tmpPurpose = null;
            } else {
              _tmpPurpose = _cursor.getString(_cursorIndexOfPurpose);
            }
            final String _tmpMerchant;
            if (_cursor.isNull(_cursorIndexOfMerchant)) {
              _tmpMerchant = null;
            } else {
              _tmpMerchant = _cursor.getString(_cursorIndexOfMerchant);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new DetailedModeExpense(_tmpId,_tmpModeName,_tmpAmount,_tmpPurpose,_tmpMerchant,_tmpLocation,_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Double> getTotalSpentBetween(final long startDate, final long endDate) {
    final String _sql = "SELECT SUM(amount) FROM expenses WHERE timestamp >= ? AND timestamp <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expenses"}, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ReportGroupSum>> getReportByCategory(final long startDate, final long endDate) {
    final String _sql = "SELECT c.name AS groupName, SUM(x.amount) AS totalAmount FROM expenses x LEFT JOIN categories c ON x.categoryId = c.id WHERE x.timestamp >= ? AND x.timestamp <= ? GROUP BY c.name ORDER BY totalAmount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expenses",
        "categories"}, new Callable<List<ReportGroupSum>>() {
      @Override
      @NonNull
      public List<ReportGroupSum> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfGroupName = 0;
          final int _cursorIndexOfTotalAmount = 1;
          final List<ReportGroupSum> _result = new ArrayList<ReportGroupSum>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReportGroupSum _item;
            final String _tmpGroupName;
            if (_cursor.isNull(_cursorIndexOfGroupName)) {
              _tmpGroupName = null;
            } else {
              _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
            }
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            _item = new ReportGroupSum(_tmpGroupName,_tmpTotalAmount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ReportGroupSum>> getReportByPurpose(final long startDate, final long endDate) {
    final String _sql = "SELECT purpose AS groupName, SUM(amount) AS totalAmount FROM expenses WHERE timestamp >= ? AND timestamp <= ? GROUP BY purpose ORDER BY totalAmount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expenses"}, new Callable<List<ReportGroupSum>>() {
      @Override
      @NonNull
      public List<ReportGroupSum> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfGroupName = 0;
          final int _cursorIndexOfTotalAmount = 1;
          final List<ReportGroupSum> _result = new ArrayList<ReportGroupSum>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReportGroupSum _item;
            final String _tmpGroupName;
            if (_cursor.isNull(_cursorIndexOfGroupName)) {
              _tmpGroupName = null;
            } else {
              _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
            }
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            _item = new ReportGroupSum(_tmpGroupName,_tmpTotalAmount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ReportGroupSum>> getReportByMerchant(final long startDate, final long endDate) {
    final String _sql = "SELECT merchant AS groupName, SUM(amount) AS totalAmount FROM expenses WHERE timestamp >= ? AND timestamp <= ? GROUP BY merchant ORDER BY totalAmount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expenses"}, new Callable<List<ReportGroupSum>>() {
      @Override
      @NonNull
      public List<ReportGroupSum> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfGroupName = 0;
          final int _cursorIndexOfTotalAmount = 1;
          final List<ReportGroupSum> _result = new ArrayList<ReportGroupSum>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReportGroupSum _item;
            final String _tmpGroupName;
            if (_cursor.isNull(_cursorIndexOfGroupName)) {
              _tmpGroupName = null;
            } else {
              _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
            }
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            _item = new ReportGroupSum(_tmpGroupName,_tmpTotalAmount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ReportGroupSum>> getReportByPaymentMode(final long startDate,
      final long endDate) {
    final String _sql = "SELECT paymentMode AS groupName, SUM(amount) AS totalAmount FROM expenses WHERE timestamp >= ? AND timestamp <= ? GROUP BY paymentMode ORDER BY totalAmount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expenses"}, new Callable<List<ReportGroupSum>>() {
      @Override
      @NonNull
      public List<ReportGroupSum> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfGroupName = 0;
          final int _cursorIndexOfTotalAmount = 1;
          final List<ReportGroupSum> _result = new ArrayList<ReportGroupSum>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReportGroupSum _item;
            final String _tmpGroupName;
            if (_cursor.isNull(_cursorIndexOfGroupName)) {
              _tmpGroupName = null;
            } else {
              _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
            }
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            _item = new ReportGroupSum(_tmpGroupName,_tmpTotalAmount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ReportGroupSum>> getReportByLocation(final long startDate, final long endDate) {
    final String _sql = "SELECT city AS groupName, SUM(amount) AS totalAmount FROM expenses WHERE timestamp >= ? AND timestamp <= ? GROUP BY city ORDER BY totalAmount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expenses"}, new Callable<List<ReportGroupSum>>() {
      @Override
      @NonNull
      public List<ReportGroupSum> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfGroupName = 0;
          final int _cursorIndexOfTotalAmount = 1;
          final List<ReportGroupSum> _result = new ArrayList<ReportGroupSum>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReportGroupSum _item;
            final String _tmpGroupName;
            if (_cursor.isNull(_cursorIndexOfGroupName)) {
              _tmpGroupName = null;
            } else {
              _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
            }
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            _item = new ReportGroupSum(_tmpGroupName,_tmpTotalAmount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ReportGroupSum>> getReportByExpenseMode(final long startDate,
      final long endDate) {
    final String _sql = "SELECT e.name AS groupName, SUM(x.amount) AS totalAmount FROM expenses x LEFT JOIN expense_modes e ON x.expenseModeId = e.id WHERE x.timestamp >= ? AND x.timestamp <= ? GROUP BY e.name ORDER BY totalAmount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expenses",
        "expense_modes"}, new Callable<List<ReportGroupSum>>() {
      @Override
      @NonNull
      public List<ReportGroupSum> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfGroupName = 0;
          final int _cursorIndexOfTotalAmount = 1;
          final List<ReportGroupSum> _result = new ArrayList<ReportGroupSum>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReportGroupSum _item;
            final String _tmpGroupName;
            if (_cursor.isNull(_cursorIndexOfGroupName)) {
              _tmpGroupName = null;
            } else {
              _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
            }
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            _item = new ReportGroupSum(_tmpGroupName,_tmpTotalAmount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ReportGroupSum>> getReportByBank(final long startDate, final long endDate) {
    final String _sql = "SELECT b.name AS groupName, SUM(x.amount) AS totalAmount FROM expenses x LEFT JOIN banks b ON x.bankId = b.id WHERE x.timestamp >= ? AND x.timestamp <= ? GROUP BY b.name ORDER BY totalAmount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expenses",
        "banks"}, new Callable<List<ReportGroupSum>>() {
      @Override
      @NonNull
      public List<ReportGroupSum> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfGroupName = 0;
          final int _cursorIndexOfTotalAmount = 1;
          final List<ReportGroupSum> _result = new ArrayList<ReportGroupSum>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReportGroupSum _item;
            final String _tmpGroupName;
            if (_cursor.isNull(_cursorIndexOfGroupName)) {
              _tmpGroupName = null;
            } else {
              _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
            }
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            _item = new ReportGroupSum(_tmpGroupName,_tmpTotalAmount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

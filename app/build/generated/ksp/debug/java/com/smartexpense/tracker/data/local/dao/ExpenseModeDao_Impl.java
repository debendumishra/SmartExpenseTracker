package com.smartexpense.tracker.data.local.dao;

import android.database.Cursor;
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
import com.smartexpense.tracker.data.local.entity.ExpenseModeEntity;
import java.lang.Class;
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
public final class ExpenseModeDao_Impl implements ExpenseModeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExpenseModeEntity> __insertionAdapterOfExpenseModeEntity;

  private final EntityDeletionOrUpdateAdapter<ExpenseModeEntity> __updateAdapterOfExpenseModeEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeactivateCurrentMode;

  public ExpenseModeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExpenseModeEntity = new EntityInsertionAdapter<ExpenseModeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `expense_modes` (`id`,`name`,`isActive`,`createdAt`,`endedAt`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExpenseModeEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindLong(4, entity.getCreatedAt());
        if (entity.getEndedAt() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getEndedAt());
        }
      }
    };
    this.__updateAdapterOfExpenseModeEntity = new EntityDeletionOrUpdateAdapter<ExpenseModeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `expense_modes` SET `id` = ?,`name` = ?,`isActive` = ?,`createdAt` = ?,`endedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExpenseModeEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindLong(4, entity.getCreatedAt());
        if (entity.getEndedAt() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getEndedAt());
        }
        statement.bindLong(6, entity.getId());
      }
    };
    this.__preparedStmtOfDeactivateCurrentMode = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE expense_modes SET isActive = 0, endedAt = ? WHERE isActive = 1";
        return _query;
      }
    };
  }

  @Override
  public Object insertMode(final ExpenseModeEntity mode,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfExpenseModeEntity.insertAndReturnId(mode);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateMode(final ExpenseModeEntity mode,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfExpenseModeEntity.handle(mode);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deactivateCurrentMode(final long endTime,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeactivateCurrentMode.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, endTime);
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
          __preparedStmtOfDeactivateCurrentMode.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<ExpenseModeEntity> getActiveMode() {
    final String _sql = "SELECT * FROM expense_modes WHERE isActive = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expense_modes"}, new Callable<ExpenseModeEntity>() {
      @Override
      @Nullable
      public ExpenseModeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfEndedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "endedAt");
          final ExpenseModeEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpEndedAt;
            if (_cursor.isNull(_cursorIndexOfEndedAt)) {
              _tmpEndedAt = null;
            } else {
              _tmpEndedAt = _cursor.getLong(_cursorIndexOfEndedAt);
            }
            _result = new ExpenseModeEntity(_tmpId,_tmpName,_tmpIsActive,_tmpCreatedAt,_tmpEndedAt);
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
  public Flow<List<ExpenseModeEntity>> getAllModes() {
    final String _sql = "SELECT * FROM expense_modes ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"expense_modes"}, new Callable<List<ExpenseModeEntity>>() {
      @Override
      @NonNull
      public List<ExpenseModeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfEndedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "endedAt");
          final List<ExpenseModeEntity> _result = new ArrayList<ExpenseModeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExpenseModeEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpEndedAt;
            if (_cursor.isNull(_cursorIndexOfEndedAt)) {
              _tmpEndedAt = null;
            } else {
              _tmpEndedAt = _cursor.getLong(_cursorIndexOfEndedAt);
            }
            _item = new ExpenseModeEntity(_tmpId,_tmpName,_tmpIsActive,_tmpCreatedAt,_tmpEndedAt);
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

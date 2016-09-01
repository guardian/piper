package com.gu.piper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A table containing a certain type of objects T.
 */
public class Table<T> {

    @NonNull protected final SQLiteDatabase db;
    @NonNull protected final String tableName;
    @NonNull protected final Mapper<T> mapper;
    @NonNull private final String keySelection;

    public Table(@NonNull SQLiteDatabase db, @NonNull String tableName, @NonNull Mapper<T> mapper) {
        this.db = db;
        this.tableName = tableName;
        this.mapper = mapper;
        this.keySelection = selectionFromKeyColumns(mapper.getKeyColumns());
    }

    @NonNull
    private static String selectionFromKeyColumns(@NonNull String[] keyColumns) {
        String selection = keyColumns[0] + "=?";
        for (int i = 1; i < keyColumns.length; i++) {
            selection += " AND " + keyColumns[i] + "=?";
        }
        return selection;
    }

    @NonNull
    private List<T> listFromCursor(@NonNull Cursor cursor) {
        if (cursor.isBeforeFirst()) {
            cursor.moveToFirst();
        }

        final List<T> result = new ArrayList<>(cursor.getCount() - cursor.getPosition());
        while (!cursor.isAfterLast()) {
            result.add(mapper.fromCursor(cursor));
            cursor.moveToNext();
        }
        return result;
    }

    public long insert(@NonNull T t) throws IOException {
        return insert(t, SQLiteDatabase.CONFLICT_NONE);
    }

    public long insert(@NonNull T t, int conflictAlgorithm) throws IOException {
        if (db.isReadOnly()) {
            throw new IllegalArgumentException("db is read-only");
        }
        final long rowId = db.insertWithOnConflict(
                tableName,
                null,
                mapper.toContentValues(t),
                conflictAlgorithm
        );
        mapper.onNewId(t, rowId);
        return rowId;
    }

    @NonNull
    public final Cursor simpleQuery(@Nullable String[] cols, @Nullable String where, @Nullable String[] args) {
        return db.query(tableName, cols, where, args, null, null, null);
    }

    @NonNull
    public final List<T> getWhere(String where, String[] args) {
        return listFromCursor(simpleQuery(null, where, args));
    }

    @NonNull
    public final List<T> getAll() {
        return listFromCursor(simpleQuery(null, null, null));
    }

    public final boolean update(T t) {
        if (db.isReadOnly()) {
            throw new IllegalStateException("db is read-only");
        }
        final int updated = db.update(
                tableName,
                mapper.toContentValues(t),
                keySelection,
                mapper.getKeyValues(t)
        );
        return updated == 1;
    }

    public final boolean delete(T t) {
        if (db.isReadOnly()) {
            throw new IllegalStateException("db is read-only");
        }
        final int deleted = db.delete(
                tableName,
                keySelection,
                mapper.getKeyValues(t)
        );
        return deleted == 1;
    }
}

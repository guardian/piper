package com.gu.piper.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gu.piper.Mapper;
import com.gu.piper.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@link Table} implementation based on SQLite.
 */
public class SqliteTable<T> implements Table<T> {

    @NonNull private final SQLiteDatabase db;
    @NonNull private final String tableName;
    @NonNull private final Mapper<T> mapper;

    public SqliteTable(@NonNull SQLiteDatabase db, @NonNull String tableName, @NonNull Mapper<T> mapper) {
        this.db = db;
        this.tableName = tableName;
        this.mapper = mapper;
    }

    @NonNull
    private static String[] sortedKeys(@NonNull ContentValues values) {
        final String[] keys = values.keySet().toArray(new String[values.size()]);
        Arrays.sort(keys);
        return keys;
    }

    @NonNull
    private static String getKeySelection(@NonNull ContentValues values) {
        final String[] columns = sortedKeys(values);
        String selection = columns[0] + "=?";
        for (int i = 1; i < columns.length; i++) {
            selection += " AND " + columns[i] + "=?";
        }
        return selection;
    }

    @NonNull
    private static String[] getKeySelectionArgs(@NonNull ContentValues values) {
        final String[] columns = sortedKeys(values);
        final String[] args = new String[columns.length];
        for (int i = 0; i < columns.length; i++) {
            args[i] = values.getAsString(columns[i]);
        }
        return args;
    }

    @NonNull
    private ContentValues getContentValues() {
        // TODO use an object pool here
        return new ContentValues();
    }

    @NonNull
    private List<T> listFromCursor(@NonNull Cursor cursor) {
        if (cursor.isBeforeFirst()) {
            cursor.moveToFirst();
        }

        final List<T> result = new ArrayList<>(cursor.getCount() - cursor.getPosition());
        while (!cursor.isAfterLast()) {
            result.add(mapper.readFrom(cursor));
            cursor.moveToNext();
        }
        return result;
    }

    @Override
    public long insert(@NonNull T t) {
        if (db.isReadOnly()) {
            throw new IllegalArgumentException("db is read-only");
        }
        final ContentValues values = getContentValues();
        mapper.writeTo(t, values);
        return db.insert(tableName, null, values);
    }

    @NonNull
    private Cursor simpleQuery(@Nullable String[] cols, @Nullable String where, @Nullable String[] args) {
        return db.query(tableName, cols, where, args, null, null, null);
    }

    @Override
    @NonNull
    public final List<T> getAll() {
        return listFromCursor(simpleQuery(null, null, null));
    }

    @Override
    public final boolean update(T t) {
        if (db.isReadOnly()) {
            throw new IllegalStateException("db is read-only");
        }
        final ContentValues values = getContentValues();
        final ContentValues idValues = getContentValues();
        mapper.writeTo(t, values);
        mapper.writeIdTo(t, idValues);
        final int updated = db.update(
                tableName,
                values,
                getKeySelection(idValues),
                getKeySelectionArgs(idValues)
        );
        return updated == 1;
    }

    @Override
    public final boolean delete(T t) {
        if (db.isReadOnly()) {
            throw new IllegalStateException("db is read-only");
        }
        final ContentValues idValues = getContentValues();
        mapper.writeIdTo(t, idValues);
        final int deleted = db.delete(
                tableName,
                getKeySelection(idValues),
                getKeySelectionArgs(idValues)
        );
        return deleted == 1;
    }
}

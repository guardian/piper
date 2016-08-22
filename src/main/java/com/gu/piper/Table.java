package com.gu.piper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author Max Spencer <max.spencer@guardian.co.uk>
 */
public class Table<T> {

    @NonNull protected final SQLiteDatabase db;
    @NonNull protected final String tableName;
    @NonNull protected final Mapper<T> mapper;

    protected Table(@NonNull SQLiteDatabase db, @NonNull String tableName, @NonNull Mapper<T> mapper) {
        this.db = db;
        this.tableName = tableName;
        this.mapper = mapper;
    }

    @NonNull
    public static <T> Table<T> getTable(@NonNull SQLiteDatabase db, @NonNull String tableName, @NonNull Mapper<T> mapper) {
        return new Table<>(db, tableName, mapper);
    }

    @NonNull
    protected static String[] asArg(long id) {
        return new String[] { String.valueOf(id) };
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
        return insert(t, -1);
    }

    public long insert(@NonNull T t, int conflictAlgorithm) throws IOException {
        if (db.isReadOnly()) {
            throw new IllegalArgumentException("db is read-only");
        }

        final ContentValues values = mapper.toContentValues(t);
        if (conflictAlgorithm < 0) {
            return db.insert(tableName, null, values);
        } else {
            return db.insertWithOnConflict(tableName, null, values, conflictAlgorithm);
        }
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

}

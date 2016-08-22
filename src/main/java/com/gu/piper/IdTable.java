package com.gu.piper;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import java.io.IOException;

/**
 * TODO
 */
public abstract class IdTable<T> extends Table<T> {

    protected IdTable(@NonNull SQLiteDatabase db, @NonNull String tableName, @NonNull Mapper<T> mapper) {
        super(db, tableName, mapper);
    }

    protected abstract long getId(@NonNull T t);

    protected abstract void setId(@NonNull T t, long id);

    @Override
    public long insert(@NonNull T t, int conflictAlgorithm) throws IOException {
        final long id = super.insert(t, conflictAlgorithm);
        if (id >= 0) {
            setId(t, id);
        }
        return id;
    }

    public final boolean update(T t) {
        if (db.isReadOnly()) {
            throw new IllegalStateException("db is read-only");
        }

        final int updated = db.update(
                tableName,
                mapper.toContentValues(t),
                BaseColumns._ID + "=?",
                asArg(getId(t))
        );
        return updated == 1;
    }

    public final boolean delete(T t) {
        if (db.isReadOnly()) {
            throw new IllegalStateException("db is read-only");
        }

        final int deleted = db.delete(
                tableName,
                BaseColumns._ID + "=?",
                asArg(getId(t))
        );
        return deleted == 1;
    }
}

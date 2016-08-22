package com.gu.piper;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import java.io.IOException;

/**
 * TODO
 */
public abstract class IdTable<T> extends Table<T> {

    public IdTable(@NonNull SQLiteDatabase db, @NonNull String tableName, @NonNull Mapper<T> mapper) {
        super(db, tableName, mapper);
    }

    @NonNull
    public static <R extends Identifiable> IdTable<R> getIdTable(@NonNull SQLiteDatabase db, @NonNull String tableName, @NonNull Mapper<R> mapper) {
        return new IdTable<R>(db, tableName, mapper) {
            @Override
            protected long getId(@NonNull R r) {
                return r.getId();
            }

            @Override
            protected void setId(@NonNull R r, long id) {
                r.setId(id);
            }
        };
    }

    @NonNull
    public static <R> IdTable<R> getIdTable(@NonNull SQLiteDatabase db, @NonNull String tableName, @NonNull Mapper<R> mapper, @NonNull final Identifier<R> identifier) {
        return new IdTable<R>(db, tableName, mapper) {
            @Override
            protected long getId(@NonNull R r) {
                return identifier.getId(r);
            }

            @Override
            protected void setId(@NonNull R r, long id) {
                identifier.setId(r, id);
            }
        };
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

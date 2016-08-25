package com.gu.piper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * TODO
 */
public class UpdateQuery {

    @NonNull private final ContentValues values = new ContentValues();
    @Nullable private String where;
    @Nullable private String[] args;
    private int conflictAlgorithm = SQLiteDatabase.CONFLICT_NONE;

    protected UpdateQuery() {
    }

    @NonNull
    public UpdateQuery values(@NonNull ContentValues values) {
        this.values.putAll(values);
        return this;
    }

    @NonNull
    public UpdateQuery set(@NonNull String column, @Nullable Short value) {
        values.put(column, value);
        return this;
    }

    @NonNull
    public UpdateQuery set(@NonNull String column, @Nullable Long value) {
        values.put(column, value);
        return this;
    }

    @NonNull
    public UpdateQuery set(@NonNull String column, @Nullable Double value) {
        values.put(column, value);
        return this;
    }

    @NonNull
    public UpdateQuery set(@NonNull String column, @Nullable Integer value) {
        values.put(column, value);
        return this;
    }

    @NonNull
    public UpdateQuery set(@NonNull String column, @Nullable String value) {
        values.put(column, value);
        return this;
    }

    @NonNull
    public UpdateQuery set(@NonNull String column, @Nullable Boolean value) {
        values.put(column, value);
        return this;
    }

    @NonNull
    public UpdateQuery set(@NonNull String column, @Nullable Float value) {
        values.put(column, value);
        return this;
    }

    @NonNull
    public UpdateQuery set(@NonNull String column, @Nullable byte[] value) {
        values.put(column, value);
        return this;
    }

    @NonNull
    public UpdateQuery set(@NonNull String column, @Nullable Byte value) {
        values.put(column, value);
        return this;
    }

    @NonNull
    public UpdateQuery where(@Nullable String where, @Nullable String... args) {
        this.where = where;
        this.args = args;
        return this;
    }

    @NonNull
    public UpdateQuery conflictAlgorithm(int algorithm) {
        this.conflictAlgorithm = algorithm;
        return this;
    }

    public int execute(@NonNull SQLiteDatabase db, @NonNull String tableName) {
        return db.updateWithOnConflict(tableName, values, where, args, conflictAlgorithm);
    }

}

package com.gu.piper;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * TODO
 */
public class DeleteQuery {

    @NonNull private final SQLiteDatabase db;
    @NonNull private final String tableName;
    @Nullable private String where;
    @Nullable private String[] args;

    public DeleteQuery(@NonNull SQLiteDatabase db, @NonNull String tableName) {
        this.db = db;
        this.tableName = tableName;
    }

    @NonNull
    public DeleteQuery where(@Nullable String where, String... args) {
        this.where = where;
        this.args = args;
        return this;
    }

    public int execute() {
        return db.delete(tableName, where, args);
    }

}

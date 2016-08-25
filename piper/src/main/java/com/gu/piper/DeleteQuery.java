package com.gu.piper;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * TODO
 */
public class DeleteQuery {

    @Nullable private String where;
    @Nullable private String[] args;

    public DeleteQuery() {
    }

    @NonNull
    public DeleteQuery where(@Nullable String where, String... args) {
        this.where = where;
        this.args = args;
        return this;
    }

    public int execute(@NonNull SQLiteDatabase db, @NonNull String tableName) {
        return db.delete(tableName, where, args);
    }

}

package com.gu.piper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * TODO
 */
public class SelectQuery {

    private boolean distinct = false;
    @Nullable private String where;
    @Nullable private String[] args;
    @Nullable private String groupBy;
    @Nullable private String having;
    @Nullable private String orderBy;
    @Nullable private String limit;

    public SelectQuery() {
    }

    @NonNull
    public SelectQuery distinct(boolean distinct) {
        this.distinct = distinct;
        return this;
    }

    @NonNull
    public SelectQuery where(@Nullable String where, @Nullable String... args) {
        this.where = where;
        this.args = args;
        return this;
    }

    @NonNull
    public SelectQuery groupBy(@Nullable String groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    @NonNull
    public SelectQuery having(@Nullable String having) {
        this.having = having;
        return this;
    }

    @NonNull
    public SelectQuery orderBy(@Nullable String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    @NonNull
    public SelectQuery limit(int limit) {
        this.limit = limit > 0 ? Integer.toString(limit) : null;
        return this;
    }

    @NonNull
    public Cursor cursor(@NonNull SQLiteDatabase db, @NonNull String tableName) {
        return db.query(distinct, tableName, null, where, args, groupBy, having, orderBy, limit);
    }

    public int count(@NonNull SQLiteDatabase db, @NonNull String tableName) {
        // TODO
        return 0;
    }

    public boolean exists(@NonNull SQLiteDatabase db, @NonNull String tableName) {
        // TODO
        return false;
    }
}

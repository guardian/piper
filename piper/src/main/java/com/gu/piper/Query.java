package com.gu.piper;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collections;
import java.util.List;

/**
 * TODO
 */
public class Query<T> {

    private boolean distinct = false;
    @Nullable private String where;
    @Nullable private String[] args;
    @Nullable private String groupBy;
    @Nullable private String having;
    @Nullable private String orderBy;
    @Nullable private String limit;

    public Query() {
    }

    @NonNull
    public Query<T> distinct(boolean distinct) {
        this.distinct = distinct;
        return this;
    }

    @NonNull
    public Query<T> where(@Nullable String where, @Nullable String[] args) {
        this.where = where;
        this.args = args;
        return this;
    }

    @NonNull
    public Query<T> groupBy(@Nullable String groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    @NonNull
    public Query<T> having(@Nullable String having) {
        this.having = having;
        return this;
    }

    @NonNull
    public Query<T> orderBy(@Nullable String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    @NonNull
    public Query<T> limit(int limit) {
        this.limit = limit > 0 ? Integer.toString(limit) : null;
        return this;
    }

    @NonNull
    public List<T> select(@NonNull Table<T> table) {
        Cursor result = null;
        try {
            result = table.db.query(distinct, table.tableName, null, where, args, groupBy, having, orderBy, limit);
            return table.listFromCursor(result);
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }

    public int count() {
        // TODO
        return 0;
    }

    public boolean exists() {
        // TODO
        return false;
    }
}

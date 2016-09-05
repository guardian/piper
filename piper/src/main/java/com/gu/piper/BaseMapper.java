package com.gu.piper;

import android.support.annotation.NonNull;

/**
 * TODO
 */
public abstract class BaseMapper<T> implements Mapper<T> {

    @NonNull private final String[] keyColumns;

    public BaseMapper(@NonNull String keyColumn) {
        keyColumns = new String[] { keyColumn };
    }

    @NonNull
    @Override
    public final String[] getKeyColumns() {
        return keyColumns;
    }

    @NonNull
    @Override
    public final String[] getKeyValues(@NonNull T t) {
        return new String[] { getKeyValue(t) };
    }

    @Override
    public void onNewId(@NonNull T t, long rowId) {

    }

    protected abstract String getKeyValue(@NonNull T t);
}

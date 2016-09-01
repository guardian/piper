package com.gu.piper;

import android.provider.BaseColumns;
import android.support.annotation.NonNull;

/**
 * TODO
 */
public abstract class IdMapper<T> extends SimpleMapper<T> {

    public IdMapper() {
        super(BaseColumns._ID);
    }

    @Override
    public void onNewId(@NonNull T t, long rowId) {
        setId(t, rowId);
    }

    @Override
    protected String getKeyValue(T t) {
        return Long.toString(getId(t));
    }

    protected abstract long getId(T t);

    protected abstract void setId(T t, long id);
}

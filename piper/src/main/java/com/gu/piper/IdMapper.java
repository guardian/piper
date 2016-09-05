package com.gu.piper;

import android.provider.BaseColumns;
import android.support.annotation.NonNull;

/**
 * TODO
 */
public abstract class IdMapper<T> extends BaseMapper<T> {

    public IdMapper() {
        super(BaseColumns._ID);
    }

    @Override
    public void onNewId(@NonNull T t, long rowId) {
        setId(t, rowId);
    }

    @Override
    protected String getKeyValue(@NonNull T t) {
        return Long.toString(getId(t));
    }

    protected abstract long getId(@NonNull T t);

    protected abstract void setId(@NonNull T t, long id);
}

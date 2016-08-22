package com.gu.piper.orm;

import android.support.annotation.NonNull;

/**
 * TODO
 */
public interface Identifier<T> {

    long getId(@NonNull T t);

    void setId(@NonNull T t, long id);

}

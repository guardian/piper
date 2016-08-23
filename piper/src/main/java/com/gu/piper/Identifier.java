package com.gu.piper;

import android.support.annotation.NonNull;

/**
 * An Identifier can get and set the ID of a certain type of object T. Use an identifier when you
 * are unable to have T implement {@link Identifiable} itself, for example if T is a generated
 * class.
 */
public interface Identifier<T> {
    long getId(@NonNull T t);
    void setId(@NonNull T t, long id);
}

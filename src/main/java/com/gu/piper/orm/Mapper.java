package com.gu.piper.orm;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

/**
 * Encapsulation of how a specified type can be converted to a {@link ContentValues} instance and
 * created from a {@link Cursor}.
 *
 * @author Max Spencer <max.spencer@guardian.co.uk>
 */
public interface Mapper<T> {

    /**
     * Create a new {@link ContentValues} from a T.
     *
     * @param t a T
     * @return a new ContentValues
     */
    @NonNull
    ContentValues toContentValues(@NonNull T t);

    /**
     * Create a new T from a non-empty {@link Cursor} instance.
     *
     * @param cursor to read from
     * @return a new T
     */
    @NonNull
    T fromCursor(@NonNull Cursor cursor);

}

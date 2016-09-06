package com.gu.piper;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

/**
 * Encapsulation of how a specified type can be converted to a {@link ContentValues} instance and
 * created from a {@link Cursor}.
 */
public interface Mapper<T> {

    /**
     * Write a T into a {@link ContentValues} map.
     *
     * @param t a T
     * @param values a ContentValues to write to
     */
    void writeTo(@NonNull T t, @NonNull ContentValues values);

    /**
     * Write the ID value(s) of a T into a {@link ContentValues} map.
     *
     * @param t a T to write the ID value(s) of
     * @param values a ContentValues to write them to
     */
    void writeIdTo(@NonNull T t, @NonNull ContentValues values);

    /**
     * Create a new T from a non-empty {@link Cursor} instance.
     *
     * @param cursor to read from
     * @return a new T
     */
    @NonNull
    T readFrom(@NonNull Cursor cursor);
}

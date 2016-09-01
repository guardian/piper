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
     * Get an array of one or more names of columns which contain the primary key of a T.
     *
     * @return an array of column names
     */
    @NonNull
    String[] getKeyColumns();

    /**
     * Get an array of one or more values from a T which form its primary key.
     *
     * @param t a T
     * @return an array of values
     */
    @NonNull
    String[] getKeyValues(@NonNull T t);

    /**
     * Called when a T is inserted and a new row ID is returned.
     * <p>
     * You may override this method to set the ID of <code>t</code> if appropriate, or use the new
     * ID in some other way.
     *
     * @param t which was inserted
     * @param rowId the new row ID
     */
    void onNewId(@NonNull T t, long rowId);

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

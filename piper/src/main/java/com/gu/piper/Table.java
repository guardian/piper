package com.gu.piper;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * A table containing a certain type of objects T.
 */
public interface Table<T> {

    /**
     * Insert a T into this table.
     *
     * @param t to insert
     * @return the new row ID
     */
    long insert(@NonNull T t);

    /**
     * Return a list of all the Ts in this table.
     *
     * @return list of Ts in the table
     */
    @NonNull
    List<T> getAll();

    /**
     * Update a T in this table.
     *
     * @param t to update
     * @return <code>true</code> if <code>t</code> was found in this table and updated successfully
     */
    boolean update(T t);

    /**
     * Delete a T from this table.
     *
     * @param t to delete
     * @return <code>true</code> if <code>t</code> was found in this table and deleted successfully
     */
    boolean delete(T t);
}

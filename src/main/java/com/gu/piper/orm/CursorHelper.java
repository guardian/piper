package com.gu.piper.orm;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * TODO
 */
public final class CursorHelper {

    private CursorHelper() {
        // Non-instantiable helper class
    }

    @Nullable
    public static byte[] getBlob(@NonNull Cursor cursor, @NonNull String col, @Nullable byte[] def) {
        final int index = cursor.getColumnIndex(col);
        return index >= 0 ? cursor.getBlob(index) : def;
    }

    @Nullable
    public static byte[] requireBlob(@NonNull Cursor cursor, @NonNull String col) {
        return cursor.getBlob(cursor.getColumnIndexOrThrow(col));
    }

    public static double getDouble(@NonNull Cursor cursor, @NonNull String col, double def) {
        final int index = cursor.getColumnIndex(col);
        return index >= 0 ? cursor.getDouble(index) : def;
    }

    public static double requireDouble(@NonNull Cursor cursor, @NonNull String col) {
        return cursor.getDouble(cursor.getColumnIndexOrThrow(col));
    }

    public static float getFloat(@NonNull Cursor cursor, @NonNull String col, float def) {
        final int index = cursor.getColumnIndex(col);
        return index >= 0 ? cursor.getFloat(index) : def;
    }

    public static float requireFloat(@NonNull Cursor cursor, @NonNull String col) {
        return cursor.getFloat(cursor.getColumnIndexOrThrow(col));
    }

    public static int getInt(@NonNull Cursor cursor, @NonNull String col, int def) {
        final int index = cursor.getColumnIndex(col);
        return index >= 0 ? cursor.getInt(index) : def;
    }

    public static int requireInt(@NonNull Cursor cursor, @NonNull String col) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(col));
    }

    public static long getLong(@NonNull Cursor cursor, @NonNull String col, long def) {
        final int index = cursor.getColumnIndex(col);
        return index >= 0 ? cursor.getLong(index) : def;
    }

    public static long requireLong(@NonNull Cursor cursor, @NonNull String col) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(col));
    }

    @Nullable
    public static String getString(@NonNull Cursor cursor, @NonNull String col, @Nullable String def) {
        final int index = cursor.getColumnIndex(col);
        return index >= 0 ? cursor.getString(index) : def;
    }

    @Nullable
    public static String requireString(@NonNull Cursor cursor, @NonNull String col) {
        return cursor.getString(cursor.getColumnIndexOrThrow(col));
    }

}

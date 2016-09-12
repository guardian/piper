package com.gu.pipersample;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.gu.piper.CursorHelper;
import com.gu.piper.Mapper;

import java.util.Date;

/**
 * TODO
 */
public final class PeopleTable implements BaseColumns {

    private PeopleTable() {
        // Non-instantiable class
    }

    public static final String TABLE_NAME = "people";
    public static final String NAME = "name";
    public static final String JOB = "job";
    public static final String DOB = "dob";

    public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            NAME + " TEXT," +
            JOB + " TEXT," +
            DOB + " INTEGER)";

    public static final String DROP_SQL = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final Mapper<Person> MAPPER = new Mapper<Person>() {

        @Override
        public void writeTo(@NonNull Person person, @NonNull ContentValues values) {
            values.put(NAME, person.getName());
            values.put(JOB, person.getJob());
            values.put(DOB, person.getDob().getTime());
        }

        @Override
        public void writeIdTo(@NonNull Person person, @NonNull ContentValues values) {
            values.put(_ID, person.getId());
        }

        @NonNull
        @Override
        public Person readFrom(@NonNull Cursor cursor) {
            return new Person(
                    CursorHelper.requireLong(cursor, _ID),
                    CursorHelper.requireString(cursor, NAME),
                    CursorHelper.requireString(cursor, JOB),
                    new Date(CursorHelper.requireLong(cursor, DOB))
            );
        }
    };
}

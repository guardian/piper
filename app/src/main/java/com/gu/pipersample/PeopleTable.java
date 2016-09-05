package com.gu.pipersample;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.gu.piper.CursorHelper;
import com.gu.piper.IdMapper;
import com.gu.piper.Mapper;

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

    public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            NAME + " TEXT," +
            JOB + " TEXT)";

    public static final String DROP_SQL = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final Mapper<Person> MAPPER = new IdMapper<Person>() {

        @Override
        protected long getId(@NonNull Person person) {
            return person.getId();
        }

        @Override
        protected void setId(@NonNull Person person, long id) {
            person.setId(id);
        }

        @NonNull
        @Override
        public ContentValues toContentValues(@NonNull Person person) {
            ContentValues values = new ContentValues(2);
            if (person.hasId()) values.put(_ID, person.getId());
            values.put(NAME, person.getName());
            values.put(JOB, person.getJob());
            return values;
        }

        @NonNull
        @Override
        public Person fromCursor(@NonNull Cursor cursor) {
            return new Person(
                    CursorHelper.requireLong(cursor, _ID),
                    CursorHelper.requireString(cursor, NAME),
                    CursorHelper.requireString(cursor, JOB)
            );
        }
    };
}

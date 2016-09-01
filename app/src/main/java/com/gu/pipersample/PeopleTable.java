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

    public static final String _TABLE = "people";
    public static final String NAME = "name";
    public static final String JOB = "job";

    public static final String _CREATE_SQL = "CREATE TABLE " + _TABLE + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            NAME + " TEXT," +
            JOB + " TEXT)";

    public static final Mapper<Person> _MAPPER = new IdMapper<Person>() {

        @Override
        protected long getId(Person person) {
            return person.getId();
        }

        @Override
        protected void setId(Person person, long id) {
            person.setId(id);
        }

        @NonNull
        @Override
        public ContentValues toContentValues(@NonNull Person person) {
            ContentValues values = new ContentValues(2);
            values.put(NAME, person.getName());
            values.put(JOB, person.getJob());
            return values;
        }

        @NonNull
        @Override
        public Person fromCursor(@NonNull Cursor cursor) {
            return new Person(
                    CursorHelper.requireString(cursor, NAME),
                    CursorHelper.requireString(cursor, JOB)
            );
        }
    };
}

package com.gu.pipersample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gu.piper.Table;

/**
 * TODO
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "pipersample.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PeopleTable.CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(PeopleTable.DROP_SQL);
        sqLiteDatabase.execSQL(PeopleTable.CREATE_SQL);
    }

    public Table<Person> getPeopleTable() {
        return new Table<>(getWritableDatabase(), PeopleTable.TABLE_NAME, PeopleTable.MAPPER);
    }
}

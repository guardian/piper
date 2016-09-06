package com.gu.pipersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gu.piper.Table;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Table<Person> peopleTable = new DbHelper(this).getPeopleTable();
        Person max = new Person("Max Spencer", "Android developer");

        try {
            max.setId(peopleTable.insert(max));
        } catch (IOException e) {
            Log.w("MainActivity", e);
        }


    }
}

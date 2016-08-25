package com.gu.pipersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gu.piper.Queries;
import com.gu.piper.SelectQuery;
import com.gu.piper.Table;
import com.gu.piper.UpdateQuery;

import java.util.Arrays;
import java.util.List;

import static com.gu.piper.Queries.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Table<Object> employees = Table.getTable(null, null, null);

        List<Object> bestPaidInDigDev = employees.list(select()
                .distinct(true)
                .orderBy("salary DESC")
                .limit(10)
                .where("department=DigDev")
        );

        int millionaireCount = employees.count(select().where("salary>1000000"));

        employees.run(delete().where("role = ?", null));

        employees.run(update().set("salary", 100000).where("role = Android"));

        UpdateQuery query = update().set("salary", 100000);
        List<String> names = Arrays.asList("Jack", "Jill", "Jeremy");
        for (String name : names) {
            employees.run(query.where("name = ?", name));
        }

        employees.run(update()
                .set("salary", 100000)
                .where("name ")
        );

        UpdateQuery query2 = Queries.update().set("salary", 100000).where("name = Max");
        if (employees.run(query2) != 1) {
            // there was a problem
        }

        employees.list(select());
    }
}

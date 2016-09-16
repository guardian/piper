package com.gu.pipersample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, PeopleFragment.PeopleFragmentListener {

    private FloatingActionButton addFab;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        addFab = (FloatingActionButton) findViewById(R.id.add_fab);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main, new PeopleFragment())
                    .commit();
            getSupportFragmentManager().addOnBackStackChangedListener(this);
        }
    }

    public void onAddClick(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new NewPersonFragment())
                .addToBackStack(null)
                .commit();
        addFab.setVisibility(View.GONE);
        getSupportActionBar().setTitle("New person");
    }

    @Override
    public void onBackStackChanged() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            addFab.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("People");
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onPersonClick(Person person) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, PersonFragment.newInstance(person))
                .addToBackStack(null)
                .commit();
        addFab.setVisibility(View.GONE);
    }
}

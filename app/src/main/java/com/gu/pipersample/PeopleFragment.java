package com.gu.pipersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.Comparator;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * TODO
 */
public class PeopleFragment extends Fragment implements PeopleAdapter.PeopleAdapterListener {

    private static final String TAG = PeopleFragment.class.getSimpleName();

    public interface PeopleFragmentListener {
        void onPersonClick(Person person);
    }

    private final PeopleAdapter adapter = new PeopleAdapter(this);

    @Nullable
    private PeopleFragmentListener getListener() {
        return getActivity() instanceof PeopleFragmentListener ? (PeopleFragmentListener) getActivity() : null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_people, container, false);
        final RecyclerView list = (RecyclerView) view.findViewById(R.id.list);
        final RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list.setAdapter(adapter);
        list.setLayoutManager(layout);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_name:
                Log.d("TAG", "Sort by name");
                adapter.setSortOrder((p1, p2) -> p1.getName().compareTo(p2.getName()));
                return true;
            case R.id.sort_dob:
                Log.d("TAG", "Sort by DOB");
                adapter.setSortOrder((p1, p2) -> p1.getDob().compareTo(p2.getDob()));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPeople();
    }

    private void loadPeople() {
        Observable
                .create((Observable.OnSubscribe<List<Person>>) subscriber -> {
                    subscriber.onNext(new DbHelper(getActivity()).getPeopleTable().getAll());
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(people -> {
                    Log.d(TAG, "Loaded " + people.size() + " people from database");
                    adapter.setData(people);
                }, error -> {
                    Log.w(TAG, "Unable to load people", error);
                });
    }

    @Override
    public void onPersonClick(Person person) {
        if (getListener() != null) {
            getListener().onPersonClick(person);
        }
    }
}

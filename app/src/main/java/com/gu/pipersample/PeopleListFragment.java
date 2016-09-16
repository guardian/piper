package com.gu.pipersample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * TODO
 */
public class PeopleListFragment extends Fragment implements PersonAdapter.PersonAdapterListener {

    private static final String TAG = PeopleListFragment.class.getSimpleName();

    public interface PeopleListListener {
        void onPersonClick(Person person);
    }

    private final PersonAdapter adapter = new PersonAdapter(this);

    @Nullable
    private PeopleListListener getListener() {
        return getActivity() instanceof PeopleListListener ? (PeopleListListener) getActivity() : null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_people_list, container, false);
        final RecyclerView list = (RecyclerView) view.findViewById(R.id.list);
        final RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list.setAdapter(adapter);
        list.setLayoutManager(layout);
        return view;
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

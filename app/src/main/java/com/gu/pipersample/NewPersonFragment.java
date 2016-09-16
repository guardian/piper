package com.gu.pipersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * TODO
 */
public class NewPersonFragment extends Fragment {

    private EditText nameInput;
    private EditText jobInput;
    private DatePicker dobPicker;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_new_person, container, false);
        nameInput = (EditText) view.findViewById(R.id.name_input);
        jobInput = (EditText) view.findViewById(R.id.job_input);
        dobPicker = (DatePicker) view.findViewById(R.id.dob_picker);
        view.findViewById(R.id.save_button).setOnClickListener(this::onSaveClick);
        return view;
    }

    public void onSaveClick(View view) {
        if (validate()) {
            final String name = nameInput.getText().toString();
            final String job = jobInput.getText().toString();
            final Date dob = new GregorianCalendar(dobPicker.getYear(), dobPicker.getMonth(), dobPicker.getDayOfMonth()).getTime();
            final Person person = new Person(name, job, dob);
            insertPerson(person).subscribe(new Observer<Void>() {
                @Override
                public void onCompleted() {
                    Toast.makeText(getActivity(), getString(R.string.fmt_added, name), Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(getActivity(), R.string.problem_occurred, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(Void aVoid) {
                    // Unused
                }
            });
        }
    }

    private boolean validate() {
        boolean isValid = true;
        if (nameInput.getText().toString().isEmpty()) {
            nameInput.setError(getString(R.string.required));
            isValid = false;
        }
        if (jobInput.getText().toString().isEmpty()) {
            jobInput.setError(getString(R.string.required));
            isValid = false;
        }
        return isValid;
    }

    private Observable<Void> insertPerson(final Person person) {
        return Observable.create((Observable.OnSubscribe<Void>) subscriber -> {
            try {
                new DbHelper(getActivity()).getPeopleTable().insert(person);
                Log.d("Inserted", person.toString());
                subscriber.onCompleted();
            } catch (IOException e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}

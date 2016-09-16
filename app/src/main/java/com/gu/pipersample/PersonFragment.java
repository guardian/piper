package com.gu.pipersample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.TimeZone;

/**
 * TODO
 */
public class PersonFragment extends Fragment {

    private static final String ARG_PERSON = "person";
    private static final DateFormat DOB_FORMAT = DateFormat.getDateInstance();
    static {
        DOB_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static PersonFragment newInstance(@NonNull Person person) {
        final PersonFragment fragment = new PersonFragment();
        final Bundle args = new Bundle();
        args.putParcelable(ARG_PERSON, person);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    private Person getPerson() {
        final Person person = getArguments().getParcelable(ARG_PERSON);
        if (person == null) {
            throw new RuntimeException("PersonFragment with no person arg");
        }
        return person;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_person, container, false);
        ((TextView) view.findViewById(R.id.name)).setText(getPerson().getName());
        ((TextView) view.findViewById(R.id.job)).setText(getPerson().getJob());
        ((TextView) view.findViewById(R.id.dob)).setText(DOB_FORMAT.format(getPerson().getDob()));
        view.findViewById(R.id.edit_fab).setOnClickListener(this::onEditClick);
        return view;
    }

    private void onEditClick(View view) {
        // TODO
    }
}

package com.gu.pipersample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO
 */
public class PersonAdapter extends android.support.v7.widget.RecyclerView.Adapter<PersonAdapter.PersonHolder> {

    public static class PersonHolder extends RecyclerView.ViewHolder {

        private final TextView nameView;
        private final TextView jobView;

        public PersonHolder(View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.name);
            jobView = (TextView) view.findViewById(R.id.job);
        }
    }

    @NonNull private final List<Person> data = new ArrayList<>();
    @Nullable private LayoutInflater inflater = null;

    public PersonAdapter() {
        setHasStableIds(true);
    }

    public void setData(@NonNull Collection<Person> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @NonNull
    private LayoutInflater getInflater(View view) {
        if (inflater == null) {
            inflater = LayoutInflater.from(view.getContext());
        }
        return inflater;
    }

    @Override
    public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonHolder(getInflater(parent).inflate(R.layout.item_person, parent, false));
    }

    @Override
    public void onBindViewHolder(PersonHolder holder, int position) {
        final Person person = data.get(position);
        holder.nameView.setText(person.getName());
        holder.jobView.setText(person.getJob());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

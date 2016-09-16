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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * TODO
 */
public class PeopleAdapter extends android.support.v7.widget.RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameView;
        private final TextView jobView;

        public ViewHolder(View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.name);
            jobView = (TextView) view.findViewById(R.id.job);
        }
    }

    public interface PeopleAdapterListener {
        void onPersonClick(Person person);
    }

    @NonNull private final List<Person> data = new ArrayList<>();
    @NonNull private final List<Person> sortedData = new ArrayList<>();
    @Nullable private Comparator<Person> comparator = null;
    @Nullable private LayoutInflater inflater = null;
    @Nullable private PeopleAdapterListener listener = null;

    public PeopleAdapter(@Nullable PeopleAdapterListener listener) {
        setHasStableIds(true);
        this.listener = listener;
    }

    public void setData(@NonNull Collection<Person> data) {
        this.data.clear();
        this.data.addAll(data);
        sortData();
    }

    public void setSortOrder(@NonNull Comparator<Person> comparator) {
        this.comparator = comparator;
        sortData();
    }

    private void sortData() {
        sortedData.clear();
        sortedData.addAll(data);
        if (comparator != null) {
            Collections.sort(sortedData, comparator);
        }
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return sortedData.get(position).getId();
    }

    @NonNull
    private LayoutInflater getInflater(View view) {
        if (inflater == null) {
            inflater = LayoutInflater.from(view.getContext());
        }
        return inflater;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getInflater(parent).inflate(R.layout.item_person, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Person person = sortedData.get(position);
        holder.nameView.setText(person.getName());
        holder.jobView.setText(person.getJob());
        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onPersonClick(person);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sortedData.size();
    }
}

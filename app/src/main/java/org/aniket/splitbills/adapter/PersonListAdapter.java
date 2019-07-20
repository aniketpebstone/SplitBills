package org.aniket.splitbills.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.aniket.splitbills.model.*;

import androidx.recyclerview.widget.RecyclerView;

import org.aniket.splitbills.R;
import org.aniket.splitbills.model.Session;

import java.util.Collections;
import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolder> {

    class PersonViewHolder extends RecyclerView.ViewHolder {
        private final TextView personItemView;
        private final LinearLayout llAllPersons;

        private PersonViewHolder(View itemView) {
            super(itemView);
            personItemView = itemView.findViewById(R.id.tv_personItem);
            llAllPersons = (LinearLayout) itemView;
        }
    }

    private final LayoutInflater mInflater;
    private List<Person> mPersons = Collections.emptyList(); // Cached copy of words

    public PersonListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.persons_item, parent, false);
        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        Person current = mPersons.get(position);
        holder.personItemView.setText(current.getName());
        holder.llAllPersons.setTag(current.getId());
    }

    public void setPersons(List<Person> persons) {
        mPersons = persons;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }
}
package org.aniket.splitbills.adapter;

import android.content.ClipData;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.aniket.splitbills.R;
import org.aniket.splitbills.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonListInTxnAdapter extends RecyclerView.Adapter<PersonListInTxnAdapter.PersonViewHolder> {

    List<Integer> personIds=new ArrayList<>();

    class PersonViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox personItemView;
        private final LinearLayout llAllPersons;

        private PersonViewHolder(View itemView) {
            super(itemView);
            personItemView = itemView.findViewById(R.id.cbk_personsInTxn);
            llAllPersons = (LinearLayout) itemView;

        }
    }

    private final LayoutInflater mInflater;
    private List<Person> mPersons = Collections.emptyList(); // Cached copy of words

    public PersonListInTxnAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.persons_in_txn_item, parent, false);
        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        Person current = mPersons.get(position);
        holder.personItemView.setText(current.getName());
        holder.personItemView.setTag(current.getId());
        holder.personItemView.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked)
                            personIds.add((Integer)(compoundButton.getTag()));
                        else
                            personIds.remove(compoundButton.getTag());
                    }

                });
    }

    public void setPersons(List<Person> persons) {
        mPersons = persons;
        notifyDataSetChanged();
    }

    public List<Integer>  getPersonsIds() {
        return personIds;
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }
}
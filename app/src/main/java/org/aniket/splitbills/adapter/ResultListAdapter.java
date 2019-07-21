package org.aniket.splitbills.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.aniket.splitbills.R;
import org.aniket.splitbills.model.*;

import java.util.Collections;
import java.util.List;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.PersonViewHolder> {

    class PersonViewHolder extends RecyclerView.ViewHolder {
        private final TextView resultItemView;
        private final LinearLayout llAllPersons;

        private PersonViewHolder(View itemView) {
            super(itemView);
            resultItemView = itemView.findViewById(R.id.tv_personItem);
            llAllPersons = (LinearLayout) itemView;
        }
    }

    private final LayoutInflater mInflater;
    private List<Result> mResult = Collections.emptyList(); // Cached copy of words

    public ResultListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.persons_item, parent, false);
        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        Result current = mResult.get(position);
//        holder.personItemView.setText(current.getName());

    }

    public void setResult(List<Result> result) {
        mResult = result;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mResult.size();
    }
}
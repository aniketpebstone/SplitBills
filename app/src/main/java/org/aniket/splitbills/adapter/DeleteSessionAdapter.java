package org.aniket.splitbills.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.aniket.splitbills.R;
import org.aniket.splitbills.model.Session;

import java.util.Collections;
import java.util.List;

public class DeleteSessionAdapter extends RecyclerView.Adapter<DeleteSessionAdapter.DeleteSessionViewHolder> {

    class DeleteSessionViewHolder extends RecyclerView.ViewHolder {
        private final TextView sessionItemView;
        private final LinearLayout llDeleteSessionItemView;

        private DeleteSessionViewHolder(View itemView) {
            super(itemView);
            sessionItemView = itemView.findViewById(R.id.tv_delete_session);
            llDeleteSessionItemView = (LinearLayout) itemView;
        }
    }

    private final LayoutInflater mInflater;
    private List<Session> mSessions = Collections.emptyList(); // Cached copy of words

    public DeleteSessionAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public DeleteSessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.delete_sessons_item, parent, false);
        return new DeleteSessionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DeleteSessionViewHolder holder, int position) {
        Session current = mSessions.get(position);
        holder.sessionItemView.setText(current.getName());
        holder.llDeleteSessionItemView.setTag(current.getId());
    }

    public void setSessions(List<Session> sessions) {
        mSessions = sessions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mSessions.size();
    }

}
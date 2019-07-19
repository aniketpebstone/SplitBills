package org.aniket.splitbills.dao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.aniket.splitbills.R;
import org.aniket.splitbills.model.Session;

import java.util.Collections;
import java.util.List;

public class SessionListAdapter extends RecyclerView.Adapter<SessionListAdapter.SessionViewHolder> {

    class SessionViewHolder extends RecyclerView.ViewHolder {
        private final TextView sessionItemView;

        private SessionViewHolder(View itemView) {
            super(itemView);
            sessionItemView = itemView.findViewById(R.id.session_view);
        }
    }

    private final LayoutInflater mInflater;
    private List<Session> mSessions = Collections.emptyList(); // Cached copy of words

    public SessionListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public SessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.sessons_item, parent, false);
        return new SessionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SessionViewHolder holder, int position) {
        Session current = mSessions.get(position);
        holder.sessionItemView.setText(current.getName());
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
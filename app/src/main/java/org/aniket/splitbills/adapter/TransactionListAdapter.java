package org.aniket.splitbills.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.aniket.splitbills.R;
import org.aniket.splitbills.model.Person;
import org.aniket.splitbills.model.Transaction;

import java.util.Collections;
import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.TxnViewHolder> {

    class TxnViewHolder extends RecyclerView.ViewHolder {
        private final TextView txnItemView;
        private final LinearLayout llAllTxns;

        private TxnViewHolder(View itemView) {
            super(itemView);
            txnItemView = itemView.findViewById(R.id.tv_txnItem);
            llAllTxns = (LinearLayout) itemView;
        }
    }

    private final LayoutInflater mInflater;
    private List<Transaction> mTxns = Collections.emptyList(); // Cached copy of words

    public TransactionListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public TxnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.transaction_item, parent, false);
        return new TxnViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TxnViewHolder holder, int position) {
        Transaction current = mTxns.get(position);
        System.out.println("Transaction:"+current);
        holder.txnItemView.setText(current.getExpense());
        holder.llAllTxns.setTag(current.getId());
    }

    public void setPersons(List<Transaction> transactions) {
        mTxns = transactions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTxns.size();
    }
}
package org.aniket.splitbills.Screens;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.aniket.splitbills.R;
import org.aniket.splitbills.adapter.*;
import org.aniket.splitbills.model.*;
import org.aniket.splitbills.model.*;

import java.util.List;

public class AllTransactions extends AppCompatActivity {

    private TransactionViewModel mTxnViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_transactions);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllTransactions.this, AddTransaction.class);
                startActivity(intent);
            }
        });


        mTxnViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        mTxnViewModel.getAllTransactionsBySessionId().observe(this, new Observer<List<Transaction>>() {
            @Override
            public void onChanged(@Nullable final List<Transaction> transactions) {

                if(transactions==null || transactions.isEmpty())
                {
                    RecyclerView recyclerView = findViewById(R.id.rv_allTransactions);
                    recyclerView.setVisibility(View.GONE);
                }
                else
                {
                    TextView textView=findViewById(R.id.tv_emptyText3);
                    textView.setVisibility(View.GONE);
                    RecyclerView recyclerView = findViewById(R.id.rv_allTransactions);
                    recyclerView.setVisibility(View.VISIBLE);
                    TransactionListAdapter adapter = new TransactionListAdapter(AllTransactions.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(AllTransactions.this));
                    recyclerView.setAdapter(adapter);
                    adapter.setPersons(transactions);
                }
            }
        });
    }

    public void onTransactionItemClicked(View view) {
        LinearLayout linearLayout= (LinearLayout) view.getParent();
        int id=Integer.parseInt(linearLayout.getTag().toString());
        System.out.println("======================================== ID: "+id);
    }

}

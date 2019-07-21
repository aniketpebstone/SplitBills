package org.aniket.splitbills.Screens;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import org.aniket.splitbills.R;
import org.aniket.splitbills.model.*;

public class AddTransaction extends AppCompatActivity {

    private TransactionViewModel txnViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        txnViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
    }

    public void createNewTxn(View view) {
        txnViewModel.insert(new Transaction());
        finish();
    }

    public void cancelCreatePerson(View view) {
        finish();
    }
}

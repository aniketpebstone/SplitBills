package org.aniket.splitbills.Screens;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.aniket.splitbills.R;
import org.aniket.splitbills.adapter.*;
import org.aniket.splitbills.model.*;

import java.util.ArrayList;
import java.util.List;

public class AddTransaction extends AppCompatActivity {

    private TransactionViewModel txnViewModel;
    private PersonViewModel personViewModel;
    Spinner spinner =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        txnViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        personViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        spinner = (Spinner) findViewById(R.id.sp_personName);

        personViewModel.getAllPersonsBySessionId().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable final List<Person> persons) {
                List<String> personNameList = new ArrayList<String>();
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddTransaction.this, android.R.layout.simple_spinner_item, personNameList);
                if(persons==null || persons.isEmpty())
                {
                    personNameList.add("-- Select Person --");
                }
                else
                {
                    for(Person person:persons)
                    {
                        personNameList.add(person.getName());
                    }

                }
                spinner.setAdapter(dataAdapter);
            }
        });

    }

    public void createNewTxn(View view) {
//        txnViewModel.insert(new Transaction());
//        finish();
        if(spinner.getSelectedItem().equals("-- Select Person --"))
            System.out.println("Please add the Person first!");
        else
            System.out.println("Transaction for "+spinner.getSelectedItem()+" added successfully!");
    }

    public void cancelCreatePerson(View view) {
        finish();
    }
}

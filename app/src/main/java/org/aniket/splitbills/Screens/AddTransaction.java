package org.aniket.splitbills.Screens;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.aniket.splitbills.R;
import org.aniket.splitbills.adapter.*;
import org.aniket.splitbills.model.*;

import java.util.ArrayList;
import java.util.List;

public class AddTransaction extends AppCompatActivity {

    private TransactionViewModel txnViewModel;
    private PersonViewModel personViewModel;
    Spinner spinner = null;
    boolean splittedEqually=true;
    RecyclerView recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        txnViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        personViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        spinner = (Spinner) findViewById(R.id.sp_personName);
        recyclerView = (RecyclerView) findViewById(R.id.rv_allPersons_in_txns);
        //Radio Group
        RadioButton radioButton = findViewById(R.id.rd_equally);
        radioButton.setChecked(true);


        personViewModel.getAllPersonsBySessionId().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable final List<Person> persons) {
                List<String> personNameList = new ArrayList<String>();
                //For spinner
                if (persons == null || persons.isEmpty()) {
                    personNameList.add("-- Select Person --");
                } else {
                    for (Person person : persons) {
                        personNameList.add(person.getName());
                    }

                }
                SpinnerAdapter<Person> spinnerAdapter=new SpinnerAdapter<>(AddTransaction.this,R.id.sp_personName,R.id.tv_name,persons);
//                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddTransaction.this, android.R.layout.simple_spinner_item, personNameList);
                spinner.setAdapter(spinnerAdapter);

                //For recycle view
                recyclerView.setVisibility(View.GONE);
                PersonListInTxnAdapter adapter = new PersonListInTxnAdapter(AddTransaction.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(AddTransaction.this));
                recyclerView.setAdapter(adapter);
                adapter.setPersons(persons);


            }
        });

    }

    public void onSplitEquallySelected(View view) {

        System.out.println("Amount is splitted equally!");
        recyclerView.setVisibility(View.GONE);
        splittedEqually=true;

    }

    public void onSplitAmongstSelected(View view) {

        System.out.println("Amount is not splitted equally!");
        recyclerView.setVisibility(View.VISIBLE);
        splittedEqually=false;
    }

    public void createNewTxn(View view) {
        String amountSpendTxt=((TextView)findViewById(R.id.amtSpend)).getText().toString();
        String purpose=((TextView)findViewById(R.id.purpose)).getText().toString();
        Float amountSpend=Float.parseFloat(amountSpendTxt);
        if (spinner.getSelectedItem().equals("-- Select Person --"))
        {
            System.out.println("Please add the Person first!");
        }
        else
        {

            Person person=(Person)spinner.getSelectedItem();
            System.out.println(person.getName()+" spend "+amountSpend+" on "+purpose+" which "+ (splittedEqually?"Splitted Equally":"Splitted among ")+" "+recyclerView.getTag());
        }
    }

    public void cancelCreatePerson(View view) {
        finish();
    }

}

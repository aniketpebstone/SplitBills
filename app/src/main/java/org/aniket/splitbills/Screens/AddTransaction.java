package org.aniket.splitbills.Screens;

import android.content.Intent;
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
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddTransaction extends AppCompatActivity {

    private TransactionViewModel txnViewModel;
    private SpliltViewModel spliltViewModel;
    private PersonViewModel personViewModel;
    private SplitViewModel splitViewModel;
    Spinner spinner = null;
    boolean splittedEqually = true;
    RecyclerView recyclerView = null;
    PersonListInTxnAdapter adapter = null;
    List<Person> personList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        txnViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        spliltViewModel = ViewModelProviders.of(this).get(SpliltViewModel.class);
        personViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        splitViewModel = ViewModelProviders.of(this).get(SplitViewModel.class);
        spinner = (Spinner) findViewById(R.id.sp_personName);
        recyclerView = (RecyclerView) findViewById(R.id.rv_allPersons_in_txns);

        //Radio Group
        RadioButton radioButton = findViewById(R.id.rd_equally);
        radioButton.setChecked(true);


        personViewModel.getAllPersonsBySessionId().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable final List<Person> persons) {

                personList = persons;
                AddTransaction.this.personList = persons;

                SpinnerAdapter<Person> spinnerAdapter = new SpinnerAdapter<>(AddTransaction.this, R.id.sp_personName, R.id.tv_name, persons);
                spinner.setAdapter(spinnerAdapter);

                //For recycle view
                recyclerView.setVisibility(View.GONE);
                adapter = new PersonListInTxnAdapter(AddTransaction.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(AddTransaction.this));
                recyclerView.setAdapter(adapter);
                adapter.setPersons(persons);


            }
        });

    }

    public void onSplitEquallySelected(View view) {

        System.out.println("Amount is splitted equally!");
        recyclerView.setVisibility(View.GONE);
        splittedEqually = true;

    }

    public void onSplitAmongstSelected(View view) {

        System.out.println("Amount is not splitted equally!");
        recyclerView.setVisibility(View.VISIBLE);
        splittedEqually = false;
    }

    public void createNewTxn(View view) {
        String amountSpendTxt = ((TextView) findViewById(R.id.amtSpend)).getText().toString();
        String purpose = ((TextView) findViewById(R.id.purpose)).getText().toString();
        Float amountSpend = Float.parseFloat(amountSpendTxt);
        if (spinner.getSelectedItem().equals("-- Select Person --")) {
            System.out.println("Please add the Person first!");
        }
        else
            {
            if (!splittedEqually && (adapter == null || adapter.getPersonsIds().isEmpty())) {

                System.out.println("Please select the persons which are involved in the current expense.");
            } else {
                Person person = (Person) spinner.getSelectedItem();
                long now=new Date().getTime();
                try {
                    Transaction txn = new Transaction(ActiveSession.sessionId, person.getId(), purpose, amountSpend, now);
                    System.out.println("Transaction:" + txn);
                    long txnId = txnViewModel.insert(txn);
                    if (splittedEqually)
                    {
                        int noOfPersons = personList.size();
                        float perShare = amountSpend / noOfPersons;
                        for (Person p : personList) {
                            Split split = new Split(ActiveSession.sessionId, (int) txnId, person.getId(), p.getId(), perShare);
                            spliltViewModel.insert(split);
                            System.out.println("Splits:" + split);
                        }
                        System.out.println(person.getName() + " spend " + amountSpend + " on " + purpose + " which  Splitted Equally");
                    }
                    else
                    {
                        int noOfPersons =adapter.getPersonsIds().size();
                        float perShare = amountSpend / noOfPersons;
                        System.out.println(person.getName() + " spend " + amountSpend + " on " + purpose + " which " + "Splitted among " + " " + adapter.getPersonsIds());
                        for (int pId : adapter.getPersonsIds())
                        {
                            Split split = new Split(ActiveSession.sessionId, (int) txnId, person.getId(), pId, perShare );
                            spliltViewModel.insert(split);
                            System.out.println("Splits:" + split);
                        }

                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }
    }

    public void cancelCreatePerson(View view) {
        finish();
    }

}

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

import org.aniket.splitbills.adapter.*;
import org.aniket.splitbills.model.*;
import org.aniket.splitbills.model.*;
import org.aniket.splitbills.R;

import java.util.List;

public class AllPersons extends AppCompatActivity {

    private PersonViewModel mPersonViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_persons);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllPersons.this, AddPerson.class);
                startActivity(intent);
            }
        });


        mPersonViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        mPersonViewModel.getAllPersonsBySessionId().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable final List<Person> persons) {

                if(persons==null || persons.isEmpty())
                {
                    RecyclerView recyclerView = findViewById(R.id.rv_allPersons);
                    recyclerView.setVisibility(View.GONE);
                }
                else
                {
                    // Update the cached copy of the words in the adapter.
                    TextView textView=findViewById(R.id.tv_emptyText2);
                    textView.setVisibility(View.GONE);
                    RecyclerView recyclerView = findViewById(R.id.rv_allPersons);
                    recyclerView.setVisibility(View.VISIBLE);
                    PersonListAdapter adapter = new PersonListAdapter(AllPersons.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(AllPersons.this));
                    recyclerView.setAdapter(adapter);
                    adapter.setPersons(persons);
                }
            }
        });
    }

    public void onPersonItemClicked(View view) {
        LinearLayout linearLayout= (LinearLayout) view.getParent();
        int id=Integer.parseInt(linearLayout.getTag().toString());
        System.out.println("======================================== ID: "+id);
        Intent intent = new Intent(this, EditPerson.class);
        intent.putExtra("PERSON_ID",id);
        startActivity(intent);

    }

}

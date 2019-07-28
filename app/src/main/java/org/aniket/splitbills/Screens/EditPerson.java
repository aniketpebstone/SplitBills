package org.aniket.splitbills.Screens;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.aniket.splitbills.R;
import org.aniket.splitbills.adapter.PersonListAdapter;
import org.aniket.splitbills.dao.PersonDao;
import org.aniket.splitbills.dao.SplitDao;
import org.aniket.splitbills.model.ActiveSession;
import org.aniket.splitbills.model.Person;
import org.aniket.splitbills.model.PersonViewModel;

import java.util.List;

public class EditPerson extends AppCompatActivity {
    private PersonViewModel mPersonViewModel;
    int personId=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);
        personId = getIntent().getIntExtra("PERSON_ID",-1);


        mPersonViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        mPersonViewModel.getPersonsByPersonId(personId).observe(this, new Observer<Person>() {
            @Override
            public void onChanged(@Nullable final Person person) {
                if(person!=null)
                {
                    EditText editText=findViewById(R.id.edt_editCreatePersonName);
                    editText.setText(person.getName());
                }

            }
        });
    }
    public void editPerson(View view) {
        TextView textView=findViewById(R.id.edt_editCreatePersonName);
        String personName=textView.getText().toString();
        mPersonViewModel.update(new Person(personId,personName, ActiveSession.sessionId));
        finish();
    }

    public void deletePerson(View view) {
        mPersonViewModel.delete(new Person(personId,null, ActiveSession.sessionId));
        finish();
    }

    public void cancelUpdatePerson(View view) {
        finish();
    }

}

package org.aniket.splitbills.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.aniket.splitbills.R;
import org.aniket.splitbills.model.*;

public class AddPerson extends AppCompatActivity {

    private PersonViewModel personViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        personViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
    }

    public void createNewPerson(View view) {
        TextView textView=findViewById(R.id.edt_createPersonName);
        String personName=textView.getText().toString();
        personViewModel.insert(new Person(personName,ActiveSession.sessionId));
        finish();
    }

    public void cancelCreatePerson(View view) {
        finish();
    }
}

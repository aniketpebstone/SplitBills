package org.aniket.splitbills.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.aniket.splitbills.R;
import org.aniket.splitbills.model.Session;
import org.aniket.splitbills.model.SessionViewModel;

public class CreateSession extends AppCompatActivity {

    private SessionViewModel sessionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);
        sessionViewModel = ViewModelProviders.of(this).get(SessionViewModel.class);
    }

    public void createNewSession(View view) {
        TextView textView=findViewById(R.id.edt_createSessionName);
        String sessionName=textView.getText().toString();
        sessionViewModel.insert(new Session(sessionName));
        finish();
    }

    public void cancelCreateSession(View view) {
        finish();
    }
}

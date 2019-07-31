package org.aniket.splitbills.Screens;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.aniket.splitbills.R;
import org.aniket.splitbills.SplitBillsApp;
import org.aniket.splitbills.model.ActiveSession;
import org.aniket.splitbills.model.Person;
import org.aniket.splitbills.model.PersonViewModel;
import org.aniket.splitbills.model.Session;
import org.aniket.splitbills.model.SessionViewModel;

public class EditSession extends AppCompatActivity {
    private SessionViewModel mSessionnViewModel;
    int sessionId=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_session);
        sessionId = getIntent().getIntExtra("SESSION_ID",-1);

        mSessionnViewModel = ViewModelProviders.of(this).get(SessionViewModel.class);
        mSessionnViewModel.getSessionById(sessionId).observe(this, new Observer<Session>() {
            @Override
            public void onChanged(@Nullable final Session session) {
                if(session!=null)
                {
                    EditText editText=findViewById(R.id.edt_editSessionName);
                    editText.setText(session.getName());
                }

            }
        });
    }

    public void editSession(View view) {
        TextView textView=findViewById(R.id.edt_editSessionName);
        String sessionName=textView.getText().toString();
        System.out.println("Edit Session Name:"+sessionName);
        mSessionnViewModel.update(new Session(ActiveSession.sessionId,sessionName));
        finish();
    }

    public void cancelUpdateSession(View view) {
        finish();
    }

}

package org.aniket.splitbills.Screens;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.aniket.splitbills.R;
import org.aniket.splitbills.adapter.SessionListAdapter;
import org.aniket.splitbills.model.ActiveSession;
import org.aniket.splitbills.model.Session;
import org.aniket.splitbills.model.SessionViewModel;

import java.util.List;

public class AllSessions extends AppCompatActivity {

    private SessionViewModel mSessionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_sessions);

        mSessionViewModel = ViewModelProviders.of(this).get(SessionViewModel.class);
        mSessionViewModel.getAllSessions().observe(this, new Observer<List<Session>>() {
            @Override
            public void onChanged(@Nullable final List<Session> sessions) {

                if(sessions==null || sessions.isEmpty())
                {
                    RecyclerView recyclerView = findViewById(R.id.rv_allSessions);
                    recyclerView.setVisibility(View.GONE);
                }
                else
                {
                    // Update the cached copy of the words in the adapter.
                    TextView textView=findViewById(R.id.tv_emptyText);
                    textView.setVisibility(View.GONE);
                    RecyclerView recyclerView = findViewById(R.id.rv_allSessions);
                    recyclerView.setVisibility(View.VISIBLE);
                    SessionListAdapter adapter = new SessionListAdapter(AllSessions.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(AllSessions.this));
                    recyclerView.setAdapter(adapter);
                    adapter.setSessions(sessions);
                }
            }
        });


    }

    public void onSessionItemClicked(View view) {
        LinearLayout linearLayout= (LinearLayout) view.getParent();
        int id=Integer.parseInt(linearLayout.getTag().toString());
        System.out.println("======================================== ID: "+id);
        ActiveSession.sessionId=id;
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }

}

package org.aniket.splitbills.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.aniket.splitbills.R;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }

    public void goToAllPerson(View view) {
        Intent intent = new Intent(this, AllPersons.class);
        startActivity(intent);
    }
}

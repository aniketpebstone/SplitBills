package org.aniket.splitbills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToCreateSessionScreen(View view) {

        Intent intent = new Intent(this, CreateSession.class);
        startActivity(intent);

    }

    public void goToLoadSessionScreen(View view) {

        Intent intent = new Intent(this, CreateSession.class);
        startActivity(intent);

    }
    public void goToDeleteSessionScreen(View view) {

        Intent intent = new Intent(this, CreateSession.class);
        startActivity(intent);

    }
}

package com.example.ReciPleaseLogin.ui.Menu;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

import android.os.Bundle;
import android.widget.EditText;


import com.example.ReciPleaseLogin.R;


public class MenuActivity extends AppCompatActivity {

    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final Button Messages = findViewById(R.id.messagesButton);
        final Button post = findViewById(R.id.postButton);
        final Button levels = findViewById(R.id.levelsButton);
        final Button search = findViewById(R.id.searchButton);
    }

}

package com.example.ReciPleaseLogin.ui.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;


import android.os.Bundle;
import android.widget.ImageView;


import com.example.ReciPleaseLogin.R;

public class MenuActivity extends AppCompatActivity {

    ImageView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.menu_toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar
        final ImageView b1 = findViewById(R.id.imageView1);
        final ImageView b2 = findViewById(R.id.imageView2);
        final ImageView b3 = findViewById(R.id.imageView3);
        final ImageView b4 = findViewById(R.id.imageView4);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.toolbar_levels:
                msg = "Levels";
                break;
            case R.id.toolbar_search:
                msg = "Search";
                break;
            case R.id.toolbar_messages:
                msg = "Messages";
                break;
            case R.id.toolbar_post:
                msg = "Post";
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
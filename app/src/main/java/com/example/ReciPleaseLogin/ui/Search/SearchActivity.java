package com.example.ReciPleaseLogin.ui.Search;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.ui.Menu.MenuActivity;

public class SearchActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Upper part of menu
       /* Toolbar toolbar = findViewById(R.id.menu_toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar*/
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
                Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
                startActivity(intent);
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

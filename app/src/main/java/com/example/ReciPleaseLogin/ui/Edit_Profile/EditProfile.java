package com.example.ReciPleaseLogin.ui.Edit_Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.ui.Levels.LevelsActivity;
import com.example.ReciPleaseLogin.ui.Menu.MenuActivity;
import com.example.ReciPleaseLogin.ui.Messages.MessagesActivity;
import com.example.ReciPleaseLogin.ui.Post.PostActivity;
import com.example.ReciPleaseLogin.ui.Profile.ProfileActivity;
import com.example.ReciPleaseLogin.ui.Search.SearchActivity;

public class EditProfile extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Upper part of menu
        Toolbar toolbar = findViewById(R.id.menu_toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.toolbar_home:
                intent = new Intent(EditProfile.this, MenuActivity.class);
                startActivity(intent);
                return true;

            case R.id.toolbar_levels:
                intent = new Intent(EditProfile.this, LevelsActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_search:
                intent = new Intent(EditProfile.this, SearchActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_messages:
                intent = new Intent(EditProfile.this, MessagesActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_post:
                intent = new Intent(EditProfile.this, PostActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_profile:
                intent = new Intent(EditProfile.this, ProfileActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_edit_profile:
                intent = new Intent(EditProfile.this, EditProfile.class);
                this.startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}


package com.example.ReciPleaseLogin.ui.PremiumStatus;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.ui.Edit_Profile.EditProfile;
import com.example.ReciPleaseLogin.ui.Levels.LevelsActivity;
import com.example.ReciPleaseLogin.ui.Menu.MenuActivity;
import com.example.ReciPleaseLogin.ui.Messages.MessagesActivity;
import com.example.ReciPleaseLogin.ui.Post.PostActivity;
import com.example.ReciPleaseLogin.ui.Profile.ProfileActivity;
import com.example.ReciPleaseLogin.ui.Search.SearchActivity;

public class PremiumActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);

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
                intent = new Intent(PremiumActivity.this, MenuActivity.class);
                startActivity(intent);
                return true;

            case R.id.toolbar_levels:
                intent = new Intent(PremiumActivity.this, LevelsActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_search:
                intent = new Intent(PremiumActivity.this, SearchActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_messages:
                intent = new Intent(PremiumActivity.this, MessagesActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_post:
                intent = new Intent(PremiumActivity.this, PostActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_profile:
                intent = new Intent(PremiumActivity.this, ProfileActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_edit_profile:
                intent = new Intent(PremiumActivity.this, EditProfile.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_premium:
                intent = new Intent(PremiumActivity.this, PremiumActivity.class);
                this.startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

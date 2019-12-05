package com.example.ReciPleaseLogin.ui.Levels;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.data.DB;
import com.example.ReciPleaseLogin.ui.Edit_Profile.EditProfile;
import com.example.ReciPleaseLogin.ui.Menu.MenuActivity;
import com.example.ReciPleaseLogin.ui.Messages.MessagesActivity;
import com.example.ReciPleaseLogin.ui.Post.PostActivity;
import com.example.ReciPleaseLogin.ui.PremiumStatus.PremiumActivity;
import com.example.ReciPleaseLogin.ui.Profile.ProfileActivity;
import com.example.ReciPleaseLogin.ui.Search.SearchActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class LevelsActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        // Upper part of menu
        Toolbar toolbar = findViewById(R.id.menu_toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar
        level();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void level() {
        Object ReturnInsideOutside = new Object();
        DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference("Root").child("users").child(DB.mUser.getUid());
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map<String, Long> myUser = (Map<String, Long>) dataSnapshot.getValue();

                int level = myUser.get("num_likers").intValue();

                ProgressBar progress = findViewById(R.id.cookProgress);
                RatingBar rate = findViewById(R.id.cookStar);
                TextView title = findViewById(R.id.cook_level_text);
                if (level < 1000) {
                    progress.setMax(1000);
                    progress.setProgress(level);
                    title.setText("Cook");
                    rate.setRating(0);
                } else if (level >= 1000 && level < 10000) {
                    progress.setMax(10000);
                    progress.setProgress(level);
                    title.setText("Sous Chef");
                    rate.setRating(1);
                } else if (level >= 10000 && level < 100000) {
                    progress.setMax(100000);
                    progress.setProgress(level);
                    title.setText("Executive Chef");
                    rate.setRating(2);
                } else {
                    progress.setMax(1000000);
                    progress.setProgress(level);
                    rate.setRating(3);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.toolbar_home:
                intent = new Intent(LevelsActivity.this, MenuActivity.class);
                startActivity(intent);
                return true;

            case R.id.toolbar_levels:
                intent = new Intent(LevelsActivity.this, LevelsActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_search:
                intent = new Intent(LevelsActivity.this, SearchActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_messages:
                intent = new Intent(LevelsActivity.this, MessagesActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_post:
                intent = new Intent(LevelsActivity.this, PostActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_profile:
                intent = new Intent(LevelsActivity.this, ProfileActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_edit_profile:
                intent = new Intent(LevelsActivity.this, EditProfile.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_premium:
                intent = new Intent(LevelsActivity.this, PremiumActivity.class);
                this.startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}

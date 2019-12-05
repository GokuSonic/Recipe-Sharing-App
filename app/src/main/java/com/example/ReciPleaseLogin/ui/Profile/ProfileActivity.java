package com.example.ReciPleaseLogin.ui.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.data.DB;
import com.example.ReciPleaseLogin.data.ImageLoadTask;
import com.example.ReciPleaseLogin.data.Recipe;
import com.example.ReciPleaseLogin.data.UserProfile;
import com.example.ReciPleaseLogin.ui.Edit_Profile.EditProfile;
import com.example.ReciPleaseLogin.ui.Levels.LevelsActivity;
import com.example.ReciPleaseLogin.ui.Menu.MenuActivity;
import com.example.ReciPleaseLogin.ui.Messages.MessagesActivity;
import com.example.ReciPleaseLogin.ui.Post.PostActivity;
import com.example.ReciPleaseLogin.ui.PremiumStatus.PremiumActivity;
import com.example.ReciPleaseLogin.ui.Search.SearchActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class ProfileActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Upper part of menu
        Toolbar toolbar = findViewById(R.id.menu_toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar

        DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference("Root").child("users").child(DB.mUser.getUid());
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, UserProfile> entry = (Map<String, UserProfile>) dataSnapshot.getValue();

                Map singleUser = (Map) dataSnapshot.getValue();


                ImageView image = findViewById(R.id.profile_picture);
                new ImageLoadTask((String) singleUser.get("pic"), image).execute();

                TextView name = findViewById(R.id.NameTextBox);
                name.setText((String) singleUser.get("who_are_you"));

                TextView premium = findViewById(R.id.Premium_box);
                if (!((boolean) singleUser.get("premium"))) {
                    premium.setText("No Premium");
                } else {
                    premium.setText("Premium User");
                }

                RatingBar rate = findViewById(R.id.ratingStar);
                int level = ((Long) singleUser.get("num_likers")).intValue();
                if (level < 1000) {
                    rate.setRating(0);
                } else if (level >= 1000 && level < 10000) {
                    rate.setRating(1);
                } else if (level >= 10000 && level < 100000) {
                    rate.setRating(2);
                } else {
                    rate.setRating(3);
                }

                TextView about = findViewById(R.id.AboutMe);
                about.setText((String) singleUser.get("AboutMe"));

                TextView FB = findViewById(R.id.FBurl);
                FB.setText("FB:" + singleUser.get("FB"));

                TextView YT = findViewById(R.id.YTurl);
                YT.setText("YT:" + singleUser.get("YT"));

                TextView IG = findViewById(R.id.IGurl);
                IG.setText("IG: " + singleUser.get("IG"));

                TextView stats = findViewById(R.id.Likes_and_shares);
                stats.setText("Likes: " + singleUser.get("num_likers").toString()
                        + "\nFollowers: " + singleUser.get("num_followers").toString()
                        + "\nRecipes: " + singleUser.get("num_recipes").toString());

                final DatabaseReference dref = DB.getInstance().database.getReference().child("Root").child("Recipes").child("Public");
                dref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Recipe myRecipe = findMyRecipe((Map<String, Recipe>) dataSnapshot.getValue());

                        ImageView rec_image = findViewById(R.id.recipe_picture);
                        new ImageLoadTask(myRecipe.instruction_pics.get(0), rec_image).execute();

                        TextView rec_text = findViewById(R.id.recipe_text);
                        rec_text.setText("test");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

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
                intent = new Intent(ProfileActivity.this, MenuActivity.class);
                startActivity(intent);
                return true;

            case R.id.toolbar_levels:
                intent = new Intent(ProfileActivity.this, LevelsActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_search:
                intent = new Intent(ProfileActivity.this, SearchActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_messages:
                intent = new Intent(ProfileActivity.this, MessagesActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_post:
                intent = new Intent(ProfileActivity.this, PostActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_profile:
                intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_edit_profile:
                intent = new Intent(ProfileActivity.this, EditProfile.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_premium:
                intent = new Intent(ProfileActivity.this, PremiumActivity.class);
                this.startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Recipe findMyRecipe(Map<String, Recipe> recipes) {

        ArrayList<Recipe> recipeList = new ArrayList<>();

        //iterate through each recipe, ignoring their UID
        for (Map.Entry<String, Recipe> entry : recipes.entrySet()) {

            //Get recipe map
            Map singleRecipe = (Map) entry.getValue();
            //Get description field and append to list
            if (((String) singleRecipe.get("owner")).contentEquals(DB.mUser.getUid())) {
                return new Recipe(
                        (String) singleRecipe.get("owner"), (String) singleRecipe.get("posted")
                        , (String) singleRecipe.get("diet"), (String) singleRecipe.get("recipe_name")
                        , (String) singleRecipe.get("description"), ((ArrayList) (singleRecipe.get("instruction_pics"))));
            }

        }
        return null;
    }

}
package com.example.ReciPleaseLogin.ui.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.data.DB;
import com.example.ReciPleaseLogin.data.ImageLoadTask;
import com.example.ReciPleaseLogin.data.Recipe;
import com.example.ReciPleaseLogin.ui.Edit_Profile.EditProfile;
import com.example.ReciPleaseLogin.ui.Levels.LevelsActivity;
import com.example.ReciPleaseLogin.ui.Messages.MessagesActivity;
import com.example.ReciPleaseLogin.ui.Post.PostActivity;
import com.example.ReciPleaseLogin.ui.PremiumStatus.PremiumActivity;
import com.example.ReciPleaseLogin.ui.Profile.ProfileActivity;
import com.example.ReciPleaseLogin.ui.Search.SearchActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MenuActivity extends AppCompatActivity {

    private TextView test;

    public static class MenuFragementAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MenuFragementAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return com.example.ReciPleaseLogin.ui.Menu.FirstFragment.newInstance(0, "Page # 1");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return com.example.ReciPleaseLogin.ui.Menu.SecondFragment.newInstance(1, "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return com.example.ReciPleaseLogin.ui.Menu.ThirdFragment.newInstance(2, "Page # 3");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }


    ImageView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        // Upper part of menu
        Toolbar toolbar = findViewById(R.id.menu_toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar
        final ImageView b1 = findViewById(R.id.imageView1);
        final ImageView b2 = findViewById(R.id.imageView2);
        final ImageView b3 = findViewById(R.id.imageView3);
        final ImageView b4 = findViewById(R.id.imageView4);
      
    //    test();

        //Lower part of Menu
        ViewPager vpPager = findViewById(R.id.menu_viewpage);

        // Crashes
        MenuFragementAdapter adapterViewPager = new MenuFragementAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);


        DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);
        ViewPager viewPager = findViewById(R.id.menu_viewpage);


        FragmentPagerAdapter adapter = new MenuActivity.MenuFragementAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        dotsIndicator.setViewPager(viewPager);
        //get latest user recipes

        final List<Recipe> ReturnInsideOutside = new Vector<>();


        final DatabaseReference dref = DB.getInstance().database.getReference().child("Root").child("Recipes").child("Public");
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Recipe[] top4 = collecetionOfRecipes((Map<String, Recipe>) dataSnapshot.getValue());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t" + top4[0].description);
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t" + top4[1].description);
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t" + top4[2].description);
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t" + top4[3].description);

                TextView post = findViewById(R.id.textView1);
                ImageView image = findViewById(R.id.imageView1);

                TextView post2 = findViewById(R.id.textView2);
                ImageView image2 = findViewById(R.id.imageView2);

                TextView post3 = findViewById(R.id.textView3);
                ImageView image3 = findViewById(R.id.imageView3);

                TextView post4 = findViewById(R.id.textView4);
                ImageView image4 = findViewById(R.id.imageView4);

                //Update all the images
                new ImageLoadTask(top4[0].getInstruction_pics().get(0), image).execute();
                post.setText("Name: " + top4[0].recipe_name
                        + "\nDiet: " + top4[0].diet + "\nDescription: " + top4[0].description);

                new ImageLoadTask(top4[1].getInstruction_pics().get(0), image2).execute();
                post2.setText("Name: " + top4[1].recipe_name
                        + "\nDiet: " + top4[1].diet + "\nDescription: " + top4[1].description);

                new ImageLoadTask(top4[2].getInstruction_pics().get(0), image3).execute();
                post3.setText("Name: " + top4[2].recipe_name
                        + "\nDiet: " + top4[2].diet + "\nDescription: " + top4[2].description);

                new ImageLoadTask(top4[3].getInstruction_pics().get(0), image4).execute();
                post4.setText("Name: " + top4[3].recipe_name
                        + "\nDiet: " + top4[3].diet + "\nDescription: " + top4[3].description);

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
                intent = new Intent(MenuActivity.this, MenuActivity.class);
                startActivity(intent);
                return true;

            case R.id.toolbar_levels:
                intent = new Intent(MenuActivity.this, LevelsActivity.class);
                startActivity(intent);
                return true;

            case R.id.toolbar_search:
                intent = new Intent(MenuActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;

            case R.id.toolbar_messages:
                intent = new Intent(MenuActivity.this, MessagesActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_post:
                intent = new Intent(MenuActivity.this, PostActivity.class);
                startActivity(intent);
                return true;

            case R.id.toolbar_profile:
                intent = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(intent);
                return true;

            case R.id.toolbar_edit_profile:
                intent = new Intent(MenuActivity.this, EditProfile.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_premium:
                intent = new Intent(MenuActivity.this, PremiumActivity.class);
                this.startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Recipe[] collecetionOfRecipes(Map<String, Recipe> recipes) {

        ArrayList<Recipe> recipeList = new ArrayList<>();

        //iterate through each recipe, ignoring their UID
        for (Map.Entry<String, Recipe> entry : recipes.entrySet()) {

            //Get recipe map
            Map singleRecipe = (Map) entry.getValue();
            //Get description field and append to list

            Recipe temp = new Recipe(
                    (String) singleRecipe.get("owner"), (String) singleRecipe.get("posted")
                    , (String) singleRecipe.get("diet"), (String) singleRecipe.get("recipe_name")
                    , (String) singleRecipe.get("description"), ((ArrayList) (singleRecipe.get("instruction_pics"))));

            recipeList.add(temp);
        }

        Collections.sort(recipeList, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe1, Recipe recipe2) {

                return recipe1.posted.compareTo(recipe2.posted);
            }
        });

        Recipe[] MenuRecipes = new Recipe[4];
        MenuRecipes[0] = recipeList.get(0);
        MenuRecipes[1] = recipeList.get(1);
        MenuRecipes[2] = recipeList.get(2);
        MenuRecipes[3] = recipeList.get(3);
        System.out.println("\t\t!!!!!!!!!!!!!!!!!!!!!!!Works=============" + recipeList.toString());
        return MenuRecipes;
        }


}


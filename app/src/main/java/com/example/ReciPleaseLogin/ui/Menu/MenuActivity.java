package com.example.ReciPleaseLogin.ui.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;


import android.os.Bundle;
import android.widget.ImageView;


import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;

import com.example.ReciPleaseLogin.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;


public class MenuActivity extends AppCompatActivity {

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
                    return com.example.ReciPleaseLogin.ui.Menu.FirstFragment.newInstance(1, "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return com.example.ReciPleaseLogin.ui.Menu.SecondFragment.newInstance(2, "Page # 3");
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


        //Lower part of Menu

        ViewPager vpPager = (ViewPager) findViewById(R.id.menu_viewpage);

        /* Crashes
        MenuFragementAdapter adapterViewPager = new MenuFragementAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
*/

        DotsIndicator dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);
        ViewPager viewPager = (ViewPager) findViewById(R.id.menu_viewpage);

        /*  CRASHES
        FragmentPagerAdapter adapter = new MenuActivity.MenuFragementAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);
        */

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
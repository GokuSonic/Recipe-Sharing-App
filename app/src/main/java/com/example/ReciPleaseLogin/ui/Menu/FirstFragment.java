package com.example.ReciPleaseLogin.ui.Menu;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.data.DB;
import com.example.ReciPleaseLogin.data.UserProfile;
import com.example.ReciPleaseLogin.ui.IObjectListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirstFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    private AppCompatActivity appCompatActivity;

    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
    //public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");


        level();//}
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.activity_menu_fragment_1, container, false);
        return view;
    }




    public void level(){
        Object ReturnInsideOutside = new Object();
        DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference("Root").child("users").child(DB.mUser.getUid()).child("num_likers");//download eVeRyTHING under each user slot and make massive object
        DB.getInstance().pull(new IObjectListener() {
            @Override
            public void onRetrievalSuccess(Object InsideObject) {
                Object OutsideObject = InsideObject;
                Log.i("TEST-Success", "" + InsideObject.toString());
            }

            @Override
            public void onRetrievalFailure() {
                Log.i("TEST-INSIDE-FAIL", "F");
            }
        }, ReturnInsideOutside, dref, ReturnInsideOutside);
        //if (ReturnInsideOutside instanceof UserProfile) {
        Log.i("TEST-OUTSIDE", "" + (ReturnInsideOutside).toString());

        UserProfile user=new UserProfile();
        if (ReturnInsideOutside instanceof UserProfile) {
            Log.i("TEST-object", "" +   ReturnInsideOutside.toString());
        } else
        {}
        int level = user.num_likers;
        System.out.println("\n\n\n\t\t level " + level);
        ProgressBar progress = getView().findViewById(R.id.menu_fragment1_baker_progressbar);
        RatingBar rate = getView().findViewById(R.id.menu_fragment1_bakerStar);
        if (level < 1000) {
            progress.setMax(1000);
            progress.setProgress(level);
            rate.setRating(0);
        } else if (level >= 1000 && level < 10000) {
            progress.setMax(10000);
            progress.setProgress(level);
            rate.setRating(1);
        } else if (level >= 10000 && level < 100000) {
            progress.setMax(100000);
            progress.setProgress(level);
            rate.setRating(2);
        } else {
            progress.setMax(1000000);
            progress.setProgress(level);
            rate.setRating(3);
        }


    }
}
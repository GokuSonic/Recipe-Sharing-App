package com.example.ReciPleaseLogin.ui.Menu;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.data.DB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

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
        dref = FirebaseDatabase.getInstance().getReference("Root").child("users").child(DB.mUser.getUid());
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map<String, Long> myUser = (Map<String, Long>) dataSnapshot.getValue();

                int level = myUser.get("num_likers").intValue();

                ProgressBar progress = getView().findViewById(R.id.menu_fragment1_baker_progressbar);
                RatingBar rate = getView().findViewById(R.id.menu_fragment1_bakerStar);
                TextView title = getView().findViewById(R.id.menu_fragment1_cook_text);
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
}
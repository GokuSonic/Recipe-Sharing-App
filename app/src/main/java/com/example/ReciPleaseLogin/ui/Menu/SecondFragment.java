package com.example.ReciPleaseLogin.ui.Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.data.DB;
import com.example.ReciPleaseLogin.data.ImageLoadTask;
import com.example.ReciPleaseLogin.data.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class SecondFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static SecondFragment newInstance(int page, String title) {
        SecondFragment fragmentFirst = new SecondFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

        getMessage();


    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.activity_menu_fragment_2, container, false);
        return view;
    }

    public void getMessage() {
        Object ReturnInsideOutside = new Object();
        DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference("Root").child("Messages");
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Message currentMessage = collecetionOfMessages((Map<String, Message>) dataSnapshot.getValue());

                ImageView sender = getView().findViewById(R.id.menu_fragment2_user_messages_pic);
                new ImageLoadTask(currentMessage.picture, sender).execute();

                TextView message = getView().findViewById(R.id.menu_fragment2_user_message);
                message.setText("Date Sent: " + currentMessage.timestamp + "\n" + currentMessage.message);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private Message collecetionOfMessages(Map<String, Message> messages) {

        ArrayList<Message> messageList = new ArrayList<>();
        Message myMessage = new Message();

        //iterate through each recipe, ignoring their UID
        for (Map.Entry<String, Message> entry : messages.entrySet()) {

            //Get recipe map
            Map singleMessage = (Map) entry.getValue();

            //Get description field and append to list
            if ((singleMessage.get("To").toString()).contentEquals(DB.mUser.getUid())) {
                myMessage = new Message(singleMessage.get("Message").toString(), singleMessage.get("To").toString(),
                        singleMessage.get("sent").toString(), singleMessage.get("pic").toString());
                break;

            }

        }
        return myMessage;
    }
}
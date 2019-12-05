package com.example.ReciPleaseLogin.ui.Messages;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.data.DB;
import com.example.ReciPleaseLogin.data.ImageLoadTask;
import com.example.ReciPleaseLogin.data.Message;
import com.example.ReciPleaseLogin.ui.Edit_Profile.EditProfile;
import com.example.ReciPleaseLogin.ui.Levels.LevelsActivity;
import com.example.ReciPleaseLogin.ui.Menu.MenuActivity;
import com.example.ReciPleaseLogin.ui.Post.PostActivity;
import com.example.ReciPleaseLogin.ui.PremiumStatus.PremiumActivity;
import com.example.ReciPleaseLogin.ui.Profile.ProfileActivity;
import com.example.ReciPleaseLogin.ui.Search.SearchActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class MessagesActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        // Upper part of menu
        Toolbar toolbar = findViewById(R.id.menu_toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar

        DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference("Root").child("Messages");
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Message[] top4 = collecetionOfRecipes((Map<String, Message>) dataSnapshot.getValue());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t" + top4[0].toString());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t" + top4[1].toString());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t" + top4[2].toString());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t" + top4[3].toString());

                TextView post = findViewById(R.id.textView12);
                ImageView image = findViewById(R.id.imageView8);

                TextView post2 = findViewById(R.id.textView13);
                ImageView image2 = findViewById(R.id.imageView9);

                TextView post3 = findViewById(R.id.textView14);
                ImageView image3 = findViewById(R.id.imageView10);

                TextView post4 = findViewById(R.id.textView15);
                ImageView image4 = findViewById(R.id.imageView11);

                //Update all the images
                new ImageLoadTask(top4[0].picture, image).execute();
                post.setText("Date Sent: " + top4[0].timestamp + "\n" + top4[0].message);

                new ImageLoadTask(top4[1].picture, image2).execute();
                post2.setText("Date Sent: " + top4[1].timestamp + "\n" + top4[1].message);

                new ImageLoadTask(top4[2].picture, image3).execute();
                post3.setText("Date Sent: " + top4[2].timestamp + "\n" + top4[2].message);

                new ImageLoadTask(top4[3].picture, image4).execute();
                post4.setText("Date Sent: " + top4[3].timestamp + "\n" + top4[3].message);

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
                intent = new Intent(MessagesActivity.this, MenuActivity.class);
                startActivity(intent);
                return true;

            case R.id.toolbar_levels:
                intent = new Intent(MessagesActivity.this, LevelsActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_search:
                intent = new Intent(MessagesActivity.this, SearchActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_messages:
                intent = new Intent(MessagesActivity.this, MessagesActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_post:
                intent = new Intent(MessagesActivity.this, PostActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_profile:
                intent = new Intent(MessagesActivity.this, ProfileActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_edit_profile:
                intent = new Intent(MessagesActivity.this, EditProfile.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_premium:
                intent = new Intent(MessagesActivity.this, PremiumActivity.class);
                this.startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Message[] collecetionOfRecipes(Map<String, Message> messages) {

        ArrayList<Message> messageList = new ArrayList<>();


        for (Map.Entry<String, Message> entry : messages.entrySet()) {

            // only add the users messages
            Map singleMessage = (Map) entry.getValue();
            System.out.println("\t\t!!!!!!!!!!!!!!!!!!!!!!!messages=============" + messageList.toString());
            if ((singleMessage.get("To").toString()).contentEquals(DB.mUser.getUid())) {
                Message temp = new Message(
                        (String) singleMessage.get("Message"), (String) singleMessage.get("To")
                        , (String) singleMessage.get("sent"), (String) singleMessage.get("pic"));
                messageList.add(temp);
            }
        }


        Message[] messagesAct = new Message[4];
        messagesAct[0] = messageList.get(0);
        messagesAct[1] = messageList.get(1);
        messagesAct[2] = messageList.get(2);
        messagesAct[3] = messageList.get(3);
        System.out.println("\t\t!!!!!!!!!!!!!!!!!!!!!!!Works=============" + messageList.toString());
        return messagesAct;
    }


}

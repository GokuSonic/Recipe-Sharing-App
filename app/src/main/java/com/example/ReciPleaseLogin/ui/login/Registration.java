package com.example.ReciPleaseLogin.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.data.DB;
import com.example.ReciPleaseLogin.data.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class Registration extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    //account
    private String email, password;
    //profile
   // private String name, dname, occ, exp;
    //boolean over15;

    //connection to ui
    private Button bCreateUser;
    private CheckBox age;
    private TextView status;
    private EditText emailbox, passbox,namebox,dispbox,occbox,expbox;

    private UserProfile newProfile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        bCreateUser = findViewById(R.id.register);
        emailbox = findViewById(R.id.rEmail);
        passbox = findViewById(R.id.rPassword);
        namebox = findViewById(R.id.rrealName);
        dispbox = findViewById(R.id.rDisplayName);
        occbox = findViewById(R.id.rOcc);
        expbox = findViewById(R.id.rExperience);
        age =findViewById(R.id.rAge);

        bCreateUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Do something in response to button click
                if(validate_info()){
                    create_user();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        submit_profile(user);
                    }
                }
                else{
                    return;


                }
            }
        });
    }


    private void create_user() {

        //email
        //password

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                         //   Toast.makeText(Registration.this, "Logged In", Toast.LENGTH_SHORT).show();
                            //update user
                            DB.getInstance().mUser=mAuth.getCurrentUser();

                        } else {

                            //Toast.makeText(Registration.this, "Login Failure", Toast.LENGTH_SHORT).show();

                            // If sign in fails, display a message to the user.
//                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //                      Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //          Toast.LENGTH_SHORT).show();
                            //  LoginActivity.updateUI(null);
                        }

                        // ...
                    }
                });
    }


    private void submit_profile(FirebaseUser user) {
        Intent intent;


        newProfile.updateDB();

        Toast.makeText(Registration.this, "Account Submitted!", Toast.LENGTH_SHORT).show();

        intent = new Intent(Registration.this, LoginActivity.class);
        startActivity(intent);

/*
// Add a new document with a generated ID  //FIRESTORE???  TODO:cleanup/remove commented code
    db.collection("users").add(profile)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(Registration.this, "Upload Success", Toast.LENGTH_SHORT).show();

                    //         Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
        //            Log.w(TAG, "Error adding document", e);
                    Toast.makeText(Registration.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                }
            });
*/


    }


    private boolean validate_info() {
        boolean valid = true;


        if (emailbox.getText().length() == 0) {
            valid = false;
            emailbox.setError("Email is required");
        } else {
            email = emailbox.getText().toString();
        }

        if (passbox.getText().length() < 6) {
            //error
            valid = false;
            passbox.setError("Password is required and minimum length of 6");

        } else {
            password = passbox.getText().toString();
        }

        if (namebox.getText().length() == 0) {
            valid = false;
            namebox.setError("Name is required");
        } else {
            newProfile.who_are_you = namebox.getText().toString();

        }


        if (dispbox.getText().length() == 0) {
            dispbox.setError("Preferred Display Name is required");
            valid = false;
        } else {
            newProfile.username = dispbox.getText().toString();
        }


        if (occbox.getText().length() == 0) {
            occbox.setError("Please tell us what do you do?");
            valid = false;
        } else {
            newProfile.what_do_you_do = occbox.getText().toString();

        }


        if (expbox.getText().length() == 0) {
            expbox.setError("Please Enter your cooking experience");
            valid = false;
        } else {
            newProfile.cooking_experience = expbox.getText().toString();

        }

        if (!age.isChecked()) {
            age.setError("Come Back when you are at least 15 years old");
            valid = false;
        } else {
            newProfile.over15 = true;

        }

        return valid;
    }


}












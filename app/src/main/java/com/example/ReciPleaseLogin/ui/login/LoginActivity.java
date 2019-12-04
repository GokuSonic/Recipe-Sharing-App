package com.example.ReciPleaseLogin.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.data.DB;
import com.example.ReciPleaseLogin.data.Recipe;
import com.example.ReciPleaseLogin.data.Recipes;
import com.example.ReciPleaseLogin.ui.IObjectListener;
import com.example.ReciPleaseLogin.ui.Menu.MenuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;
import java.util.Vector;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button bLogin;
    private String email;
    private String password;
    private TextView status;
    private EditText emailbox, passbox;

    RelativeLayout splash1;
    ImageView logo;


    Handler handle = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            splash1.setVisibility(View.VISIBLE);
            logo = findViewById(R.id.logoImage);

            ConstraintLayout logoLayout = findViewById(R.id.imageConstraint);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(logoLayout);
            constraintSet.connect(R.id.logoImage, ConstraintSet.RIGHT, R.id.relativeParent, ConstraintSet.RIGHT, 0);
            constraintSet.connect(R.id.logoImage, ConstraintSet.TOP, R.id.relativeParent, ConstraintSet.TOP, 100);
            constraintSet.applyTo(logoLayout);

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);

        DB.getInstance();

        splash1 = findViewById(R.id.splash1);

        //what for s seconds
        handle.postDelayed(runnable, 2000);

        bLogin = findViewById(R.id.login);
        emailbox = findViewById(R.id.username);
        passbox = findViewById(R.id.Password);
        bLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Do something in response to button click
                onLogin(view);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance();
    }

    private void updateUI(FirebaseUser User) {

        if (User != null) {
            //login
            test();

            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
        } else {
            //?
        }
    }

    public void onLogin(View view) {


        if (!validateUser()) {
            return;
        } else { //collect user pass combo
            email = emailbox.getText().toString();
            password = passbox.getText().toString();
        }


        //autocomplete generated header structure, notify listeners of auth

        DB.getInstance().mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                final Context context = LoginActivity.this;

                if (!task.isSuccessful()) {
                    DB.getInstance().mAuth.getCurrentUser();
                    Toast.makeText(LoginActivity.this, "Login Failure", Toast.LENGTH_SHORT).show();
                    try {
                        throw task.getException();
                    } catch (Exception e) {

                        if (e instanceof FirebaseAuthInvalidUserException) {
                            Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            //register user

                            Recipes newR=new Recipes();
                            DB.getInstance().push(newR);
                            //register?
                            Intent intent =new Intent(LoginActivity.this, Registration.class);
                            startActivity(intent);


                        } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            //forgot password

                        } else {
                            //unhandled

                        }


                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                    updateUI(DB.getInstance().mAuth.getCurrentUser());
                    //startActivity(intent);


                }

            }
        });
    }


    //    }
    public boolean validateUser() {
        //default to true, if it makes past both conditions and is still true return true
        boolean valid = true;

        email = emailbox.getText().toString();
        if (email.isEmpty()) {
            emailbox.setError("Email is required");
            valid = false;
        } else {
            emailbox.setError(null);

        }

        password = passbox.getText().toString();

        if (password.isEmpty()) {
            passbox.setError("Password is required");
            valid = false;
        } else {
            passbox.setError(null);

        }
        return valid;
    }


    public void test() {

        /* need listener in on create in ui*/
        /* need interface class with function names that will be overridden and types if any to return*/
        /* need fetch method to call which then signals the listener in ui that data is ready/available */
        /* Because firebase/android! everything is outside of scope, no direct access available*/

        List<Recipe> ReturnInsideOutside =new Vector<>();
        DatabaseReference dref;

        dref =DB.getInstance().database.getReference("Root").child("Recipes").child("Public");; //download eVeRyTHING and make massive object

        DB.getInstance().pull(new IObjectListener() {
            @Override
            public void onRetrievalSuccess(Object InsideObject) {
                Object OutsideObject = InsideObject;
                Log.i("TEST", "" + InsideObject.toString());
            }
            @Override
            public void onRetrievalFailure() {
                Log.i("TEST-INSIDE", "F");
            }

        }, ReturnInsideOutside, dref,new Recipe());

        Log.i("TEST-OUTSIDE", "" + ReturnInsideOutside.toString());

        Object recipes = ReturnInsideOutside;
        recipes.toString();
        Log.i("TEST", "" + recipes.toString());

        //((Recipes) recipes);
        Log.i("TEST", "" + recipes.toString());

    }


}
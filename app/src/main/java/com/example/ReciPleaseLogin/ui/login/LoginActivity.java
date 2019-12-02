package com.example.ReciPleaseLogin.ui.login;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ReciPleaseLogin.IObjectListener;
import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.data.DB;
import com.example.ReciPleaseLogin.data.Recipe;
import com.example.ReciPleaseLogin.ui.IRecipeListener;
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
import com.google.firebase.firestore.DocumentReference;

import android.content.Context;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button bLogin;
    private String email;
    private String password;
    private TextView status;
    private EditText emailbox, passbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);

        DB.getInstance();
        bLogin = (Button) findViewById(R.id.login);
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
        mAuth.getInstance();
    }

    private void updateUI(FirebaseUser User) {

        if (User != null) {
            ; //login
            test();
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
        } else {
            ;//?
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
                            ;//register?
                            Intent intent = new Intent(LoginActivity.this, Registration.class);
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
/*
    String recipe_name = "steak";
    DB.getInstance().pullRecipe(new IRecipeListener(){
        @Override
        public void onRetrievalSuccess(Recipe recipe) {
            String theName = recipe.recipe_name;
            test.setText(theName);
            Log.i("TEST", "" + recipe.recipe_name);
        }
        @Override
        public void onRetrievalFailure() {
            Log.i("TEST", "F");
        }

    }, recipe_name);
    */
        Object OutsideObject =new Object();
        DatabaseReference dref;
        dref =DB.getInstance().database.getReference("Root");; //download eVeRyTHING and make massive object
        DB.getInstance().pull(new IObjectListener() {
            @Override
            public void onRetrievalSuccess(Object InsideObject) {
                Object OutsideObject = InsideObject;
                Log.i("TEST", "" + InsideObject.toString());
            }

            @Override
            public void onRetrievalFailure() {
                Log.i("TEST", "F");
            }

        }, OutsideObject, dref);


    }


}
/* autogenerated

package com.example.ReciPleaseLogin.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

// Patrick's additions
import android.content.Intent;
import android.content.Context;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.ui.Menu.MenuActivity;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        final Context context = this;

        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience

        Intent intent = new Intent(context, MenuActivity.class);
        startActivity(intent);


        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
*/
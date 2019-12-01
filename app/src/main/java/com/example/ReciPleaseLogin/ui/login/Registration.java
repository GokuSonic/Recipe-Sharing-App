package com.example.ReciPleaseLogin.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ReciPleaseLogin.R;

import com.example.ReciPleaseLogin.data.UserProfile;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Registration extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    //account
    private String email, password;
    //profile
    private String name, dname, occ,exp;
    boolean over15;
    //photo
    static int PReqCode = 1 ;
    static int REQUESCODE = 1 ;
    Uri pickedImgUri ;
    private String URL;

    //connection to ui
    private Button bCreateUser;
    private CheckBox age;
    private TextView status;
    private EditText emailbox, passbox,namebox,dispbox,occbox,expbox;
    private ImageView uPhoto;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        bCreateUser = (Button) findViewById(R.id.register);
        emailbox = findViewById(R.id.rEmail);
        passbox = findViewById(R.id.rPassword);
        namebox = findViewById(R.id.rrealName);
        dispbox = findViewById(R.id.rDisplayName);
        occbox = findViewById(R.id.rOcc);
        expbox = findViewById(R.id.rExperience);
        age =findViewById(R.id.rAge);
        uPhoto=findViewById(R.id.rPhoto);

        bCreateUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Do something in response to button click
                if(validate_info()){
                    create_user();
                    user = FirebaseAuth.getInstance().getCurrentUser();
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
                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "createUserWithEmail:success");
                       // FirebaseUser user = mAuth.getCurrentUser();

                       // create_profile(user);
                      //  LoginActivity.updateUI(user);

                    } else {
                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
  //                      Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                      //          Toast.LENGTH_SHORT).show();
                      //  LoginActivity.updateUI(null);

                        // account creation failed
                        showMessage("account creation failed" + task.getException().getMessage());
                    }

                    // ...
                }
            });
}


private void submit_profile(FirebaseUser user){
    Intent intent;
/*
    Map<String, Object> profile = new HashMap<>();

    profile.put("uid",uid);
    profile.put("name",name);
    profile.put("dispname",dname);
    profile.put("email",email);
    profile.put("experience",exp);
    profile.put("oldenough",over15);*/

 //UserDate(String username, String realname, String cook_exp, String do_what, String something, String picture,  boolean age, boolean prem) {
    UserProfile userdb =new UserProfile(); //initialize userdata object

     userdb.username=name;

     userdb.who_are_you=dname;
     userdb.cooking_experience=exp;
     userdb.what_do_you_do=occ;
     userdb.picture_link=URL;
     userdb.over15=over15;

     userdb.updateDB();
    Toast.makeText(Registration.this, "Account Created!", Toast.LENGTH_SHORT).show();
    intent = new Intent(Registration.this, LoginActivity.class);
    startActivity(intent);
}



private boolean validate_info() {
    boolean valid = true;

    if (uPhoto.getText().length() == 0) {
        valid = false;
        namebox.setError("Name is required");
    } else {
        name = namebox.getText().toString();

    }
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
        name = namebox.getText().toString();

    }


    if (dispbox.getText().length() == 0) {
        dispbox.setError("Preferred Display Name is required");
        valid = false;
    } else {
        dname = dispbox.getText().toString();
    }


    if (occbox.getText().length() == 0) {
        occbox.setError("Please tell us what do you do?");
        valid = false;
    } else {
        occ = occbox.getText().toString();

    }


    if (expbox.getText().length() == 0) {
        expbox.setError("Please Enter your cooking experience");
        valid = false;
    } else {
        exp = expbox.getText().toString();

    }

    if (!age.isChecked()) {
        age.setError("Come Back when you are at least 15 years old");
        valid = false;
    } else {
        over15 = true;

    }

    uPhoto = findViewById(R.id.rPhoto);

    uPhoto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (Build.VERSION.SDK_INT >= 22) {

                checkAndRequestForPermission();


            } else {
                openGallery();
            }
        }
    });
    return valid;
}
    // update user photo and name
    private void updateUserInfo(final String name, Uri pickedImgUri, final FirebaseUser currentUser) {

        // first we need to upload user photo to firebase storage and get url

        //StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        final imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // image uploaded succesfully
                // now we can get our image url

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        // uri contain user image url


                        UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();


                        currentUser.updateProfile(profleUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            // user info updated successfully
                                            showMessage("Register Complete");
                                            updateUI();
                                        }

                                    }
                                });

                    }
                });





            }
        });
    }
    private void updateUI() {

        Intent homeActivity = new Intent(getApplicationContext(),Home.class);
        startActivity(homeActivity);
        finish();


    }
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }
        private void openGallery() {
            //open gallery intent and wait for user to pick an image !

            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, REQUESCODE);
        }

        private void checkAndRequestForPermission() {


            if (ContextCompat.checkSelfPermission(Registration.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(Registration.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    Toast.makeText(Registration.this, "Please accept for required permission", Toast.LENGTH_SHORT).show();

                } else {
                    ActivityCompat.requestPermissions(Registration.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            PReqCode);
                }

            } else
                openGallery();

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {

                // the user has successfully picked an image
                // we need to save its reference to a Uri variable
                pickedImgUri = data.getData();
                uPhoto.setImageURI(pickedImgUri);


            }

        }


    }















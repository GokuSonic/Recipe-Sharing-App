package com.example.ReciPleaseLogin.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;



public class DB {

    static  private FirebaseAuth mAuth;
    static private FirebaseUser user;
    static private FirebaseFirestore db;


    //submit any object to db
    static public void push( Object object){

        mAuth= FirebaseAuth.getInstance();
        user=mAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        if (object instanceof UserProfile) {
            db.collection("users").document(user.getUid()).set(object);
        }
        else if (object instanceof Recipe) {
            db.collection("users").document(user.getUid()).collection("recipes").add(object);
        }
        else if (object instanceof Message){
            db.collection("users").document(user.getUid()).collection("messages").add(object);
        }

    }


    //not sure yet
    //currently does nothing, returns object it was given;
    static public Object pull(Object object){


        //fetch something

        return object;
    };



}

package com.example.ReciPleaseLogin.data;

import com.example.ReciPleaseLogin.data.Recipe;

public class Recipes {

    Recipe[] recipes;

    public void updateDB(){

        DB.push(this);


        //nolonger needed
        /*mAuth=FirebaseAuth.getInstance();
        user=mAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        //db.collection(user.getUid()).add()
        db.collection("users").document(user.getEmail()).set(this);*/

    }

    //fetch new data
    public void updateView(){
        DB.pull(this);
    }

}

package com.example.ReciPleaseLogin.data;

public class Messages {






    public void post(Message message){

    }

    //not implemented
    public void edit(Message message){

    }



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

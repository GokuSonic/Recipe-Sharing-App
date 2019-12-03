package com.example.ReciPleaseLogin.data;

import java.util.Date;

public class UserFeed {

    public  UserFeed(){}

        String[] following;
        Date[] last_followed;
        String[] followers;
        Messages mailbox;
        Date last_read;
        Recipes recipes ;
        Recipes recipes_liked;


        public String[] getFollowing(){
            return following;
        }
        public Date[] getLast_followed(){
                return last_followed;
        }
        public String[] getFollowers(){
            return followers;
        }
        public Messages getMailbox(){
            return mailbox;
        }
        public Date getLast_read(){
        return last_read;
        }
        public Recipes getRecipes(){
            return recipes;
        }
        public Recipes getRecipes_liked(){
            return recipes_liked;
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


}

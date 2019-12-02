package com.example.ReciPleaseLogin.data;

import android.util.Log;

import com.example.ReciPleaseLogin.ui.IObjectListener;
import com.example.ReciPleaseLogin.ui.IRecipeListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.FirebaseFirestore;

    //import firebase.RTD

//import java.util.Date;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
//import java.util.Vector;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class DB {

    static public FirebaseAuth mAuth;
    static public FirebaseUser mUser = mAuth.getInstance().getCurrentUser();

    static public FirebaseDatabase database;
    static DatabaseReference mRootRef;

    //singleton
    private static DB soloDB = null;

    public static DB getInstance() {

        if (soloDB == null)
            soloDB = new DB();
        return soloDB;
    }

    private DB() {

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        mRootRef = database.getReference("Root");
    }

//}

    static public void pushWhoAreYou(String who_are_you) //Real Name
    {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Real Name").setValue(who_are_you);
        return;
    }

    static public void pushUserName(String displayName) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("UserName").setValue(displayName);
        return;
    }

    static public void pushCookingExp(String cooking_experience) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Cooking Experience").setValue(cooking_experience);
        return;
    }

    static public void pushWhatYouDo(String what_do_you_do) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("What do you do").setValue(what_do_you_do);
        return;
    }

    static public void pushSomethingInteresting(String something_int) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Something Interesting").setValue(something_int);
        return;
    }

//private void pushPicture save for later

    //Note for group and self: Basic write operations enjoy string/long over int. Either make int variables long or
//before the push function is called, convert int to string
    static public void pushNumFollowers(int num_followers) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Number of Followers").setValue(num_followers);
        return;
    }

    static public void pushNumLikers(int num_likers) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Number of Likers").setValue(num_likers);
        return;
    }

    //Note for group: If the user was able to make an account to use, they had to be over 15, what is this check for?
    static public void pushAgeCheck(boolean over15) {
        String check = "";

        if (over15) {
            check = "true";
        } else {
            check = "false";
        }
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("User over 15").setValue(check);
        return;
    }


    static public void pushPremiumStatus(boolean premium) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Premium").setValue(premium);
        return;
    }

    //Note for group and self: Basic write operations enjoy string/long over int. Either make int variables long or
//before the push function is called, convert int to string
    static public void pushNumberOfRecipes(String num_recipes) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Number of Recipes").setValue(num_recipes);
        return;
    }

    static public void pushRecipeName(String recipe_name) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).setValue(recipe_name);
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("recipename").setValue(recipe_name);
        return;
    }

    //Date needs to be converted to String/long: https://www.javatpoint.com/java-date-to-string
    static public void pushRecipeDate(String recipe_name, String posted) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("posted").setValue(posted);
        return;
    }

    //Check
    static public void pushIngredients(String recipe_name, List<String> ingredients) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("ingredients").setValue(ingredients);
        return;
    }

    static public void pushDescription(String recipe_name, String description) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("description").setValue(description);
        return;
    }

    static public void pushTags(String recipe_name, List<String> tag) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("tags").setValue(tag);
        return;
    }

    static public void pushInstructions(String recipe_name, List<String> instructions) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("instructions").setValue(instructions);
        return;
    }

    //Note for group and self: Basic write operations enjoy string/long over int. Either make int variables long or
//before the push function is called, convert int to string
    static public void pushLikersPerRecipe(String recipe_name, String recipe_likers) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("num_likers").setValue(recipe_likers);
        return;
    }

    static public void pushLikers(String recipe_name, List<String> likers) {
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).child("likers").setValue(likers);
        return;
    }

//--------------------------------------------------------------------------------------------------------
//Example of a pull

    public void pullRecipe(final IRecipeListener listener, String recipe_name) {
        //mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child("1").addListenerForSingleValueEvent(new ValueEventListener() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //HashMap<String, Recipe> map = (HashMap<String, Recipe>) dataSnapshot.getValue();
                //String recipe = dataSnapshot.getValue(String.class);
                //Log.i(TAG, "recipe is:" + recipe_name.recipe_name);
                Recipe recipe = dataSnapshot.getValue(Recipe.class);
                if (recipe != null) {

                    Log.i(TAG, "recipe_name is(inside):" + recipe.recipe_name);
                    //Log.i(TAG, "recipename is(inside):" + recipe.recipename);
                    Log.i(TAG, "description is:" + recipe.description);
                    Log.i(TAG, "ingredients is:" + recipe.ingredients);
                    //listener.onRetrievalSuccess(dataSnapshot.getValue(String.class).toString());
                    listener.onRetrievalSuccess(recipe);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //pretend we have stuff here
                Log.i(TAG, "DB F");
            }
        };
        Log.i(TAG, "recipe is(outside):" + recipe_name);
        mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).addValueEventListener(postListener);
        //});
    }
    public void pull(final IObjectListener listener,Object InsidetoOutside, DatabaseReference dref) {
        //mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child("1").addListenerForSingleValueEvent(new ValueEventListener() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //HashMap<String, Recipe> map = (HashMap<String, Recipe>) dataSnapshot.getValue();
                //String recipe = dataSnapshot.getValue(String.class);
                //Log.i(TAG, "recipe is:" + recipe_name.recipe_name);
                //Recipe recipe = dataSnapshot.getValue(Recipe.class);

                Object object =dataSnapshot.getValue(Object.class);
                if (object != null) {

                  /*  Log.i(TAG, "recipe_name is(inside):" + recipe.recipe_name);
                    //Log.i(TAG, "recipename is(inside):" + recipe.recipename);
                    Log.i(TAG, "description is:" + recipe.description);
                    Log.i(TAG, "ingredients is:" + recipe.ingredients);*/
                    //listener.onRetrievalSuccess(dataSnapshot.getValue(String.class).toString());
                    Log.i(TAG, "DB Failure"+ object.toString());
                    listener.onRetrievalSuccess(object);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //pretend we have stuff here
                Log.i(TAG, "DB Failure");
            }
        };
        Log.i(TAG, "dref:" +dref.toString());
        dref.addValueEventListener(postListener);
        //mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes").child(recipe_name).addValueEventListener(postListener);
        //});
    }



    static public void push(Object obj) {
    if (obj instanceof UserProfile){
        DatabaseReference users=mRootRef.child("users");
         users.setValue((UserProfile)obj);
    }
    else if (obj instanceof Message){
        DatabaseReference userMsg=mRootRef.child("users");
        if (obj!=null) {
            //check if message or comment, messages shouldnt have recipeUid

            if (((Message) obj).recipeUid==null) {
                //this is a message
                //post into logged in users messages sent

                ((Message) obj).sender = mUser.getUid();
                userMsg.child(mUser.getUid()).child("Sent").setValue((Message) obj);
                // Search for user uuid in UI
                // post in  "user" "user uid" "Messages" "Recieved"
                if(((Message)obj).recipientUid!=null)
                    userMsg.child(((Message) obj).recipientUid).child("recieved").setValue((Message) obj);
            }
            else {
                DatabaseReference userComment=mRootRef.child("users").child(((Message)obj).senderUid).child("Recipes").child(((Message) obj).recipeUid);
                userComment.setValue((Message)obj);

            }
        }

    }
        else if (obj instanceof Recipe) {
            DatabaseReference recipes = mRootRef.child(mAuth.getCurrentUser().getUid()).child("Recipes");
            ;
            //need to fetch current value increment by one and push/
            // mRootRef.child(mAuth.getCurrentUser().getUid()).child("Number of Recipes").setValue((long)num_recipes);   /// do we need or can we get recipes list size?
            DatabaseReference newrecipe = recipes.push();
            ((Recipe) obj).owner = mUser.getUid();
            newrecipe.setValue((Recipe) obj);
            newrecipe.child(newrecipe.getKey());

        /*;recipe.recipe_name);

        //Date needs to be converted to String/long: https://www.javatpoint.com/java-date-to-string
        newrecipe.child(newrecipe.getKey()).child("posted").setValue(recipe.posted);
        //Check
        newrecipe.child(newrecipe.getKey()).child("ingredients").setValue(recipe.ingredients);
        newrecipe.child(newrecipe.getKey()).child("description").setValue(recipe.description);
        newrecipe.child(newrecipe.getKey()).child("tags").setValue(recipe.tags);
        newrecipe.child(newrecipe.getKey()).child("instructions").setValue(recipe.instructions);
        //Note for group and self: Basic write operations enjoy string/long over int. Either make int variables long or
        //before the push function is called, convert int to string
        newrecipe.child(newrecipe.getKey()).child("num_likers").setValue((long) recipe.num_likers);
        newrecipe.child(newrecipe.getKey()).child("likers").setValue(recipe.likers);
        return;*/
        }
    }
}
package com.example.ReciPleaseLogin.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
//import firebase.RTD

import java.util.Date;
import java.util.List;
import java.util.Vector;



public class DB {

    static private FirebaseAuth mAuth;
    static private FirebaseUser mUser;

    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference mRootRef = database.getReference("Root");

//}

    static public void pushWhoAreYou(String who_are_you) //Real Name
    {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getInstance().getCurrentUser();
        mRootRef.child(mUser.getUid()).child("Real Name").setValue(who_are_you);
        return;
    }

    static public void pushUserName(String displayName)
    {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getInstance().getCurrentUser();
        mRootRef.child(mUser.getUid()).child("UserName").setValue(displayName);
        return;
    }

    static public void pushCookingExp(String cooking_experience)
    {
        mRootRef.child(mUser.getUid()).child("Cooking Experience").setValue(cooking_experience);
        return;
    }

    static public void pushWhatYouDo(String what_do_you_do)
    {
        mRootRef.child(mUser.getUid()).child("What do you do").setValue(what_do_you_do);
        return;
    }

    static public void pushSomethingInteresting(String something_int)
    {
        mRootRef.child(mUser.getUid()).child("Something Interesting").setValue(something_int);
        return;
    }

//private void pushPicture save for later

    //Note for group and self: Basic write operations enjoy string/long over int. Either make int variables long or
//before the push function is called, convert int to string
    static public void pushNumFollowers(int num_followers)
    {
        mRootRef.child(mUser.getUid()).child("Number of Followers").setValue(num_followers);
        return;
    }

    static public void pushNumLikers(int num_likers)
    {
        mRootRef.child(mUser.getUid()).child("Number of Likers").setValue(num_likers);
        return;
    }
    //Note for group: If the user was able to make an account to use, they had to be over 15, what is this check for?
    static public void pushAgeCheck(boolean over15)
    {
        String check = "";

        if(over15)
        {
            check = "true";
        }
        else
        {
            check = "false";
        }
        mRootRef.child(mUser.getUid()).child("User over 15").setValue(check);
        return;
    }

    static public void pushPremiumStatus(boolean premium)
    {
        mRootRef.child(mUser.getUid()).child("Premium").setValue(premium);
        return;
    }

    //Note for group and self: Basic write operations enjoy string/long over int. Either make int variables long or
//before the push function is called, convert int to string
    static public void pushNumberOfRecipes(String num_recipes)
    {
        mRootRef.child(mUser.getUid()).child("Number of Recipes").setValue(num_recipes);
        return;
    }

    static public void pushRecipeName(String recipe_name)
    {
        mRootRef.child(mUser.getUid()).child("Recipes").child("Recipe Name").setValue(recipe_name);
        return;
    }
    //Date needs to be converted to String/long: https://www.javatpoint.com/java-date-to-string
    static public void pushRecipeDate(String recipe_name, String posted)
    {
        mRootRef.child(mUser.getUid()).child("Recipes").child(recipe_name).child("Date Posted").setValue(posted);
        return;
    }
    //Check
    static public void pushIngredients(String recipe_name, List<String> ingredients)
    {
        mRootRef.child(mUser.getUid()).child("Recipes").child(recipe_name).child("Ingredients").setValue(ingredients);
        return;
    }

    static public void pushInstructions(String recipe_name, List<String> instructions)
    {
        mRootRef.child(mUser.getUid()).child("Recipes").child(recipe_name).child("Instructions").setValue(instructions);
        return;
    }
    //Note for group and self: Basic write operations enjoy string/long over int. Either make int variables long or
//before the push function is called, convert int to string
    static public void pushLikersPerRecipe(String recipe_name, String recipe_likers)
    {
        mRootRef.child(mUser.getUid()).child("Recipes").child(recipe_name).child("Number of Likes").setValue(recipe_likers);
        return;
    }

    static public void pushLikers(String recipe_name, List<String> likers)
    {
        mRootRef.child(mUser.getUid()).child("Recipes").child(recipe_name).child("Likers").setValue(likers);
        return;
    }

//--------------------------------------------------------------------------------------------------------
//Example of a pull
//
//
//
//
//
//
//
//
};
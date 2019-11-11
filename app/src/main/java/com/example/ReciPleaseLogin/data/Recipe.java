package com.example.ReciPleaseLogin.data;

import java.util.Date;

public class Recipe {
    String owner;
    String recipe_name;
    Date posted;
    String[] ingredients;
    String[] instructions;
    int num_ingredients;
    String[] tags;
    String[] likers;
    int num_likers;
    boolean premium;
    Messages comments;




    public Recipe(){}

    //public void Recipe(){};

    public String getOwner(){
        return owner;
    }

    public String getRecipe_name()
    {
        return recipe_name;
    }
    public Date getPosted()
    {
        return posted;
    }
    public String[] getIngredients()
    {
    return ingredients;
    }
    public String[] getInstructions()
    {
    return instructions;
    }
    public int getNum_ingredients()
    {
return num_ingredients;
    }
    public String[] getTags()
    {
return tags;
    }
    public String[] getLikers()
    {
return likers;
    }
    public int getNum_likers()
    {
return num_likers;
    }
    public boolean getPremium()
    {
return premium;
    }
    public Messages getComments()
    {
return comments;
    }

    /* local data only
    public void updateDB(){
        DB.push(this);
    }

    //fetch new data
    public void updateView(){
        DB.pull(this);
    }
*/


}

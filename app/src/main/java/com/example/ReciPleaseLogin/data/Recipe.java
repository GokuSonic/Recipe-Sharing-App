package com.example.ReciPleaseLogin.data;

import com.google.firebase.Timestamp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;

//import static com.example.ReciPleaseLogin.data.DB.pushRecipeName;

public class Recipe {
    String self;
    public String owner;
    public String recipe_name;
    //public String recipename;
    public String description;
    public Timestamp posted;
    public List<String> ingredients =new ArrayList<>();
    public List<String> instructions=new ArrayList<>();
    public List<String> instruction_pics=new ArrayList<>();
    public int num_ingredients;
    public List<String> tags=new ArrayList<>();
    public List<String> likers=new ArrayList<>();
    public int num_likers;
    public boolean premium;
    public Messages comments;
    public String prepTimeCookTime;
    public String diet;
    public String difficulty;



    public Recipe()
    {}

    public Recipe(String recipe_name, String description, List<String> ingredients)
    {
        this.recipe_name = recipe_name;
        this.description = description;
        this.ingredients = ingredients;
    }


    public String getOwner(){
        return owner;
    }

    public String getRecipe_name()
    {
        return recipe_name;
    }
    @Exclude
    public String getSelf(){return self;};
    public String getDescription() { return description; }
    public List<String> getInstruction_pics() { return instruction_pics; }

    public Timestamp getPosted()
    {
        return posted;
    }
    public List<String> getIngredients()
    {
    return ingredients;
    }
    public List<String> getInstructions()
    {
    return instructions;
    }
    public int getNum_ingredients()
    {
return num_ingredients;
    }
    public List<String> getTags()
    {
return tags;
    }
    public List<String> getLikers()
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

    public String getPrepTimeCookTime() {
        return prepTimeCookTime;
    }

    public String getDiet() {
        return diet;
    }

    public String getDifficulty() {
        return difficulty;
    }

    //public String getRecipename(){ return recipename;};

    public void updateDB(){
        DB.getInstance().push(this);

        /*
        DB.getInstance().pushRecipeName(recipe_name);
        DB.getInstance().pushIngredients(recipe_name, ingredients);
        DB.getInstance().pushDescription(recipe_name, description);
        DB.getInstance().pushInstructions(recipe_name, instructions);
        DB.getInstance().pushTags(recipe_name, tags);
*/
    }



}

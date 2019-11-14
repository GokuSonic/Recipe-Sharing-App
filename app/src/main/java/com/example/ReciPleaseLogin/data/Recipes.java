package com.example.ReciPleaseLogin.data;

import com.example.ReciPleaseLogin.data.Recipe;

import java.util.List;
import java.util.Vector;

public class Recipes {

   List<Recipe> recipes;


    public Recipes(){
        recipes=new Vector<Recipe>();
    }


    public List<Recipe> getRecipes(){
        return recipes;
    }

    public void updateDB(){

        DB.push(this);
    }

    //fetch new data
    public void updateView(){
        DB.pull(this);
    }

}

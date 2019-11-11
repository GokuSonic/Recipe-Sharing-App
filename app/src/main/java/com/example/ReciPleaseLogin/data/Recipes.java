package com.example.ReciPleaseLogin.data;

import com.example.ReciPleaseLogin.data.Recipe;

public class Recipes {

    Recipe[] recipes;


    public Recipes(){}


    public Recipe[] getRecipes(){
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

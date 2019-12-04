package com.example.ReciPleaseLogin.data;

import java.util.List;
import java.util.Vector;

public class Recipes {

    public List<String> key=new Vector<>();
   public List<Recipe> recipes=new Vector<Recipe>();;

    public Recipes(){
    }


    public List<Recipe> getRecipes(){
        return recipes;
    }

    public void updateDB(){
        DB.push(this);
    }
    }
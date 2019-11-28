package com.example.ReciPleaseLogin.ui;

import com.example.ReciPleaseLogin.data.Recipe;
import com.example.ReciPleaseLogin.data.RecipeExample;

public interface IRecipeListener {
    void onRetrievalSuccess(RecipeExample recipe);
    void onRetrievalFailure();
}

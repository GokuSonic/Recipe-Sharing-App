package com.example.ReciPleaseLogin.ui;

import com.example.ReciPleaseLogin.data.Recipe;
import com.example.ReciPleaseLogin.data.RecipeTwo;

public interface IRecipeListener {
    void onRetrievalSuccess(RecipeTwo recipe);
    void onRetrievalFailure();
}

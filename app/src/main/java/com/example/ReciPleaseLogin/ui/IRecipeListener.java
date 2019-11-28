package com.example.ReciPleaseLogin.ui;

import com.example.ReciPleaseLogin.data.Recipe;

public interface IRecipeListener {
    void onRetrievalSuccess(String recipe);
    void onRetrievalFailure();
}

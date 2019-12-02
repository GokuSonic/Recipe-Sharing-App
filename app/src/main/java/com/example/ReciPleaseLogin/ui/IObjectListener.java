package com.example.ReciPleaseLogin.ui;

import com.example.ReciPleaseLogin.data.Recipe;

public interface IObjectListener {
    void onRetrievalSuccess(Object object);
    void onRetrievalFailure();
}
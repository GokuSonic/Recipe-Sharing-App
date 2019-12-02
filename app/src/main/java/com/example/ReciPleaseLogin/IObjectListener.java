package com.example.ReciPleaseLogin;

import com.example.ReciPleaseLogin.data.Recipe;

public interface IObjectListener {
    void onRetrievalSuccess(Object object);
    void onRetrievalFailure();
}
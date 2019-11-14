package com.example.ReciPleaseLogin.data;

public class User {
    int instance=0;
    UserFeed feed;
    UserProfile profile;

private User user;
    private User(){}

public User GetInstance(){
    if (user==null)
        user=new User();
    return user;
    }



}

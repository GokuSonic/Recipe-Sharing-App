package com.example.ReciPleaseLogin.data;

import java.util.Date;

public class Message {
    public String recipeUid;
    public boolean premium;
    public String sender;
    public String recipient;
    public Date timestamp;
    public String message;


    //constructor
    public Message(){
    }

    public String getSender(){
        return sender;
    }
    public String getRecipient(){
        return recipient;
    }
    public Date getTimestamp(){
        return timestamp;
    }
    public String getMessage(){
        return message;
    }
    public String getRecipeUid(){
        return recipeUid;
    }
    public boolean getPremium(){
        return premium;
    }




/* local data only
    public void updateDB(){

        DB.push(this);

    }

    //fetch new data
    public void updateView(){
        DB.pull(this);
    }
*/


}

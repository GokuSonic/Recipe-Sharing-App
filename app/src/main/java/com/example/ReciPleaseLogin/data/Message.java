package com.example.ReciPleaseLogin.data;

import java.util.Date;

public class Message {
    public String recipeUid;
    public String parentUid; //this is to structure messages
    public String senderUid;
    public String recipientUid; //must be queried in UI before sending to db
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

    public String getParentUid(){return parentUid;}
    public String getSenderUid(){
        return senderUid;
    }
    public String getRecipient(){
        return recipient;
    }
    public String getRecipientUid(){
        return recipientUid;
    }
    public Date getTimestamp(){
        return timestamp;
    }
    public String getMessage(){
        return message;
    }
    public String getRecipeUid(){
        return recipeUid;
    } // this is Recipe post id
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

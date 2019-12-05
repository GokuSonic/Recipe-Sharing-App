package com.example.ReciPleaseLogin.data;

public class Message {
    public String recipeUid;
    public String parentUid; //this is to structure messages
    public String senderUid;
    public String recipientUid; //must be queried in UI before sending to db
    public boolean premium;
    public String sender;
    public String recipient;
    public String timestamp;
    public String message;
    public String picture;


    //constructor
    public Message(String mess, String toUID, String time, String pic) {
        recipeUid = toUID;
        message = mess;
        timestamp = time;
        picture = pic;
    }
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

    public String getTimestamp() {
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




    public void updateDB(){

        DB.push(this);

    }


}

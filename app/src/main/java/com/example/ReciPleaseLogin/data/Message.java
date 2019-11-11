package com.example.ReciPleaseLogin.data;

import java.util.Date;

public class Message {

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

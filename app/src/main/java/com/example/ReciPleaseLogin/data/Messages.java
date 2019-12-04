package com.example.ReciPleaseLogin.data;

import java.util.List;
import java.util.Vector;

public class Messages {


    List<Message> messages=new Vector<Message>();


    public Messages(){

    }

    public void post(Message message){

    }

    //not implemented
    public void edit(Message message){

    }


    public List<Message> getMessages(){
        return messages;
    }




    public void updateDB(){

        DB.push(this);

    }



}

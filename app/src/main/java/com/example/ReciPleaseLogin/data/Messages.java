package com.example.ReciPleaseLogin.data;

public class Messages {


    Message[] messages;



    public void post(Message message){

    }

    //not implemented
    public void edit(Message message){

    }


    public Message[] getMessages(){
        return messages;
    }




    public void updateDB(){

        DB.push(this);

    }

    //fetch new data
    public void updateView(){
        DB.pull(this);
    }

}

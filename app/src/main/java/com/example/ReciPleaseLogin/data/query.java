package com.example.ReciPleaseLogin.data;

import java.util.List;
import java.util.Vector;

public class query {
    String collection;
    int num_queries;
    List<String> type; /* < , <= , ==, > >=, array-contains, in, array-contains-any "*/
    List<String> searchfor;



    query(){
        type=new Vector<String>();
        searchfor=new Vector<String>();
    }
}

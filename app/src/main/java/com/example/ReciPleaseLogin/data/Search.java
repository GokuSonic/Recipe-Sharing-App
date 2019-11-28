package com.example.ReciPleaseLogin.data;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Vector;

public class Search {
    public int max;
    public CollectionReference ref;
    public FirebaseFirestore db;
    public Query getResults;
    public Object searchfor;



    public Search(Object obj, int page_size){
        db.getInstance();
        max=page_size;

//TODO: make sure to run database indexing on Recipe name, who_are_you, tags field in firebase Console

        if (searchfor instanceof UserProfile){

        }
    }
        public  void next(){

        }

        public void prev(){

        }
    }


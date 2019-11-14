package com.example.ReciPleaseLogin.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;



public class DB {

    static  private FirebaseAuth mAuth;
    static private FirebaseUser mUser;
    static private FirebaseFirestore db;
    static private User user;

    //submit any object to db
    static public void push( Object object){

        mAuth= FirebaseAuth.getInstance();
        mUser=mAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        if (object instanceof UserProfile) {
            db.collection("users").document(mUser.getUid()).set(object);
        }
        else if (object instanceof Recipe) {
            if (((Recipe) object).premium==true)
                db.collection("PremiumRecipes").add(object);
            else if (((Recipe) object).premium==false){
                db.collection("PublicRecipes").add(object);
            }
        }
        else if (object instanceof Message){

            //send to sender and recipient
            if (((Message) object).recipeUid ==null) {
                db.collection("users").document(mUser.getUid()).collection("messages").add(object);

                //fake recipient
                db.collection("users").document("recipient").collection("messages").add(object);



            }else{
                if (((Message) object).premium==true)
                    db.collection("PremiumRecipes").document(((Message) object).recipeUid).collection("comments").add(object);
                else
                    db.collection("PublicRecipes").document(((Message) object).recipeUid).collection("comments").add(object);

            }
        }

    }


    //not sure yet
    //currently does nothing, returns object it was given;
    static public Object pull(Object object){


        //fetch something

        return object;
    };




    static public  void query(query query ){
        String collection=query.collection;

        CollectionReference ref=db.collection(collection);

        /*(< , <= , ==, > >=, array-contains, in, array-contains-any "*/
        for (int i=0;i<query.num_queries;i++){
            if (query.type.get(i)=="<") {
                ref.whereLessThan(collection, query.searchfor.get(i));
            }
            else if (query.type.get(i)=="<=") {
                ref.whereLessThanOrEqualTo(collection, query.searchfor.get(i));
            }
            else if (query.type.get(i)=="==") {
                ref.whereEqualTo(collection, query.searchfor.get(i));
            }
            else if (query.type.get(i)==">") {
                ref.whereGreaterThan(collection, query.searchfor.get(i));
            }
            else if (query.type.get(i)==">=") {
                ref.whereGreaterThanOrEqualTo(collection, query.searchfor.get(i));
            }
            else if (query.type.get(i)=="array-contains") {
                ref.whereArrayContains(collection, query.searchfor.get(i));
            }
            //not found
            //else if (query.type[i]=="in") {
              //  ref.w(collection, query.searchfor[i]);
    //        }
  //          else if (query.type[i]=="array-contains-any") {
//                ref.whereEqualTo(collection, query.searchfor[i]);
      //      }
        }




    }


}

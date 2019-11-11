package com.example.ReciPleaseLogin.data;


        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.firestore.FirebaseFirestore;

public class UserData {

    public String username;

    public String who_are_you;
    public String cooking_experience;
    public String what_do_you_do;
    public String Something_interesting;
    public String picture_link;
    public int num_followers;
    public int num_likers;
    public int num_recipes;
    public boolean over15;
    public boolean premium;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    //default constructor that takes no objects, required for firestore
public UserData(){}

    public void UserDate(String a, String b, String c, String d, String e, String f,  boolean g, boolean h) {

        username = a;
        who_are_you = b;
        cooking_experience = c;
        what_do_you_do = d;
        Something_interesting = e;
        picture_link=f;
        over15 = g;
        premium = h;



        //initialize to zero
        num_followers = 0;
        num_likers = 0;
        num_recipes = 0;
    }


    private void sync_profile(){
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        db.collection("users").document(user.getUid()).set(this);

    }


    // getters for firestore
    public String getUsername(){
        return username;
    }
    public String getWho_are_you(){
        return who_are_you;
    }
    public String getWhat_do_you_do() {
        return what_do_you_do;
    }
    public String getCooking_experience(){
        return cooking_experience;
    }
    public String getSomething_interesting() {
        return Something_interesting;
    }
    public String getPicture_link(){
        return picture_link;
    }
    public int getNum_followers(){
        return num_followers;
    }
    public int getNum_recipes(){
        return num_recipes;
    }
    public int getNum_likers(){
        return num_likers;
    }
    public boolean getOver15() {
        return over15;
    }
    public boolean isPremium() {
        return premium;
    }
}

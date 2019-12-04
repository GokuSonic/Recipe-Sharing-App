package com.example.ReciPleaseLogin.data;


public class UserProfile {

    public String name;
    public String username;
    public String who_are_you;
    public String cooking_experience;
    public String what_do_you_do;
    public String something_int;
    public String picture_link;
    public int num_followers;
    public int num_likers;
    public int num_recipes;
    public boolean over15;
    public boolean premium;
    public String uid;

    //default constructor that takes no objects, required for firestore
public UserProfile(){}

    public UserProfile(String username, String realname, String cook_exp, String do_what, String something, String picture, boolean age, boolean prem, String UID) {

        this.username = username;
        who_are_you = realname;
        cooking_experience = cook_exp;
        what_do_you_do = do_what;
        something_int = something;
        picture_link=picture;
        over15 = age;
        premium = prem;
        uid = UID;

        //initialize to zero
        num_followers = 0;
        num_likers = 0;
        num_recipes = 0;
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
    public String getCooking_experience() {
        return cooking_experience;
    }
    //firebase complains about this one and crashes
    public String getSomething_int() {
        return something_int;
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

    public String getUID() {
        return uid;
    }

    public void updateDB(){

        DB.push(this);
       /*
        //update single values
        DB.getInstance().pushWhoAreYou(who_are_you);
        DB.pushUserName(username);
        DB.pushCookingExp(cooking_experience);
        DB.pushWhatYouDo(what_do_you_do);
        DB.pushSomethingInteresting(something_int);
        DB.pushNumFollowers(num_followers);
        DB.pushAgeCheck(over15);
*/

        //nolonger needed
        /*mAuth=FirebaseAuth.getInstance();
        user=mAuth.getInstance().getCurrentUser();
        //db.collection(user.getUid()).add()
        db.collection("users").document(user.getEmail()).set(this);*/

    }

}

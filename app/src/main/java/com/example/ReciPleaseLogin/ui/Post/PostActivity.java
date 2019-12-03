package com.example.ReciPleaseLogin.ui.Post;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ReciPleaseLogin.R;
import com.example.ReciPleaseLogin.data.DB;
import com.example.ReciPleaseLogin.data.Recipe;
import com.example.ReciPleaseLogin.ui.Edit_Profile.EditProfile;
import com.example.ReciPleaseLogin.ui.Levels.LevelsActivity;
import com.example.ReciPleaseLogin.ui.Menu.MenuActivity;
import com.example.ReciPleaseLogin.ui.Messages.MessagesActivity;
import com.example.ReciPleaseLogin.ui.Profile.ProfileActivity;
import com.example.ReciPleaseLogin.ui.Search.SearchActivity;

public class PostActivity extends AppCompatActivity {

    private Button bPostRecipe, bPostNext, bAddIngredient ,bAddTags;
    private EditText Receipt_Name, Type, Description, Ingredient, Front_Picture_Link, Instruction, Instruction_pic;

    Recipe newRecipe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        // Upper part of menu
        Toolbar toolbar = findViewById(R.id.menu_toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar

    //text fields
        Receipt_Name = findViewById(R.id.pName);
        Type = findViewById(R.id.pType);
        Description = findViewById(R.id.pDescription);
        Ingredient = findViewById(R.id.pIngredient);
        Front_Picture_Link = findViewById(R.id.pFrontLink);
        Instruction = findViewById(R.id.pStep1);
        Instruction_pic= findViewById(R.id.pStep1Link);
        //Step_2 = findViewById(R.id.pStep2);
        //Picture_Link_2 = findViewById(R.id.pStep2Link);

        //buttons
        bPostNext = (Button) findViewById(R.id.pSteps);
        bPostNext = findViewById(R.id.pSteps);
        bPostRecipe = findViewById(R.id.pPost);
        bAddTags =findViewById(R.id.pAddTags);
        bAddIngredient=findViewById(R.id.pAddIngredient);


        /*Button listeners */
        bPostRecipe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Do something in response to button click
                if(validate_info(newRecipe)) {
                    newRecipe.updateDB();
            //change intent?
                }
                else {

                }

            }
        });

        bPostNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Do something in response to button click
                if(Instruction.getText().length()>=0 &&Instruction_pic.getText().length()>=0) {

                newRecipe.instructions.add(Instruction.getText().toString());
                newRecipe.instruction_pics.add(Instruction_pic.getText().toString());
                    Instruction.setText("");
                    Instruction_pic.setText("");
                }
                else {

                }

            }
        });


        bAddIngredient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Do something in response to button click
                if(Ingredient.getText().length()>=0) {
                    newRecipe.ingredients.add(Ingredient.getText().toString());
                    //erase text box for next usage
                    Ingredient.setText("");

                }
                else {

                }

            }
        });

        bAddTags.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Do something in response to button click
                if(Instruction.getText().length()>=0) {
          //do something

                }
                else {

                }

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onStart(){
    super.onStart();
       newRecipe = new Recipe(); //initialize userdata object

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.toolbar_home:
                intent = new Intent(PostActivity.this, MenuActivity.class);
                startActivity(intent);
                return true;

            case R.id.toolbar_levels:
                intent = new Intent(PostActivity.this, LevelsActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_search:
                intent = new Intent(PostActivity.this, SearchActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_messages:
                intent = new Intent(PostActivity.this, MessagesActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_post:
                intent = new Intent(PostActivity.this, PostActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_profile:
                intent = new Intent(PostActivity.this, ProfileActivity.class);
                this.startActivity(intent);
                return true;

            case R.id.toolbar_edit_profile:
                intent = new Intent(PostActivity.this, EditProfile.class);
                this.startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void submit() {
        Intent intent;
        intent = new Intent(PostActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private boolean validate_info(Recipe newRecipe){
        boolean valid=true;
        if (Receipt_Name.getText().length()==0){
            valid=false;
            Receipt_Name.setError("Recipe is required");
        }
        else{
            newRecipe.recipe_name=Receipt_Name.getText().toString();

        }

        if (Type.getText().length()==0){
            //error
            valid=false;
            Type.setError("Type is Required");

        }else
        {
            newRecipe.tags.add(Type.getText().toString());
        }

        if (Description.getText().length()==0){
            valid=false;
            Description.setError("Description is required");
        }else
        {
            newRecipe.description=Description.getText().toString();

        }


        if (Ingredient.getText().length()==0){
            Ingredient.setError("An ingredient is required");
            valid=false;
        }else
        {
            newRecipe.ingredients.add(Ingredient.getText().toString());
        }

        if (newRecipe.instructions.size()!=0) {
        ;
        }
        else if (Instruction.getText().length()==0){
            Instruction.setError("A step is required");
            valid=false;
        }else
        {
            newRecipe.instructions.add(Instruction.getText().toString());
        }

        if (newRecipe.instruction_pics.size()!=0){
            ;
        }
        else if (Instruction_pic.getText().length()==0){
            Instruction_pic.setError("A Link is required?");
            valid=false;
        }else
        {
            newRecipe.instruction_pics.add(Instruction_pic.getText().toString());
        }

/* removed infavor of larger text box and add instruction button
        if (Step_2.getText().length()==0){
            Step_2.setError("A Step is required");
            valid=false;
        }else
        {
            newRecipe.instructions.add(Step_2.getText().toString());
        }


        if (Picture_Link_2.getText().length()==0){
            Picture_Link_2.setError("A link is required");
            valid=false;
        }else
        {
            newRecipe.instruction_pics.add(Picture_Link_2.getText().toString());
        }
*/
        return valid;
    }

}

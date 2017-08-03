package com.comp3717.comp3717;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> myIngredients = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // Bring user to liquor selection screen
    public void loadLiquor(View v) {
        Intent intent = new Intent(MainActivity.this, LiquorSelectActivity.class);
        startActivity(intent);
    }

    // Bring user to mixer selection screen
    public void loadMixers(View v) {
        Intent intent = new Intent(MainActivity.this, MixerSelectActivity.class);
        startActivity(intent);
    }

    // Bring user to garnish selection screen
    public void loadGarnishes(View v) {
        Intent intent = new Intent(MainActivity.this, GarnishSelectActivity.class);
        startActivity(intent);
    }

    // Shows all ingredients the user added
    public void showMyIngredients(View v) {
        if (myIngredients.size() == 0) {
            Toast.makeText(this, "You didn't add any ingredients yet.", Toast.LENGTH_LONG).show();
        } else {
            String ingredientStr = "";
            for (String ingredient : myIngredients){
                ingredientStr += (ingredient + ", ");
            }
            Toast.makeText(this, "My Ingredients: " + ingredientStr, Toast.LENGTH_LONG).show();
        }
    }

    public void findResults(View v) {
        Intent intent = new Intent(MainActivity.this, RecipeSelectAcitvity.class);
        //intent.putExtra("selectedIngredients", myIngredients);
        startActivity(intent);
    }
}

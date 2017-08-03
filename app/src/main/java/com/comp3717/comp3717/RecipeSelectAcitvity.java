package com.comp3717.comp3717;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.comp3717.comp3717.utils.RecipeFetcher;

import java.util.ArrayList;

public class RecipeSelectAcitvity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_select_acitvity);

        //ArrayList<String> aTest = new ArrayList<String>();
        //aTest.add("Gin");
        CursorAdapter cursorAdapter = new RecipeFetcher(this).getRecipeByIngredient(MainActivity.myIngredients);
        //CursorAdapter cursorAdapter = new RecipeFetcher(this).getRecipeByIngredient(aTest);
        setListAdapter(cursorAdapter);
    }

    @Override
    protected void onListItemClick(final ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);
        final Cursor recipeChosen = (Cursor) getListView().getItemAtPosition(position); // get recipe at clicked position
        String recipeName = recipeChosen.getString(recipeChosen.getColumnIndex("name"));
        String recipeDescription = recipeChosen.getString(recipeChosen.getColumnIndex("description"));
        Bundle bundle = new Bundle();
        bundle.putString("recipeName", recipeName); // store name in the key 'recipeName'
        bundle.putString("recipeDescription", recipeDescription);
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtras(bundle); // place bundle into the intent
        startActivity(intent);
    }
}

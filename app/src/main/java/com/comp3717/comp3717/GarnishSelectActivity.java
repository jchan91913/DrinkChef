package com.comp3717.comp3717;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.comp3717.comp3717.database.RecipeContract;
import com.comp3717.comp3717.database.RecipeDBHelper;
import com.comp3717.comp3717.utils.ID;
import com.comp3717.comp3717.utils.IngredientFetcher;

import java.util.ArrayList;
import java.util.List;

public class GarnishSelectActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String selectedIngredient;
    RecipeDBHelper rdbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garnish_select);
        rdbHelper = new RecipeDBHelper(this);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.garnishSpinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> garnishList = new ArrayList<String>();
        garnishList.add("Cherry");
        garnishList.add("Celery Stalk");
        garnishList.add("Lime Wedge");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, garnishList);

        CursorAdapter cursorAdapter = new IngredientFetcher(this).getIngredientByCategory(ID.ID_INGREDIENT_CATEGORY_GARNISH);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(cursorAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        SimpleCursorAdapter sac = (SimpleCursorAdapter) parent.getAdapter();
        Cursor c = sac.getCursor();
        c.moveToPosition(position);
        selectedIngredient = c.getString(c.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_NAME_NAME));

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void submitSelection(View v) {
        MainActivity.myIngredients.add(selectedIngredient);
        Intent intent = new Intent(GarnishSelectActivity.this, MainActivity.class);
        Toast.makeText(this, selectedIngredient + " added.", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}

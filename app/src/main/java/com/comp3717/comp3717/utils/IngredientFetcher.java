package com.comp3717.comp3717.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;

import com.comp3717.comp3717.R;
import com.comp3717.comp3717.database.RecipeContract;
import com.comp3717.comp3717.database.RecipeDBHelper;

/**
 * Created by Ntori on 11/21/2016.
 */

public class IngredientFetcher {

    Context context;

    public IngredientFetcher(Context context){
        this.context = context;
    }

    public CursorAdapter getIngredientByCategory(String categoryId) {
        RecipeDBHelper recipeDBHelper = new RecipeDBHelper(context);
        SQLiteDatabase db = recipeDBHelper.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + RecipeContract.RecipeEntry.INGREDIENTS_TABLE_NAME + " WHERE "
                + RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID + "=\'" + categoryId +"\'", null );
        String[] from = {RecipeContract.RecipeEntry.COLUMN_NAME_NAME};
        int[] to = {android.R.id.text1};
        final CursorAdapter cursorAdapter = new SimpleCursorAdapter(context, R.layout.support_simple_spinner_dropdown_item, res, from, to, 0);
        return cursorAdapter;
    }
}

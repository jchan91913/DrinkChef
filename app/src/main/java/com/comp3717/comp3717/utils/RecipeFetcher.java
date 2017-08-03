package com.comp3717.comp3717.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;

import com.comp3717.comp3717.R;
import com.comp3717.comp3717.database.RecipeContract;
import com.comp3717.comp3717.database.RecipeDBHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Created by Leo on 11/26/2016.
 */

public class RecipeFetcher {

    Context context;
    String ingredientId = RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENTS_ID;
    String ingerdientTable = RecipeContract.RecipeEntry.INGREDIENTS_TABLE_NAME;
    String receiptIngredientId = RecipeContract.RecipeEntry.COLUMN_NAME_RECIPE_INGREDIENTS_ID;
    String receiptIngredientTable = RecipeContract.RecipeEntry.RECIPE_INGREDIENTS_TABLE_NAME;
    String receiptTable =  RecipeContract.RecipeEntry.RECIPE_TABLE_NAME;
    String receiptId = RecipeContract.RecipeEntry.COLUMN_NAME_RECIPE_ID;


    public RecipeFetcher(Context context){
        this.context = context;
    }

    public CursorAdapter getRecipeByIngredient(ArrayList<String>  selectedIngredients) {
        RecipeDBHelper recipeDBHelper = new RecipeDBHelper(context);
        SQLiteDatabase db = recipeDBHelper.getReadableDatabase();

        //get ingredients' id for all selected ingredients
        ArrayList<String> ingredientIds = new ArrayList<String>();
        for(int i=0; i<selectedIngredients.size(); i++ ){
            Cursor res =  db.rawQuery( "SELECT " + ingredientId + " FROM "
                    + ingerdientTable + " WHERE "
                    + RecipeContract.RecipeEntry.COLUMN_NAME_NAME + "=\'" + selectedIngredients.get(i) +"\'", null );
            fetchResToArrayList(res, ingredientIds, RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENTS_ID);
        }

        //get receipts' id for all selected ingredients
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, Integer> sortedMapDesc = new HashMap<String, Integer>();
        for(int i=0; i<ingredientIds.size(); i++ ){
            Cursor res =  db.rawQuery( "SELECT " + receiptIngredientId + " FROM "
                    + receiptIngredientTable + " WHERE "
                    + ingredientId + "=\'" + ingredientIds.get(i) +"\'", null );
            fetchResToMap(res, map, receiptIngredientId);
            /*
            res.moveToFirst();
            while(res.isAfterLast() == false){
                map.merge(res.getString(res.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_NAME_RECIPE_ID)), 1,(v1,v2) -> v1++);
                res.moveToNext();
            }*/
            final boolean ASC = true;
            final boolean DESC = false;
            sortedMapDesc = sortByComparator(map, DESC);
        }

        //get receipts' name
        Set set = sortedMapDesc.keySet();
        Iterator<String> it = set.iterator();
        ArrayList<Cursor> cursorsAL = new ArrayList<Cursor>();
        while (it.hasNext()){
            Cursor res =  db.rawQuery( "SELECT * FROM "
                    + receiptTable + " WHERE "
                    + receiptId + "=\'" + it.next() +"\'", null );
            cursorsAL.add(res);
        }
        Cursor[] curs = cursorsAL.toArray(new Cursor[cursorsAL.size()]);
        Cursor cursors = new MergeCursor(curs);


        String[] from = {RecipeContract.RecipeEntry.COLUMN_NAME_NAME};
        int[] to = {android.R.id.text1};
        final CursorAdapter cursorAdapter = new SimpleCursorAdapter(context, R.layout.support_simple_spinner_dropdown_item, cursors, from, to, 0);
        return cursorAdapter;
    }

    private void fetchResToMap(Cursor res, Map<String, Integer> map, String columnName){
        res.moveToFirst();
        while(res.isAfterLast() == false){
            String keyReceiptsId = res.getString(res.getColumnIndex(columnName));
            if(map.get(keyReceiptsId) == null){
                map.put(keyReceiptsId, 1);
            }else{
                Integer count = map.get(keyReceiptsId) + 1;
                map.put(keyReceiptsId, count);
            }
            res.moveToNext();
        }
    }

    private void fetchResToArrayList(Cursor res, ArrayList<String> al, String columnName){
        res.moveToFirst();
        while(res.isAfterLast() == false){
            al.add(res.getString(res.getColumnIndex(columnName)));
            res.moveToNext();
        }
    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}

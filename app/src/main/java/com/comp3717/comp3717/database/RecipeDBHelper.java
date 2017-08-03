package com.comp3717.comp3717.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.comp3717.comp3717.R;
import com.comp3717.comp3717.utils.ID;
import com.comp3717.comp3717.utils.IngredientPackage;

import java.util.ArrayList;

/**
 * Created by Ntori on 11/18/2016.
 */

public class RecipeDBHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "recipes.db";

    private Context context;

    public RecipeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RecipeContract.SQL_CREATE_INGREDIENT_CATEGORY_TABLE);
        db.execSQL(RecipeContract.SQL_CREATE_GLASSWARE_TABLE);
        db.execSQL(RecipeContract.SQL_CREATE_RECIPE_CATEGORY_TABLE);


        db.execSQL(RecipeContract.SQL_CREATE_INGREDIENTS_TABLE);
        db.execSQL(RecipeContract.SQL_CREATE_RECIPE_TABLE);
        db.execSQL(RecipeContract.SQL_CREATE_RECIPE_INGREDIENTS_TABLE);

        insertIngredients(db);
        //insertLiquor(db);
        //insertMixers(db);
        //insertGarnishes(db);
        insertRecipes(db);

    }

    private void insertRecipe(SQLiteDatabase db, String recipeId, String name, ArrayList<IngredientPackage> ingredients){
        ContentValues cv = new ContentValues();
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_RECIPE_ID, recipeId);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_NAME, name);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_DESCRIPTION, context.getResources().getString(context.getResources().getIdentifier(recipeId, "string", context.getPackageName())));
        cv.putNull(RecipeContract.RecipeEntry.COLUMN_NAME_GLASSWARE_ID);
        cv.putNull(RecipeContract.RecipeEntry.COLUMN_NAME_RECIPE_CATEGORY_ID);
        db.insert(RecipeContract.RecipeEntry.RECIPE_TABLE_NAME, null, cv);

        for(IngredientPackage ingredient : ingredients) {
            ContentValues icv = new ContentValues();
            icv.put(RecipeContract.RecipeEntry.COLUMN_NAME_RECIPE_INGREDIENTS_ID, recipeId);
            icv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENTS_ID, ingredient.ingredientsId);
            icv.put(RecipeContract.RecipeEntry.COLUMN_NAME_AMOUNT, ingredient.amount);
            db.insert(RecipeContract.RecipeEntry.RECIPE_INGREDIENTS_TABLE_NAME, null, icv);
        }
    }

    private void insertRecipes(SQLiteDatabase db) {
        ArrayList<IngredientPackage> ingredients = new ArrayList<>();

        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_GIN, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_TONIC_WATER, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_LIME_JUICE, 1));
        insertRecipe(db, ID.ID_RECIPE_GIN_AND_TONIC, "Gin and Tonic", ingredients);
        ingredients.clear();

        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_DRY_GIN, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_DRY_VERMOUTH, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_LEMON_PEEL, 1));
        insertRecipe(db, ID.ID_RECIPE_MARTINI, "Martini", ingredients);
        ingredients.clear();

        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_GIN, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_CREAM, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_EGG_WHITE, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_LEMON_JUICE, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_LIME_JUICE, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_SUGAR, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_CLUB_SODA, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_ORANGE_FLOWER_WATER, 1));
        insertRecipe(db, ID.ID_RECIPE_RAMOS_FIZZ, "Ramos Fizz", ingredients);
        ingredients.clear();


        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_BOURBON, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_SUGAR, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_MINT_LEAF, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_MINT_SPRIG, 1));
        insertRecipe(db, ID.ID_RECIPE_MINT_JULEP, "Mint Julep", ingredients);
        ingredients.clear();

        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_WHISKEY, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_LEMON_JUICE, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_SUGAR, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_EGG_WHITE, 1));
        insertRecipe(db, ID.ID_RECIPE_WHISKEY_SOUR, "Whiskey Sour", ingredients);
        ingredients.clear();

        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_CITRUS_FLAVOURED_VODKA, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_TRIPLE_SEC, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_LIME_JUICE, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_ORANGE_TWIST, 1));
        insertRecipe(db, ID.ID_RECIPE_COSMOPOLITAN, "Cosmopolitan", ingredients);
        ingredients.clear();

        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_GIN, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_LEMON_JUICE, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_SUGAR, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_CLUB_SODA, 1));
        insertRecipe(db, ID.ID_RECIPE_TOM_COLLINS, "Tom Collins", ingredients);
        ingredients.clear();

        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_GIN, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_LIME_JUICE, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_MARASCHINO, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_CHARTREUSE_GREEN, 1));
        insertRecipe(db, ID.ID_RECIPE_THE_LAST_WORD, "The Last Word", ingredients);
        ingredients.clear();

        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_SUGAR, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_MINT_LEAF, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_WHITE_RUM, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_LIME_JUICE, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_CLUB_SODA, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_MINT_SPRIG, 1));
        ingredients.add(new IngredientPackage(ID.ID_INGREDIENT_LIME_TWIST, 1));
        insertRecipe(db, ID.ID_RECIPE_MOJITO, "Mojito", ingredients);
    }

    private void insertIngredients(SQLiteDatabase db){
        insertIngredient(db, ID.ID_INGREDIENT_LEMON_PEEL, "Lemon Peel", null, ID.ID_INGREDIENT_CATEGORY_GARNISH);
        insertIngredient(db, ID.ID_INGREDIENT_MINT_LEAF, "Mint Leaf", null, ID.ID_INGREDIENT_CATEGORY_GARNISH);
        insertIngredient(db, ID.ID_INGREDIENT_MINT_SPRIG, "Mint Sprig", null, ID.ID_INGREDIENT_CATEGORY_GARNISH);
        insertIngredient(db, ID.ID_INGREDIENT_ORANGE_TWIST, "Orange Twist", null, ID.ID_INGREDIENT_CATEGORY_GARNISH);
        insertIngredient(db, ID.ID_INGREDIENT_LIME_TWIST, "Lime Twist", null, ID.ID_INGREDIENT_CATEGORY_GARNISH);


        insertIngredient(db, ID.ID_INGREDIENT_TONIC_WATER, "Tonic Water", null, ID.ID_INGREDIENT_CATEGORY_MIXER);
        insertIngredient(db, ID.ID_INGREDIENT_CREAM, "Cream", null, ID.ID_INGREDIENT_CATEGORY_MIXER);
        insertIngredient(db, ID.ID_INGREDIENT_EGG_WHITE, "Egg White", null, ID.ID_INGREDIENT_CATEGORY_MIXER);
        insertIngredient(db, ID.ID_INGREDIENT_LEMON_JUICE, "Lemon Juice", null, ID.ID_INGREDIENT_CATEGORY_MIXER);
        insertIngredient(db, ID.ID_INGREDIENT_LIME_JUICE, "Lime Juice", null, ID.ID_INGREDIENT_CATEGORY_MIXER);
        insertIngredient(db, ID.ID_INGREDIENT_SUGAR, "Sugar", null, ID.ID_INGREDIENT_CATEGORY_MIXER);
        insertIngredient(db, ID.ID_INGREDIENT_CLUB_SODA, "Club Soda", null, ID.ID_INGREDIENT_CATEGORY_MIXER);
        insertIngredient(db, ID.ID_INGREDIENT_ORANGE_FLOWER_WATER, "Orange Flower Water", null, ID.ID_INGREDIENT_CATEGORY_MIXER);
        insertIngredient(db, ID.ID_INGREDIENT_CRANBERRY_JUICE, "Cranberry Juice", null, ID.ID_INGREDIENT_CATEGORY_MIXER);


        insertIngredient(db, ID.ID_INGREDIENT_GIN, "Gin", null, ID.ID_INGREDIENT_CATEGORY_LIQUOR);
        insertIngredient(db, ID.ID_INGREDIENT_DRY_GIN, "Dry Gin", null, ID.ID_INGREDIENT_CATEGORY_LIQUOR);
        insertIngredient(db, ID.ID_INGREDIENT_DRY_VERMOUTH, "Dry Vermouth", null, ID.ID_INGREDIENT_CATEGORY_LIQUOR);
        insertIngredient(db, ID.ID_INGREDIENT_BOURBON, "Bourbon", null, ID.ID_INGREDIENT_CATEGORY_LIQUOR);
        insertIngredient(db, ID.ID_INGREDIENT_WHISKEY, "Whiskey", null, ID.ID_INGREDIENT_CATEGORY_LIQUOR);
        insertIngredient(db, ID.ID_INGREDIENT_CITRUS_FLAVOURED_VODKA, "Citrus-flavoured Vodka", null, ID.ID_INGREDIENT_CATEGORY_LIQUOR);
        insertIngredient(db, ID.ID_INGREDIENT_TRIPLE_SEC, "Triple sec", null, ID.ID_INGREDIENT_CATEGORY_LIQUOR);
        insertIngredient(db, ID.ID_INGREDIENT_MARASCHINO, "Maraschino", null, ID.ID_INGREDIENT_CATEGORY_LIQUOR);
        insertIngredient(db, ID.ID_INGREDIENT_CHARTREUSE_GREEN, "Chartreuse Green", null, ID.ID_INGREDIENT_CATEGORY_LIQUOR);
        insertIngredient(db, ID.ID_INGREDIENT_WHITE_RUM, "White Rum", null, ID.ID_INGREDIENT_CATEGORY_LIQUOR);
    }

    private void insertIngredient(SQLiteDatabase db, String ingredientId, String name, String description, String ingredientCategory){
        ContentValues cv = new ContentValues();
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENTS_ID, ingredientId); //temp; maybe remove
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_NAME, name);
        if(description == null || description.trim().isEmpty()){
            cv.putNull(RecipeContract.RecipeEntry.COLUMN_NAME_DESCRIPTION);
        }else{
            cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_DESCRIPTION, description);
        }
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID, ingredientCategory);
        db.insert(RecipeContract.RecipeEntry.INGREDIENTS_TABLE_NAME, null, cv);
    }

    private void insertGarnishes(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENTS_ID, ID.ID_INGREDIENT_CHERRY); //temp; maybe remove
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_NAME, "Cherry");
        cv.putNull(RecipeContract.RecipeEntry.COLUMN_NAME_DESCRIPTION); //temp; currently no description
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID, ID.ID_INGREDIENT_CATEGORY_GARNISH);
        db.insert(RecipeContract.RecipeEntry.INGREDIENTS_TABLE_NAME, null, cv);

        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENTS_ID, ID.ID_INGREDIENT_CELERY_STALK);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_NAME, "Celery Stalk");
        cv.putNull(RecipeContract.RecipeEntry.COLUMN_NAME_DESCRIPTION);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID, ID.ID_INGREDIENT_CATEGORY_GARNISH);
        db.insert(RecipeContract.RecipeEntry.INGREDIENTS_TABLE_NAME, null, cv);

        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENTS_ID, ID.ID_INGREDIENT_LIME_WEDGE);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_NAME, "Lime Wedge");
        cv.putNull(RecipeContract.RecipeEntry.COLUMN_NAME_DESCRIPTION);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID, ID.ID_INGREDIENT_CATEGORY_GARNISH);
        db.insert(RecipeContract.RecipeEntry.INGREDIENTS_TABLE_NAME, null, cv);
    }

    private void insertMixers(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENTS_ID, ID.ID_INGREDIENT_TONIC_WATER);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_NAME, "Tonic Water");
        cv.putNull(RecipeContract.RecipeEntry.COLUMN_NAME_DESCRIPTION);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID, ID.ID_INGREDIENT_CATEGORY_MIXER);
        db.insert(RecipeContract.RecipeEntry.INGREDIENTS_TABLE_NAME, null, cv);

        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENTS_ID, ID.ID_INGREDIENT_PINEAPPLE_JUICE);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_NAME, "Pineapple Juice");
        cv.putNull(RecipeContract.RecipeEntry.COLUMN_NAME_DESCRIPTION);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID, ID.ID_INGREDIENT_CATEGORY_MIXER);
        db.insert(RecipeContract.RecipeEntry.INGREDIENTS_TABLE_NAME, null, cv);

        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENTS_ID, ID.ID_INGREDIENT_GRAPE_JUICE);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_NAME, "Grape Juice");
        cv.putNull(RecipeContract.RecipeEntry.COLUMN_NAME_DESCRIPTION);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID, ID.ID_INGREDIENT_CATEGORY_MIXER);
        db.insert(RecipeContract.RecipeEntry.INGREDIENTS_TABLE_NAME, null, cv);
    }

    private void insertLiquor(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENTS_ID, ID.ID_INGREDIENT_WHITE_RUM);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_NAME, "White Rum");
        cv.putNull(RecipeContract.RecipeEntry.COLUMN_NAME_DESCRIPTION);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID, ID.ID_INGREDIENT_CATEGORY_LIQUOR);
        db.insert(RecipeContract.RecipeEntry.INGREDIENTS_TABLE_NAME, null, cv);

        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENTS_ID, ID.ID_INGREDIENT_GIN);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_NAME, "Gin");
        cv.putNull(RecipeContract.RecipeEntry.COLUMN_NAME_DESCRIPTION);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID, ID.ID_INGREDIENT_CATEGORY_LIQUOR);
        db.insert(RecipeContract.RecipeEntry.INGREDIENTS_TABLE_NAME, null, cv);

        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENTS_ID, ID.ID_INGREDIENT_VODKA);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_NAME, "Vodka");
        cv.putNull(RecipeContract.RecipeEntry.COLUMN_NAME_DESCRIPTION);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID, ID.ID_INGREDIENT_CATEGORY_LIQUOR);
        db.insert(RecipeContract.RecipeEntry.INGREDIENTS_TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

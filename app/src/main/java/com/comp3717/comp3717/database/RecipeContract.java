package com.comp3717.comp3717.database;

import android.provider.BaseColumns;

/**
 * Created by Ntori on 11/18/2016.
 */

public class RecipeContract {

    public static abstract class RecipeEntry implements BaseColumns{
        public static final String INGREDIENTS_TABLE_NAME = "Ingredients";
        public static final String RECIPE_TABLE_NAME = "Recipe";
        public static final String RECIPE_INGREDIENTS_TABLE_NAME = "RecipeIngredients";
        public static final String INGREDIENT_CATEGORY_TABLE_NAME = "IngredientCategory";
        public static final String GLASSWARE_TABLE_NAME = "Glassware";
        public static final String RECIPE_CATEGORY_TABLE_NAME = "RecipeCategory";

        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_AMOUNT = "amount";

        public static final String COLUMN_NAME_GLASSWARE_ID = "glassware_id";
        public static final String COLUMN_NAME_INGREDIENT_CATEGORY_ID = "ingredient_category_id";
        public static final String COLUMN_NAME_RECIPE_CATEGORY_ID = "recipe_category_id";
        public static final String COLUMN_NAME_INGREDIENTS_ID = "ingredients_id";
        public static final String COLUMN_NAME_RECIPE_ID = "recipe_id";
        public static final String COLUMN_NAME_RECIPE_INGREDIENTS_ID = "recipe_ingredients_id";
    }

    public static final String SQL_CREATE_INGREDIENTS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + RecipeEntry.INGREDIENTS_TABLE_NAME + " (" +
                    RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RecipeEntry.COLUMN_NAME_INGREDIENTS_ID + " TEXT," +
                    RecipeEntry.COLUMN_NAME_NAME + " TEXT," +
                    RecipeEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID + " TEXT," +
                    "FOREIGN KEY(" + RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID + ") " +
                    "REFERENCES " + RecipeEntry.INGREDIENT_CATEGORY_TABLE_NAME + "(" + RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID + ")" + " )";

    public static final String SQL_CREATE_RECIPE_TABLE =
            "CREATE TABLE " + RecipeEntry.RECIPE_TABLE_NAME + " (" +
                    RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RecipeEntry.COLUMN_NAME_RECIPE_ID + " TEXT," +
                    RecipeEntry.COLUMN_NAME_NAME + " TEXT," +
                    RecipeEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    RecipeEntry.COLUMN_NAME_GLASSWARE_ID + " TEXT," +
                    RecipeEntry.COLUMN_NAME_RECIPE_CATEGORY_ID + " TEXT," +
                    "FOREIGN KEY(" + RecipeEntry.COLUMN_NAME_GLASSWARE_ID + ") " +
                    "REFERENCES " + RecipeEntry.GLASSWARE_TABLE_NAME + "(" + RecipeEntry.COLUMN_NAME_GLASSWARE_ID + ")," +
                    "FOREIGN KEY(" + RecipeEntry.COLUMN_NAME_RECIPE_CATEGORY_ID + ") " +
                    "REFERENCES " + RecipeEntry.RECIPE_CATEGORY_TABLE_NAME + "(" + RecipeEntry.COLUMN_NAME_RECIPE_CATEGORY_ID + ")" + " )";

    public static final String SQL_CREATE_RECIPE_INGREDIENTS_TABLE =
            "CREATE TABLE " + RecipeEntry.RECIPE_INGREDIENTS_TABLE_NAME + " (" +
                    RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RecipeEntry.COLUMN_NAME_RECIPE_INGREDIENTS_ID + " TEXT," +
                    RecipeEntry.COLUMN_NAME_INGREDIENTS_ID + " TEXT," +
                    RecipeEntry.COLUMN_NAME_AMOUNT + " INTEGER," +
                    "FOREIGN KEY(" + RecipeEntry.COLUMN_NAME_INGREDIENTS_ID + ") " +
                    "REFERENCES " + RecipeEntry.INGREDIENTS_TABLE_NAME + "(" + RecipeEntry.COLUMN_NAME_INGREDIENTS_ID + ")" + " )";

    public static final String SQL_CREATE_INGREDIENT_CATEGORY_TABLE =
            "CREATE TABLE " + RecipeEntry.INGREDIENT_CATEGORY_TABLE_NAME + " (" +
                    RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RecipeEntry.COLUMN_NAME_INGREDIENT_CATEGORY_ID + " TEXT," +
                    RecipeEntry.COLUMN_NAME_NAME + " TEXT" + " )";

    public static final String SQL_CREATE_GLASSWARE_TABLE =
            "CREATE TABLE " + RecipeEntry.GLASSWARE_TABLE_NAME + " (" +
                    RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RecipeEntry.COLUMN_NAME_GLASSWARE_ID + " TEXT," +
                    RecipeEntry.COLUMN_NAME_NAME + " TEXT" + " )";

    public static final String SQL_CREATE_RECIPE_CATEGORY_TABLE =
            "CREATE TABLE " + RecipeEntry.RECIPE_CATEGORY_TABLE_NAME + " (" +
                    RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RecipeEntry.COLUMN_NAME_RECIPE_CATEGORY_ID + " TEXT," +
                    RecipeEntry.COLUMN_NAME_NAME + " TEXT" + " )";





}

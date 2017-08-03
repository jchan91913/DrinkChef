package com.comp3717.comp3717;

import java.util.ArrayList;

/**
 * Created by Josh-ROG on 11/13/2016.
 */

public class Recipe {

    private String name;
    private ArrayList<String> ingredientList;
    // private String instructions; add later on

    public Recipe(String name, ArrayList<String> ingredientList) {
        this.name = name;
        this.ingredientList = ingredientList;
    }

    public ArrayList<String> getIngredientList() {
        return ingredientList;
    }

    public String getName() {
        return name;
    }
}

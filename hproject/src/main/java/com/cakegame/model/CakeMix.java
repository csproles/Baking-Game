package com.cakegame.model;

import com.cakegame.enums.CakeFlavor;
import java.util.ArrayList;
import java.util.List;

public class CakeMix {
    private List<Ingredient> ingredients;
    private CakeFlavor flavor;
    private boolean isMixed;
    
    public CakeMix() {
        this.ingredients = new ArrayList<>();
        this.isMixed = false;
    }
    
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setAdded(true);
    }
    
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public void setFlavor(CakeFlavor flavor) {
        this.flavor = flavor;
    }
    
    public CakeFlavor getFlavor() {
        return flavor;
    }
    
    public boolean hasRequiredIngredients() {
        // Check if all required ingredients are added
        // For simplicity, we'll check if we have at least 3 ingredients (e.g., cake mix, egg, milk)
        return ingredients.size() >= 3;
    }
    
    public void mix() {
        if (hasRequiredIngredients() && flavor != null) {
            isMixed = true;
        }
    }
    
    public boolean isMixed() {
        return isMixed;
    }
}

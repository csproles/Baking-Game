package com.cakegame.model;

import java.util.ArrayList;
import java.util.List;

public class ItemFactory {
    public static List<Ingredient> createAllIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(new Ingredient("Cake Mix", "mix", "/images/cake_mix.png"));
        ingredients.add(new Ingredient("Egg", "egg", "/images/egg.png"));
        ingredients.add(new Ingredient("Milk", "milk", "/images/milk.png"));

        ingredients.add(new Ingredient("White", "flavor", "/images/white_flavor.png"));
        ingredients.add(new Ingredient("Chocolate", "flavor", "/images/chocolate_flavor.png"));

        return ingredients;
    }

    public static List<Decoration> createAllDecorations() {
        List<Decoration> decorations = new ArrayList<>();

        decorations.add(new Decoration("White Icing", "icing", "/images/white_icing.png"));
        decorations.add(new Decoration("Pink Icing", "icing", "/images/pink_icing.png"));
        decorations.add(new Decoration("Chocolate Icing", "icing", "/images/chocolate_icing.png"));

        decorations.add(new Decoration("Round Tip", "border", "/images/round_tip.png"));
        decorations.add(new Decoration("Star Tip", "border", "/images/star_tip.png"));
        decorations.add(new Decoration("Leaf Tip", "border", "/images/leaf_tip.png"));

        decorations.add(new Decoration("Strawberries", "topping", "/images/strawberries.png"));
        decorations.add(new Decoration("Cookies", "topping", "/images/cookies.png"));
        decorations.add(new Decoration("Sprinkles", "topping", "/images/sprinkles.png"));

        decorations.add(new Decoration("Drip", "drip", "/images/drip.png"));

        return decorations;
    }
}


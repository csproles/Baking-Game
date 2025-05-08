package com.example.util;

import java.util.ArrayList;
import java.util.List;

import com.cakegame.enums.DecorationType;
import com.cakegame.model.Cake;
import com.cakegame.model.Customer;

public class GameUtils {

    // Generate a random recipe
    public static Recipe generateRandomRecipe() {
        Cake[] cakeTypes = Cake.values();

        Cake randomType = cakeTypes[(int)(Math.random() * cakeTypes.length)];

        List<DecorationType> decorations = new ArrayList<>();
        DecorationType[] decorationTypes = DecorationType.values();

        // Always include FROSTING if defined
        decorations.add(DecorationType.FROSTING);

        // Add 1-3 unique random decorations
        int numDecorations = 1 + (int)(Math.random() * 3);
        while (decorations.size() < numDecorations + 1) {
            DecorationType decoration = decorationTypes[(int)(Math.random() * decorationTypes.length)];
            if (!decorations.contains(decoration)) {
                decorations.add(decoration);
            }
        }

        return new Recipe();
    }

    // Generate a random customer
    public static Customer generateRandomCustomer() {
        String[] names = {
            "Alice", "Bob", "Charlie", "Diana", "Edward",
            "Fiona", "George", "Hannah", "Ian", "Julia"
        };

        String[] preferenceOptions = {
            "likes chocolate", "likes vanilla", "prefers round cakes",
            "loves decorations", "wants simple design", "likes colorful cakes"
        };

        String name = names[(int)(Math.random() * names.length)];
        List<String> preferences = new ArrayList<>();

        int numPreferences = 1 + (int)(Math.random() * 3);
        while (preferences.size() < numPreferences) {
            String preference = preferenceOptions[(int)(Math.random() * preferenceOptions.length)];
            if (!preferences.contains(preference)) {
                preferences.add(preference);
            }
        }

        int satisfactionThreshold = 60 + (int)(Math.random() * 31); // 60–90

        return new Customer();
    }

    // Function to calculate time bonus points
    public static int calculateTimeBonus(int timeRemaining, int maxTime) {
        // Bonus is up to 20 points depending on how much time was saved
        return (int)((timeRemaining / (double) maxTime) * 20);
    }
}


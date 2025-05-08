package com.example.util;

/**
 * Class for storing game configuration constants
 */
public class GameConfig {
    // Timing constants
    public static final int MIXING_TIME = 10; // seconds
    public static final int BAKING_TIME = 15; // seconds
    public static final int DECORATING_TIME = 20; // seconds
    
    // Score constants
    public static final int POINTS_BASE_CAKE = 100;
    public static final int POINTS_DECORATION = 50;
    public static final int POINTS_TOPPING = 25;
    public static final int POINTS_TIME_BONUS = 5; // per second left
    
    // Ingredient requirements
    public static final String[] REQUIRED_INGREDIENTS = {
        "Cake Mix", "Egg", "Milk"
    };
    
    // Color constants
    public static final String COLOR_BACKGROUND = "#F5F5F5";
    public static final String COLOR_INTERACTIVE = "#FF9800";
    public static final String COLOR_BUTTON = "#4CAF50";
    public static final String COLOR_TEXT = "#333333";
}

package com.example.util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Singleton class to track and manage the player's score throughout the game.
 */
public class ScoreTracker {
    private static ScoreTracker instance;
    
    // Score property that can be listened to for changes
    private IntegerProperty score = new SimpleIntegerProperty(0);
    
    // Points values for different actions
    private static final int CORRECT_CAKE_POINTS = 5;
    
    // Private constructor to prevent direct instantiation
    private ScoreTracker() {
        // Initialize with zero score
        score.set(0);
    }
    
    /**
     * Get the singleton instance of ScoreTracker
     * @return The ScoreTracker instance
     */
    public static synchronized ScoreTracker getInstance() {
        if (instance == null) {
            instance = new ScoreTracker();
        }
        return instance;
    }
    
    /**
     * Get the current score
     * @return The current score
     */
    public int getScore() {
        return score.get();
    }
    
    /**
     * Set the score directly
     * @param newScore The new score value
     */
    public void setScore(int newScore) {
        System.out.println("Setting score to: " + newScore);
        score.set(newScore);
    }
    
    /**
     * Reset the score to zero
     */
    public void resetScore() {
        score.set(0);
    }
    
    /**
     * Get the score property for binding to UI elements
     * @return The score property
     */
    public IntegerProperty scoreProperty() {
        return score;
    }
    
    /**
     * Add points for delivering the correct cake
     */
    public void addCorrectCakeScore() {
        System.out.println("Adding " + CORRECT_CAKE_POINTS + " points to score");
        int currentScore = score.get();
        score.set(currentScore + CORRECT_CAKE_POINTS);
        System.out.println("New score: " + score.get());
    }
}
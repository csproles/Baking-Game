package com.cakegame;

import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Level> levels = new ArrayList<>();
    private int currentLevelIndex = 0;
    
    public Level getCurrentLevel() {
        return levels.get(currentLevelIndex);
    }
    
    public boolean advanceToNextLevel() {
        if (currentLevelIndex < levels.size() - 1) {
            currentLevelIndex++;
            return true;
        }
        return false; // No more levels
    }
    
    public boolean isGameComplete() {
        return currentLevelIndex >= levels.size() - 1 && 
               getCurrentLevel().isGameComplete();
    }

    private List<Controller> orders;
    private int targetScore;
    private int timeLimit;
    private int ordersCompleted = 0;
    
    public boolean isComplete() {
        return ordersCompleted >= orders.size();
    }
    
    public boolean isSuccessful(int score) {
        return score >= targetScore;
    }
}
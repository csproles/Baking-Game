// BakingController.java
package com.cakegame.controller;

import com.cakegame.enums.StationState;
import com.cakegame.model.Cake;
import com.cakegame.model.GameStateManager;
import java.util.Random;

/**
 * Handles pouring and baking station logic
 */
public class BakingController {
    private GameStateManager gameState;
    private Random random;
    
    private int bakingTime; // in virtual seconds
    private int currentBakingTime;
    private boolean bakingStarted;
    
    public BakingController(GameStateManager gameState) {
        this.gameState = gameState;
        this.random = new Random();
        this.bakingTime = 10; // Default baking time
        this.currentBakingTime = 0;
        this.bakingStarted = false;
    }
    
    public boolean pourIntoTin() {
        if (gameState.getCurrentStation() != StationState.POURING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // Check if cake is mixed
        if (!cake.isMixed()) {
            return false;
        }
        
        cake.setPoured(true);
        return true;
    }
    
    public boolean moveFromPouringToBaking() {
        if (gameState.getCurrentStation() != StationState.POURING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // Only allow moving to next station if cake is poured
        if (!cake.isPoured()) {
            return false;
        }
        
        gameState.moveToNextStation();
        return true;
    }
    
    public boolean startBaking() {
        if (gameState.getCurrentStation() != StationState.BAKING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // Check if cake is poured
        if (!cake.isPoured()) {
            return false;
        }
        
        bakingStarted = true;
        currentBakingTime = 0;
        return true;
    }
    
    public boolean updateBaking() {
        if (!bakingStarted || gameState.getCurrentStation() != StationState.BAKING_STATION) {
            return false;
        }
        
        currentBakingTime++;
        Cake cake = gameState.getCurrentCake();
        
        // Check if baking is complete
        if (currentBakingTime >= bakingTime) {
            bakingStarted = false;
            cake.setBaked(true);
            
            // Calculate baking quality based on timing
            int quality;
            if (currentBakingTime == bakingTime) {
                // Perfect timing
                quality = 100;
            } else if (currentBakingTime <= bakingTime + 2) {
                // Slightly overbaked
                quality = 80;
            } else {
                // Overbaked
                quality = 60;
            }
            
            cake.setBakingQuality(quality);
            return true;
        }
        
        return false;
    }
    
    public boolean moveFromBakingToDecorating() {
        if (gameState.getCurrentStation() != StationState.BAKING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // Only allow moving to next station if cake is baked
        if (!cake.isBaked()) {
            return false;
        }
        
        gameState.moveToNextStation();
        return true;
    }
    
    // Utility methods for UI feedback
    public boolean canPour() {
        Cake cake = gameState.getCurrentCake();
        return cake.isMixed() && !cake.isPoured();
    }
    
    public boolean canStartBaking() {
        Cake cake = gameState.getCurrentCake();
        return cake.isPoured() && !cake.isBaked() && !bakingStarted;
    }
    
    public boolean isBaking() {
        return bakingStarted;
    }
    
    public int getBakingProgress() {
        return currentBakingTime * 100 / bakingTime;
    }
    
    public boolean canMoveToDecorating() {
        Cake cake = gameState.getCurrentCake();
        return cake.isBaked() && !bakingStarted;
    }
}


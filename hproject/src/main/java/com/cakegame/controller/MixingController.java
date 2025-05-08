package com.cakegame.controller;
// MixingController.java

import com.cakegame.enums.CakeFlavor;
import com.cakegame.enums.StationState;
import com.cakegame.model.Cake;
import com.cakegame.model.GameStateManager;
import java.util.Random;

/**
 * Handles mixing station logic
 */
public class MixingController {
    private GameStateManager gameState;
    private Random random;
    
    public MixingController(GameStateManager gameState) {
        this.gameState = gameState;
        this.random = new Random();
    }
    
    public boolean addCakeMix() {
        if (gameState.getCurrentStation() != StationState.MIXING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        cake.setHasCakeMix(true);
        return true;
    }
    
    public boolean addEgg() {
        if (gameState.getCurrentStation() != StationState.MIXING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        cake.setHasEgg(true);
        return true;
    }
    
    public boolean addMilk() {
        if (gameState.getCurrentStation() != StationState.MIXING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        cake.setHasMilk(true);
        return true;
    }
    
    public boolean setFlavor(CakeFlavor flavor) {
        if (gameState.getCurrentStation() != StationState.MIXING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        cake.setFlavor(flavor);
        return true;
    }
    
    public boolean mix(int mixingQuality) {
        if (gameState.getCurrentStation() != StationState.MIXING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // Check if all ingredients are added
        if (!cake.isReadyToMix()) {
            return false;
        }
        
        // Set mixing quality based on player action or random factor
        if (mixingQuality <= 0) {
            // If no quality specified, generate random quality between 60-100
            mixingQuality = 60 + random.nextInt(41);
        }
        
        cake.setMixingQuality(mixingQuality);
        cake.setMixed(true);
        
        return true;
    }
    
    public boolean moveToNextStation() {
        if (gameState.getCurrentStation() != StationState.MIXING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // Only allow moving to next station if cake is properly mixed
        if (!cake.isMixed()) {
            return false;
        }
        
        gameState.moveToNextStation();
        return true;
    }
    
    // Utility methods for UI feedback
    public boolean canMix() {
        Cake cake = gameState.getCurrentCake();
        return cake.isReadyToMix() && !cake.isMixed();
    }
    
    public boolean canMoveToNextStation() {
        Cake cake = gameState.getCurrentCake();
        return cake.isMixed();
    }
}

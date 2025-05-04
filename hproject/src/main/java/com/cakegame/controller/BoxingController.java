// BoxingController.java
package com.cakegame.controller;

import com.cakegame.enums.StationState;
import com.cakegame.model.Cake;
import com.cakegame.model.GameStateManager;

/**
 * Handles boxing station logic
 */
public class BoxingController {
    private GameStateManager gameState;
    
    public BoxingController(GameStateManager gameState) {
        this.gameState = gameState;
    }
    
    public boolean boxCake() {
        if (gameState.getCurrentStation() != StationState.BOXING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // Check if cake is decorated
        if (!cake.isReadyToBox()) {
            return false;
        }
        
        cake.setBoxed(true);
        return true;
    }
    
    public boolean moveToCustomer() {
        if (gameState.getCurrentStation() != StationState.BOXING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // Only allow moving to next station if cake is boxed
        if (!cake.isBoxed()) {
            return false;
        }
        
        gameState.moveToNextStation();
        return true;
    }
    
    // Utility methods for UI feedback
    public boolean canBoxCake() {
        Cake cake = gameState.getCurrentCake();
        return cake.isReadyToBox() && !cake.isBoxed();
    }
    
    public boolean canMoveToCustomer() {
        Cake cake = gameState.getCurrentCake();
        return cake.isBoxed();
    }
}

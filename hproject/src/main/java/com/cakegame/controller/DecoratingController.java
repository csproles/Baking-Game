// DecoratingController.java
package com.cakegame.controller;

import com.cakegame.enums.BorderStyle;
import com.cakegame.enums.IcingType;
import com.cakegame.enums.StationState;
import com.cakegame.enums.ToppingType;
import com.cakegame.model.Cake;
import com.cakegame.model.GameStateManager;
import java.util.Random;

/**
 * Handles decorating station logic
 */
public class DecoratingController {
    private GameStateManager gameState;
    private Random random;
    
    public DecoratingController(GameStateManager gameState) {
        this.gameState = gameState;
        this.random = new Random();
    }
    
    public boolean applyIcing(IcingType icingType) {
        if (gameState.getCurrentStation() != StationState.DECORATING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // Check if cake is baked
        if (!cake.isReadyToDecorate()) {
            return false;
        }
        
        cake.setIcingType(icingType);
        return true;
    }
    
    public boolean applyBorder(BorderStyle borderStyle) {
        if (gameState.getCurrentStation() != StationState.DECORATING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // Need icing first
        if (cake.getIcingType() == null) {
            return false;
        }
        
        cake.setBorderStyle(borderStyle);
        return true;
    }
    
    public boolean addTopping(ToppingType toppingType) {
        if (gameState.getCurrentStation() != StationState.DECORATING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // Need icing first
        if (cake.getIcingType() == null) {
            return false;
        }
        
        cake.addTopping(toppingType);
        return true;
    }
    
    public boolean toggleDrip() {
        if (gameState.getCurrentStation() != StationState.DECORATING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // Need icing first
        if (cake.getIcingType() == null) {
            return false;
        }
        
        cake.setDripAdded(!cake.isDripAdded());
        return true;
    }
    
    public boolean finishDecorating() {
        if (gameState.getCurrentStation() != StationState.DECORATING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // At minimum, need icing to finish
        if (cake.getIcingType() == null) {
            return false;
        }
        
        // Calculate decorating quality based on completeness
        int quality = calculateDecoratingQuality(cake);
        cake.setDecoratingQuality(quality);
        cake.setDecorated(true);
        
        return true;
    }
    
    private int calculateDecoratingQuality(Cake cake) {
        int quality = 0;
        
        // Base 60 points for icing
        if (cake.getIcingType() != null) {
            quality += 60;
        }
        
        // 10 points for border
        if (cake.getBorderStyle() != null) {
            quality += 10;
        }
        
        // Up to 20 points for toppings (more variety is better)
        int toppingCount = cake.getToppings().size();
        quality += Math.min(20, toppingCount * 7);
        
        // 10 points for drip
        if (cake.isDripAdded()) {
            quality += 10;
        }
        
        return quality;
    }
    
    public boolean moveToBoxing() {
        if (gameState.getCurrentStation() != StationState.DECORATING_STATION) {
            return false;
        }
        
        Cake cake = gameState.getCurrentCake();
        
        // Only allow moving to next station if cake is decorated
        if (!cake.isDecorated()) {
            return false;
        }
        
        gameState.moveToNextStation();
        return true;
    }
    
    // Utility methods for UI feedback
    public boolean canApplyIcing() {
        Cake cake = gameState.getCurrentCake();
        return cake.isReadyToDecorate() && cake.getIcingType() == null;
    }
    
    public boolean canAddDecorations() {
        Cake cake = gameState.getCurrentCake();
        return cake.getIcingType() != null;
    }
    
    public boolean canFinishDecorating() {
        Cake cake = gameState.getCurrentCake();
        return cake.getIcingType() != null && !cake.isDecorated();
    }
    
    public boolean canMoveToBoxing() {
        Cake cake = gameState.getCurrentCake();
        return cake.isDecorated();
    }
}


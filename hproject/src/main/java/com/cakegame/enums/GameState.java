// GameState.java
package com.cakegame.enums;

import com.cakegame.model.Cake;

/**
 * Enum representing the different states of the cake making process.
 */
public enum GameState {
    SELECTING_INGREDIENTS,  // Player is choosing cake mix, egg, milk, flavor
    MIXING,                 // Ingredients are in the mixer
    POURING,                // Batter is being poured into cake tin
    BAKING,                 // Cake is in the oven
    DECORATING,             // Player is adding icing, toppings, etc.
    PACKAGING,              // Cake is being boxed
    COMPLETE                // Cake has been delivered to customer
, MAIN_MENU, PLAYING, PAUSED;

    
    private static GameState currentState = SELECTING_INGREDIENTS;
    
    /**
     * Gets the current state of the game.
     * @return Current GameState
     */
    public static GameState getCurrentState() {
        return currentState;
    }
    
    /**
     * Sets the current state of the game.
     * @param newState New GameState to set
     */
    public static void setCurrentState(GameState newState) {
        currentState = newState;
    }
    
    /**
     * Advances to the next state in the game flow.
     */
    public static void advanceState() {
        switch (currentState) {
            case SELECTING_INGREDIENTS:
                currentState = MIXING;
                break;
            case MIXING:
                currentState = POURING;
                break;
            case POURING:
                currentState = BAKING;
                break;
            case BAKING:
                currentState = DECORATING;
                break;
            case DECORATING:
                currentState = PACKAGING;
                break;
            case PACKAGING:
                currentState = COMPLETE;
                break;
            case COMPLETE:
                // Game complete, reset to beginning
                currentState = SELECTING_INGREDIENTS;
                break;
        }
    }
    
    /**
     * Checks if the game can advance to the next state.
     * @param cakeModel The current cake model
     * @return True if can advance, false otherwise
     */
    public static boolean canAdvanceState(Cake cakeModel) {
        switch (currentState) {
            case SELECTING_INGREDIENTS:
                return cakeModel.hasRequiredIngredients();
            case MIXING:
                return cakeModel.isMixComplete();
            case POURING:
                return cakeModel.isMixComplete();
            case BAKING:
                return cakeModel.isMixComplete();
            case DECORATING:
                return cakeModel.isMixComplete();
            case PACKAGING:
                return cakeModel.isPackaged();
            case COMPLETE:
                return true; // Can always reset the game
            default:
                return false;
        }
    }
}

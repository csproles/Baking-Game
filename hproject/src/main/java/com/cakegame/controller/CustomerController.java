package com.cakegame.controller;

import com.cakegame.GameController;
import com.cakegame.enums.StationState;
import com.cakegame.model.Cake;
import com.cakegame.model.Customer;
import com.cakegame.model.GameStateManager;

import javafx.scene.Node;

/**
 * Handles customer station logic
 */
public class CustomerController {
    private GameStateManager gameState;

    public CustomerController(GameStateManager gameState) {
        this.gameState = gameState;
    }

    public boolean serveCake() {
        if (gameState.getCurrentStation() != StationState.CUSTOMER_STATION) {
            return false;
        }

        Cake cake = gameState.getCurrentCake();
        Customer customer = gameState.getCurrentCustomer();

        // Check if cake is boxed
        if (!cake.isReadyForCustomer()) {
            return false;
        }

        // Evaluate cake against customer preferences (logic is in Customer class)
        int satisfaction = customer.evaluateCake(cake);

        // Start a new cake cycle
        gameState.moveToNextStation();

        return true;
    }

    // Get customer satisfaction for display
    public int getCustomerSatisfaction() {
        Customer customer = gameState.getCurrentCustomer();
        return customer.getSatisfactionLevel();
    }

    // Utility methods for UI feedback
    public boolean canServeCake() {
        Cake cake = gameState.getCurrentCake();
        return cake.isReadyForCustomer();
    }

    // Optional: Provide a basic implementation or comment out if unused
    public void initialize(com.cakegame.controller.GameController gameController) {
        // Initialize any UI components or listeners if needed
    }

    public Node getView() {
        // Return the Node associated with the customer UI
        return null; // Replace with actual view if implemented
    }

    public void refresh() {
        // Optional UI refresh logic if needed
    }
}


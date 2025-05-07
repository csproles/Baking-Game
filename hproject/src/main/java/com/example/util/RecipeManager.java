// RecipeManager.java
package com.example.util;

import com.cakegame.enums.*;
import com.cakegame.model.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Manages predefined recipes and customer preferences
 */
public class RecipeManager {
    private static final Random random = new Random();
    
    // Predefined customer preferences for different difficulty levels
    private static Map<GameDifficulty, List<Customer>> customerTemplates = new HashMap<>();
    
    // Initialize customer templates
    static {
        initializeCustomerTemplates();
    }
    
    private static void initializeCustomerTemplates() {
        // Easy customers - simple preferences
        List<Customer> easyCustomers = new ArrayList<>();
        
        // Easy customer 1 - White cake with white icing
        Customer easy1 = new Customer();
        // Set preferences manually rather than random
        // Code to set preferences would go here
        easyCustomers.add(easy1);
        
        // Medium customers - more specific preferences
        List<Customer> mediumCustomers = new ArrayList<>();
        // Medium customer code would go here
        
        // Hard customers - very specific preferences
        List<Customer> hardCustomers = new ArrayList<>();
        // Hard customer code would go here
        
        customerTemplates.put(GameDifficulty.EASY, easyCustomers);
        customerTemplates.put(GameDifficulty.MEDIUM, mediumCustomers);
        customerTemplates.put(GameDifficulty.HARD, hardCustomers);
    }
    
    /**
     * Get a random customer based on difficulty level
     */
    public static Customer getRandomCustomer(GameDifficulty difficulty) {
        List<Customer> templates = customerTemplates.get(difficulty);
        
        if (templates != null && !templates.isEmpty()) {
            // Return a copy of a random template
            return templates.get(random.nextInt(templates.size()));
        } else {
            // Fall back to completely random customer
            return new Customer();
        }
    }
}

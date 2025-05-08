// Customer.java
package com.cakegame.model;

import com.cakegame.enums.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Customer {
    private CakeFlavor preferredFlavor;
    private IcingType preferredIcing;
    private BorderStyle preferredBorder;
    private List<ToppingType> preferredToppings;
    private boolean wantsDrip;
    
    private int satisfactionLevel; // 0-100
    private int patience; // Time in seconds before customer leaves
    
    public Customer() {
        Random random = new Random();
        preferredToppings = new ArrayList<>();
        generateRandomPreferences(random);
    }
    
    private void generateRandomPreferences(Random random) {
        // Randomly select preferences
        CakeFlavor[] flavors = CakeFlavor.values();
        this.preferredFlavor = flavors[random.nextInt(flavors.length)];
        
        IcingType[] icings = IcingType.values();
        this.preferredIcing = icings[random.nextInt(icings.length)];
        
        BorderStyle[] borders = BorderStyle.values();
        this.preferredBorder = borders[random.nextInt(borders.length)];
        
        // Random number of toppings (0-3)
        ToppingType[] toppings = ToppingType.values();
        int numToppings = random.nextInt(toppings.length + 1);
        
        // Add random unique toppings
        List<Integer> usedIndices = new ArrayList<>();
        for (int i = 0; i < numToppings; i++) {
            int index;
            do {
                index = random.nextInt(toppings.length);
            } while (usedIndices.contains(index));
            
            usedIndices.add(index);
            preferredToppings.add(toppings[index]);
        }
        
        // 50% chance for drip
        this.wantsDrip = random.nextBoolean();
        
        // Set initial satisfaction and patience
        this.satisfactionLevel = 100;
        this.patience = 30 + random.nextInt(31); // 30-60 seconds
    }
    
    // Getters
    public CakeFlavor getPreferredFlavor() { return preferredFlavor; }
    public IcingType getPreferredIcing() { return preferredIcing; }
    public BorderStyle getPreferredBorder() { return preferredBorder; }
    public List<ToppingType> getPreferredToppings() { return preferredToppings; }
    public boolean wantsDrip() { return wantsDrip; }
    public int getSatisfactionLevel() { return satisfactionLevel; }
    public int getPatience() { return patience; }
    
    // Setters
    public void setSatisfactionLevel(int satisfactionLevel) {
        this.satisfactionLevel = Math.max(0, Math.min(100, satisfactionLevel));
    }
    
    public void decreasePatience(int amount) {
        this.patience -= amount;
        if (this.patience < 0) this.patience = 0;
    }
    
    // Evaluate cake against preferences
    public int evaluateCake(Cake cake) {
        int score = 0;
        int totalCriteria = 5; // Base criteria count
        
        // Check flavor match
        if (cake.getFlavor() == preferredFlavor) {
            score += 20;
        }
        
        // Check icing match
        if (cake.getIcingType() == preferredIcing) {
            score += 20;
        }
        
        // Check border match
        if (cake.getBorderStyle() == preferredBorder) {
            score += 20;
        }
        
        // Check drip match
        if (cake.isDripAdded() == wantsDrip) {
            score += 20;
        }
        
        // Check toppings match (partial credit for partial matches)
        int toppingScore = evaluateToppings(cake.getToppings());
        score += toppingScore;
        
        // Factor in quality 
        score = (int)(score * (cake.calculateTotalQuality() / 100.0));
        
        satisfactionLevel = score;
        return score;
    }
    
    private int evaluateToppings(List<ToppingType> cakeToppings) {
        int maxToppingScore = 20;
        
        // If no toppings are wanted and none are on the cake, perfect score
        if (preferredToppings.isEmpty() && cakeToppings.isEmpty()) {
            return maxToppingScore;
        }
        
        // Count matches and extras
        int matches = 0;
        int extras = 0;
        
        // Count matched toppings
        for (ToppingType wanted : preferredToppings) {
            if (cakeToppings.contains(wanted)) {
                matches++;
            }
        }
        
        // Count extra unwanted toppings
        for (ToppingType given : cakeToppings) {
            if (!preferredToppings.contains(given)) {
                extras++;
            }
        }
        
        int totalWanted = preferredToppings.size();
        
        // Calculate percentage match and deduct for extras
        double matchPercentage = totalWanted == 0 ? 0 : (double) matches / totalWanted;
        int baseScore = (int) (maxToppingScore * matchPercentage);
        
        // Deduct points for each extra topping (up to half the max score)
        int penalty = Math.min(maxToppingScore / 2, extras * 5);
        
        return Math.max(0, baseScore - penalty);
    }

    public boolean wantsTopping() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'wantsTopping'");
    }

    public Object getPreferredRibbon() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPreferredRibbon'");
    }

    public boolean wantsCard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'wantsCard'");
    }
}

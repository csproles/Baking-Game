// CakeFlavor.java
package com.cakegame.enums;

public enum CakeFlavor {
    WHITE("White"),
    CHOCOLATE("Chocolate");
    
    private final String displayName;
    
    CakeFlavor(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
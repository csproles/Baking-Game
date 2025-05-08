// ToppingType.java
package com.cakegame.enums;

public enum ToppingType {
    STRAWBERRIES("Strawberries"),
    COOKIES("Cookies"),
    SPRINKLES("Sprinkles");
    
    private final String displayName;
    
    ToppingType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}


// IcingType.java
package com.cakegame.enums;

public enum IcingType {
    WHITE("White"),
    PINK("Pink"),
    CHOCOLATE("Chocolate");
    
    private final String displayName;
    
    IcingType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}

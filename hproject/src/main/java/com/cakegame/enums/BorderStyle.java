// BorderStyle.java
package com.cakegame.enums;

public enum BorderStyle {
    ROUND("Round"),
    STAR("Star"),
    PETAL("Petal"),
    LEAF("Leaf");
    
    private final String displayName;
    
    BorderStyle(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}

// Cake.java
package com.cakegame.model;

import com.cakegame.enums.*;
import java.util.ArrayList;
import java.util.List;

public class Cake {
    private boolean hasCakeMix;
    private boolean hasEgg;
    private boolean hasMilk;
    private CakeFlavor flavor;
    private boolean isMixed;
    private boolean isPoured;
    private boolean isBaked;
    private boolean isDecorated;
    private boolean isBoxed;
    private boolean isDripAdded;
    
    private IcingType icingType;
    private BorderStyle borderStyle;
    private List<ToppingType> toppings;
    
    // Quality metrics (0-100)
    private int mixingQuality;
    private int bakingQuality;
    private int decoratingQuality;
    
    public Cake() {
        this.hasCakeMix = false;
        this.hasEgg = false;
        this.hasMilk = false;
        this.flavor = null;
        this.isMixed = false;
        this.isPoured = false;
        this.isBaked = false;
        this.isDecorated = false;
        this.isBoxed = false;
        this.isDripAdded = false;
        this.toppings = new ArrayList<>();
        
        this.mixingQuality = 0;
        this.bakingQuality = 0;
        this.decoratingQuality = 0;
    }
    
    // Getters and setters
    public boolean hasCakeMix() { return hasCakeMix; }
    public void setHasCakeMix(boolean hasCakeMix) { this.hasCakeMix = hasCakeMix; }
    
    public boolean hasEgg() { return hasEgg; }
    public void setHasEgg(boolean hasEgg) { this.hasEgg = hasEgg; }
    
    public boolean hasMilk() { return hasMilk; }
    public void setHasMilk(boolean hasMilk) { this.hasMilk = hasMilk; }
    
    public CakeFlavor getFlavor() { return flavor; }
    public void setFlavor(CakeFlavor flavor) { this.flavor = flavor; }
    
    public boolean isMixed() { return isMixed; }
    public void setMixed(boolean mixed) { isMixed = mixed; }
    
    public boolean isPoured() { return isPoured; }
    public void setPoured(boolean poured) { isPoured = poured; }
    
    public boolean isBaked() { return isBaked; }
    public void setBaked(boolean baked) { isBaked = baked; }
    
    public boolean isDecorated() { return isDecorated; }
    public void setDecorated(boolean decorated) { isDecorated = decorated; }
    
    public boolean isBoxed() { return isBoxed; }
    public void setBoxed(boolean boxed) { isBoxed = boxed; }
    
    public boolean isDripAdded() { return isDripAdded; }
    public void setDripAdded(boolean dripAdded) { isDripAdded = dripAdded; }
    
    public IcingType getIcingType() { return icingType; }
    public void setIcingType(IcingType icingType) { this.icingType = icingType; }
    
    public BorderStyle getBorderStyle() { return borderStyle; }
    public void setBorderStyle(BorderStyle borderStyle) { this.borderStyle = borderStyle; }
    
    public List<ToppingType> getToppings() { return toppings; }
    public void addTopping(ToppingType topping) { 
        if (!toppings.contains(topping)) {
            this.toppings.add(topping); 
        }
    }
    
    public int getMixingQuality() { return mixingQuality; }
    public void setMixingQuality(int mixingQuality) { 
        this.mixingQuality = Math.max(0, Math.min(100, mixingQuality)); 
    }
    
    public int getBakingQuality() { return bakingQuality; }
    public void setBakingQuality(int bakingQuality) { 
        this.bakingQuality = Math.max(0, Math.min(100, bakingQuality)); 
    }
    
    public int getDecoratingQuality() { return decoratingQuality; }
    public void setDecoratingQuality(int decoratingQuality) { 
        this.decoratingQuality = Math.max(0, Math.min(100, decoratingQuality)); 
    }
    
    // Helper methods
    public boolean isReadyToMix() {
        return hasCakeMix && hasEgg && hasMilk && flavor != null;
    }
    
    public boolean isReadyToPour() {
        return isMixed;
    }
    
    public boolean isReadyToBake() {
        return isPoured;
    }
    
    public boolean isReadyToDecorate() {
        return isBaked;
    }
    
    public boolean isReadyToBox() {
        return isDecorated;
    }
    
    public boolean isReadyForCustomer() {
        return isBoxed;
    }
    
    public int calculateTotalQuality() {
        return (mixingQuality + bakingQuality + decoratingQuality) / 3;
    }

    public boolean hasDrip() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasDrip'");
    }

    public void setHasDrip(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHasDrip'");
    }

    public void boxCake() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'boxCake'");
    }

    public boolean hasRequiredIngredients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasRequiredIngredients'");
    }

    public boolean isMixComplete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isMixComplete'");
    }

    public boolean isPackaged() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isPackaged'");
    }

    public static Cake[] values() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'values'");
    }

    public boolean hasStrawberries() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasStrawberries'");
    }

    public boolean hasCookies() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasCookies'");
    }

    public boolean hasSprinkles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasSprinkles'");
    }

    public void setHasCard(boolean hasCard) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHasCard'");
    }

    public void setRibbonColor(RibbonColor ribbonColor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRibbonColor'");
    }

    public boolean hasCard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasCard'");
    }

    public RibbonColor getRibbonColor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRibbonColor'");
    }
}

package com.cakegame.model;

import com.cakegame.enums.DecorationType;

public class Decoration extends DraggableItem {
    private String category; // "icing", "border", "topping", "drip"

    public Decoration(String name, String category, String imagePath) {
        super(name, "decoration", imagePath);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public boolean isDrip() {
        return "drip".equalsIgnoreCase(category);
    }

    @Override
    public Object onDragStart() {
        return this;
    }

    @Override
    public <DropTarget> void onDragEnd(DropTarget target) {
        // Implement based on drop location
    }
}

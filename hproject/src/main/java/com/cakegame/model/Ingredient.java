package com.cakegame.model;

import javafx.scene.image.ImageView;

public class Ingredient extends DraggableItem {
    public Ingredient(String name, String type, String imagePath) {
        super(name, type, imagePath);
    }

    public Object getVariant() {
        return null; // Implement based on your logic
    }

    public boolean isFlavor() {
        return "flavor".equalsIgnoreCase(getType());
    }

    public boolean isBaseIngredient() {
        return "mix".equalsIgnoreCase(getType()) || "egg".equalsIgnoreCase(getType()) || "milk".equalsIgnoreCase(getType());
    }

    @Override
    public Object onDragStart() {
        return this;
    }

    @Override
    public <DropTarget> void onDragEnd(DropTarget target) {
        // Implement drop logic
    }

    public void setAdded(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAdded'");
    }
}

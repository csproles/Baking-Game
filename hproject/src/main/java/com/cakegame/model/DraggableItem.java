package com.cakegame.model;

import javafx.scene.image.Image;

public abstract class DraggableItem {
    private String name;
    private String type;
    private Image image;

    public DraggableItem(String name, String type, String imagePath) {
        this.name = name;
        this.type = type;
        try {
            this.image = new Image(getClass().getResourceAsStream(imagePath));
        } catch (Exception e) {
            System.err.println("Failed to load image: " + imagePath);
            this.image = null;
        }
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Image getImage() {
        return image;
    }

    public abstract Object onDragStart();

    public Object onDrag(double sceneX, double sceneY) {
        throw new UnsupportedOperationException("Unimplemented method 'onDrag'");
    }

    public abstract <DropTarget> void onDragEnd(DropTarget target);
}

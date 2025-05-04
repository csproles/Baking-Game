package com.cakegame.controller;

import com.cakegame.MainSceneController;
import com.cakegame.model.Decoration;
import com.cakegame.model.Ingredient;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

/**
 * Manages drag and drop operations for game elements
 * This class will be used by your teammate working on UI and movement
 */
public class DragDropManager {
    
    // Store reference to the main controller
    private MainSceneController controller;
    
    // Temporary storage for drag source
    private Node dragSource;
    
    /**
     * Constructor
     * 
     * @param controller Reference to the main controller
     */
    public DragDropManager(MainSceneController controller) {
        this.controller = controller;
    }
    
    /**
     * Set up drag source for an ingredient
     * 
     * @param source The node that can be dragged
     * @param ingredient The ingredient associated with the node
     */
    public void setupIngredientDragSource(Node source, Ingredient ingredient) {
        source.setOnDragDetected(event -> {
            // Store the source for later reference
            dragSource = source;
            
            // Start drag operation
            Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
            
            // Put a string identifier on the dragboard
            ClipboardContent content = new ClipboardContent();
            content.putString("ingredient:" + ingredient.getName());
            db.setContent(content);
            
            // Set a drag view (visual during drag)
            if (source instanceof ImageView) {
                ImageView iv = (ImageView) source;
                db.setDragView(iv.getImage());
            }
            
            event.consume();
        });
        
        // Clean up after drag
        source.setOnDragDone(event -> {
            dragSource = null;
            event.consume();
        });
    }
    
    /**
     * Set up drag source for a decoration
     * 
     * @param source The node that can be dragged
     * @param decoration The decoration associated with the node
     */
    public void setupDecorationDragSource(Node source, Decoration decoration) {
        source.setOnDragDetected(event -> {
            // Store the source for later reference
            dragSource = source;
            
            // Start drag operation
            Dragboard db = source.startDragAndDrop(TransferMode.COPY);
            
            // Put a string identifier on the dragboard
            ClipboardContent content = new ClipboardContent();
            content.putString("decoration:" + decoration.getName());
            db.setContent(content);
            
            // Set a drag view (visual during drag)
            if (source instanceof ImageView) {
                ImageView iv = (ImageView) source;
                db.setDragView(iv.getImage());
            }
            
            event.consume();
        });
        
        // Clean up after drag
        source.setOnDragDone(event -> {
            dragSource = null;
            event.consume();
        });
    }
    
    /**
     * Set up drop target for ingredients
     * 
     * @param target The node that accepts drops
     * @param onDropped Callback to execute when an ingredient is dropped
     */
    public void setupIngredientDropTarget(Node target, IngredientDropHandler onDropped) {
        // Show as valid target during drag over
        target.setOnDragOver(event -> {
            // Only accept if it's an ingredient being dragged
            if (event.getDragboard().hasString() && 
                event.getDragboard().getString().startsWith("ingredient:")) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
        
        // Visual feedback when entering target
        target.setOnDragEntered(event -> {
            if (event.getDragboard().hasString() && 
                event.getDragboard().getString().startsWith("ingredient:")) {
                target.setStyle("-fx-border-color: green; -fx-border-width: 2;");
            }
            event.consume();
        });
        
        // Clear visual feedback when exiting target
        target.setOnDragExited(event -> {
            target.setStyle("");
            event.consume();
        });
        
        // Handle the drop
        target.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            
            if (db.hasString() && db.getString().startsWith("ingredient:")) {
                String ingredientName = db.getString().substring("ingredient:".length());
                
                // Call the handler
                if (onDropped != null) {
                    success = onDropped.handleDrop(ingredientName);
                }
            }
            
            event.setDropCompleted(success);
            event.consume();
        });
    }
    
    /**
     * Set up drop target for decorations
     * 
     * @param target The node that accepts drops
     * @param onDropped Callback to execute when a decoration is dropped
     */
    public void setupDecorationDropTarget(Node target, DecorationDropHandler onDropped) {
        // Similar to setupIngredientDropTarget but for decorations
        target.setOnDragOver(event -> {
            if (event.getDragboard().hasString() && 
                event.getDragboard().getString().startsWith("decoration:")) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });
        
        target.setOnDragEntered(event -> {
            if (event.getDragboard().hasString() && 
                event.getDragboard().getString().startsWith("decoration:")) {
                target.setStyle("-fx-border-color: blue; -fx-border-width: 2;");
            }
            event.consume();
        });
        
        target.setOnDragExited(event -> {
            target.setStyle("");
            event.consume();
        });
        
        target.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            
            if (db.hasString() && db.getString().startsWith("decoration:")) {
                String decorationName = db.getString().substring("decoration:".length());
                
                // Call the handler
                if (onDropped != null) {
                    success = onDropped.handleDrop(decorationName);
                }
            }
            
            event.setDropCompleted(success);
            event.consume();
        });
    }
    
    /**
     * Create a draggable preview image for ingredients or decorations
     * 
     * @param imagePath The path to the image
     * @param x The initial x position
     * @param y The initial y position
     * @param gameArea The pane to add the image to
     * @return The created ImageView
     */
    public ImageView createDraggablePreview(String imagePath, double x, double y, Pane gameArea) {
        Image image = new Image(imagePath);
        ImageView preview = new ImageView(image);
        preview.setFitWidth(50);
        preview.setFitHeight(50);
        preview.setLayoutX(x);
        preview.setLayoutY(y);
        
        // Add to game area
        gameArea.getChildren().add(preview);
        
        return preview;
    }
    
    /**
     * Functional interface for handling ingredient drops
     */
    @FunctionalInterface
    public interface IngredientDropHandler {
        boolean handleDrop(String ingredientName);
    }
    
    /**
     * Functional interface for handling decoration drops
     */
    @FunctionalInterface
    public interface DecorationDropHandler {
        boolean handleDrop(String decorationName);
    }
}

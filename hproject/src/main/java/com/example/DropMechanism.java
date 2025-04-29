package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DropMechanism extends Application {

    private List<ImageView> ingredients = new ArrayList<>();
    private ImageView bowl;
    private int ingredientsInBowl = 0;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // create bowl
        bowl = new ImageView(loadImage("cake_pan.png"));
        bowl.setFitWidth(bowl.getImage().getWidth() / 8);   // resize bowl
        bowl.setFitHeight(bowl.getImage().getHeight() / 8);
        bowl.setLayoutX(300); // adjust position if needed
        bowl.setLayoutY(300);
        root.getChildren().add(bowl);

        // create ingredients
        String[] ingredientFiles = {"flour.png", "milk.png", "eggs.png", "cocoa.png"};
        for (int i = 0; i < ingredientFiles.length; i++) {
            ImageView ingredient = new ImageView(loadImage(ingredientFiles[i]));

            // resize ingredients
            ingredient.setFitWidth(ingredient.getImage().getWidth() / 8);
            ingredient.setFitHeight(ingredient.getImage().getHeight() / 8);

            // space ingredients more widely
            ingredient.setLayoutX(50 + i * 150);
            ingredient.setLayoutY(50);

            setupDrag(ingredient);
            ingredients.add(ingredient);
            root.getChildren().add(ingredient);
        }

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Drop Mechanism Game");
        primaryStage.show();
    }

    private void setupDrag(ImageView ingredient) {
        final double[] offsetX = new double[1];
        final double[] offsetY = new double[1];

        ingredient.setOnMousePressed(event -> {
            offsetX[0] = event.getSceneX() - ingredient.getLayoutX();
            offsetY[0] = event.getSceneY() - ingredient.getLayoutY();
        });

        ingredient.setOnMouseDragged(event -> {
            ingredient.setLayoutX(event.getSceneX() - offsetX[0]);
            ingredient.setLayoutY(event.getSceneY() - offsetY[0]);
        });

        ingredient.setOnMouseReleased(event -> {
            if (isOverBowl(ingredient)) {
                ingredient.setVisible(false); // hide ingredient
                ingredientsInBowl++;
                if (ingredientsInBowl == ingredients.size()) {
                    bowl.setImage(loadImage("cake_pan_full.png")); // swap bowl
                }
            }
        });
    }

    private boolean isOverBowl(ImageView ingredient) {
        return ingredient.getBoundsInParent().intersects(bowl.getBoundsInParent());
    }

    private Image loadImage(String filename) {
        return new Image(getClass().getResource("/" + filename).toExternalForm());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

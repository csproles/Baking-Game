package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class MixingMechanism extends Application {

    private List<ImageView> ingredients = new ArrayList<>();
    private List<String> droppedIngredients = new ArrayList<>();
    private ImageView bowl;
    private int ingredientsInBowl = 0;
    private Stage primaryStageReference; 

    @Override
    public void start(Stage primaryStage) {
        this.primaryStageReference = primaryStage; 

        Pane root = new Pane();


        ImageView background = new ImageView(loadImage("mix.png"));
        background.setFitWidth(1000);
        background.setFitHeight(600);
        root.getChildren().add(background);

        
        bowl = new ImageView(loadImage("cake_pan.png"));
        bowl.setFitWidth(100);
        bowl.setFitHeight(100);
        bowl.setLayoutX(750);
        bowl.setLayoutY(300);
        root.getChildren().add(bowl);

        
        String[] allIngredients = {"milk.png", "eggs.png", "flour.png", "cocoa.png", "vanilla.png"};
        for (int i = 0; i < allIngredients.length; i++) {
            ImageView ingredient = new ImageView(loadImage(allIngredients[i]));

            ingredient.setFitWidth(ingredient.getImage().getWidth() / 8);
            ingredient.setFitHeight(ingredient.getImage().getHeight() / 8);
            ingredient.setLayoutX(60 + i * 120);
            ingredient.setLayoutY(280);

            setupDrag(ingredient, allIngredients[i]);
            ingredients.add(ingredient);
            root.getChildren().add(ingredient);
        }

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mixing Mechanism");
        primaryStage.show();
    }

    private void setupDrag(ImageView ingredient, String ingredientName) {
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
                ingredient.setVisible(false);
                droppedIngredients.add(ingredientName);
                ingredientsInBowl++;
                if (ingredientsInBowl == 4) {
                    finishMixing();
                }
            }
        });
    }

    private boolean isOverBowl(ImageView ingredient) {
        return ingredient.getBoundsInParent().intersects(bowl.getBoundsInParent());
    }

    private void finishMixing() {
        if (droppedIngredients.contains("vanilla.png")) {
            bowl.setImage(loadImage("pan_full_vanilla.png"));
            BakingMechanism.setFlavor("vanilla");
        } else {
            bowl.setImage(loadImage("cake_pan_full.png"));
            BakingMechanism.setFlavor("cocoa");
        }

        System.out.println("✅ Finished mixing, opening BakingMechanism...");

        Timeline pause = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            try {
                BakingMechanism baking = new BakingMechanism();
                Stage bakingStage = new Stage();
                baking.start(bakingStage);

            
                if (primaryStageReference != null) {
                    primaryStageReference.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        pause.setCycleCount(1);
        pause.play();
    }

    private Image loadImage(String filename) {
        return new Image(getClass().getResource("/" + filename).toExternalForm());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

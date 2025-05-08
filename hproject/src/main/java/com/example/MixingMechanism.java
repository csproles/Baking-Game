package com.example;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MixingMechanism extends Application {

    private Stage primaryStage;
    private Pane root;
    private List<ImageView> ingredients = new ArrayList<>();
    private List<String> droppedIngredients = new ArrayList<>();
    private ImageView bowl;
    private int ingredientsInBowl = 0;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new Pane();

        // ── Background color split ──
        Rectangle topPane = new Rectangle(Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        topPane.setFill(Color.web("#677830"));
        Rectangle bottomPane = new Rectangle(Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        bottomPane.setFill(Color.web("#B1B371"));
        bottomPane.setLayoutY(100);
        root.getChildren().addAll(topPane, bottomPane);

        // ── Menu button ──
        // File menuFile = new File("hproject/src/main/resources/menu.png");
        // Image menuImg = new Image(menuFile.toURI().toString());
        // ImageView menuButton = new ImageView(menuImg);
        // menuButton.setFitWidth(100);
        // menuButton.setFitHeight(100);
        // menuButton.setLayoutX(20);
        // menuButton.setLayoutY(0);
        // menuButton.setOnMouseClicked(e -> {
        //     try {
        //         new LS().start(new Stage());
        //         primaryStage.close();
        //     } catch (Exception ex) {
        //         ex.printStackTrace();
        //     }
        // });
        // menuButton.setOnMouseEntered(e -> menuButton.setStyle("-fx-cursor: hand;"));
        // root.getChildren().add(menuButton);

        // ── Background image ──
        ImageView bgImage = new ImageView(loadImage("mix.png"));
        bgImage.setFitWidth(800);
        bgImage.setFitHeight(500);
        bgImage.setLayoutX(400);
        bgImage.setLayoutY(100);
        root.getChildren().add(bgImage);

        // ── Bowl ──
        bowl = new ImageView(loadImage("cake_pan.png"));
        bowl.setFitWidth(100);
        bowl.setFitHeight(100);
        bowl.setLayoutX(1000);
        bowl.setLayoutY(350);
        root.getChildren().add(bowl);

        // ── Ingredients ──
        String[] allIngredients = {"milk.png", "eggs.png", "flour.png", "cocoa.png", "vanilla.png"};
        for (int i = 0; i < allIngredients.length; i++) {
            ImageView ingredient = new ImageView(loadImage(allIngredients[i]));
            ingredient.setFitWidth(ingredient.getImage().getWidth() / 10);
            ingredient.setFitHeight(ingredient.getImage().getHeight() / 10);
            ingredient.setLayoutX(450 + i * 100);
            ingredient.setLayoutY(340);

            setupDrag(ingredient, allIngredients[i]);
            ingredients.add(ingredient);
            root.getChildren().add(ingredient);
        }

        // ── Order Note Background ──
        File noteFile = new File("hproject/src/main/resources/note.png");
        Image noteImage = new Image(noteFile.toURI().toString());
        ImageView noteImageView = new ImageView(noteImage);
        noteImageView.setFitWidth(600);
        noteImageView.setFitHeight(525);
        noteImageView.setLayoutX(-100);
        noteImageView.setLayoutY(100);
        root.getChildren().add(noteImageView);

        // ── Ordered Cake Image ──
        File selectedCakeFile = new File("hproject/src/main/resources/" + Constants.CAKE_OPTIONS[Constants.CURRENT_ORDER_INDEX]);
        Image selectedCakeImage = new Image(selectedCakeFile.toURI().toString());
        ImageView selectedCakeImageView = new ImageView(selectedCakeImage);
        selectedCakeImageView.setFitWidth(300);
        selectedCakeImageView.setFitHeight(300);
        selectedCakeImageView.setLayoutX(50);
        selectedCakeImageView.setLayoutY(220);
        root.getChildren().add(selectedCakeImageView);

        Scene scene = new Scene(root, Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mixing Mechanism");
        primaryStage.show();
    }

    private void setupDrag(ImageView ingredient, String ingredientName) {
        final double[] offsetX = new double[1];
        final double[] offsetY = new double[1];

        ingredient.setOnMousePressed((MouseEvent e) -> {
            offsetX[0] = e.getSceneX() - ingredient.getLayoutX();
            offsetY[0] = e.getSceneY() - ingredient.getLayoutY();
        });

        ingredient.setOnMouseDragged((MouseEvent e) -> {
            ingredient.setLayoutX(e.getSceneX() - offsetX[0]);
            ingredient.setLayoutY(e.getSceneY() - offsetY[0]);
        });

        ingredient.setOnMouseReleased((MouseEvent e) -> {
            if (isOverBowl(ingredient)) {
                if ((ingredientName.equals("vanilla.png") && droppedIngredients.contains("cocoa.png")) ||
                        (ingredientName.equals("cocoa.png") && droppedIngredients.contains("vanilla.png"))) {
                    System.out.println("⚠ Cannot mix both vanilla and cocoa.");
                    ingredient.setLayoutX(60 + ingredients.indexOf(ingredient) * 120);
                    ingredient.setLayoutY(280);
                    return;
                }

                if (!droppedIngredients.contains(ingredientName)) {
                    droppedIngredients.add(ingredientName);
                    ingredientsInBowl++;
                    ingredient.setVisible(false);
                }

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
        boolean hasVanilla = droppedIngredients.contains("vanilla.png");
        boolean hasCocoa = droppedIngredients.contains("cocoa.png");

        if (hasVanilla &&
                droppedIngredients.contains("milk.png") &&
                droppedIngredients.contains("eggs.png") &&
                droppedIngredients.contains("flour.png")) {

            bowl.setImage(loadImage("pan_full_vanilla.png"));
            BakingMechanism.setFlavor("vanilla");
            Constants.CAKE_TYPE_VANILLA = true;

        } else if (hasCocoa &&
                droppedIngredients.contains("milk.png") &&
                droppedIngredients.contains("eggs.png") &&
                droppedIngredients.contains("flour.png")) {

            bowl.setImage(loadImage("cake_pan_full.png"));
            BakingMechanism.setFlavor("cocoa");
            Constants.CAKE_TYPE_CHOCOLATE = true;

        } else {
            System.out.println("Missing ingredients.");
        }

        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(e -> {
            System.out.println("✅ Finished mixing.");
            Constants.CAKE_MIXED = true;
            primaryStage.close();
        });
        delay.play();
    }

    private Image loadImage(String filename) {
        return new Image(getClass().getResource("/" + filename).toExternalForm());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

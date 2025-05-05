package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DecoratingMechanism extends Application {

    private static String flavor = "cocoa"; // default
    private static boolean launchedFromBaking = false;

    private ImageView cakeView;
    private String currentCake;
    private ImageView boxView;

    private final String[] decorations = {
        "icing_strawberry.png", 
        "icing_chocolate.png", 
        "strawberry.png", 
        "sprinkles.png"
    };

    public static void setFlavor(String selectedFlavor) {
        flavor = selectedFlavor;
    }

    public static void startFromBaking() {
        launchedFromBaking = true;
        new Thread(() -> Application.launch(DecoratingMechanism.class)).start();
    }

    @Override
public void start(Stage stage) {
    Pane centerPane = new Pane();

    Rectangle background = new Rectangle(1000, 600);
    background.setFill(Color.web("#7C8A91")); // match background color
    centerPane.getChildren().add(background);

    // Background image
    ImageView bgImage = new ImageView(load("decorate.png"));
    bgImage.setFitWidth(900);
    bgImage.setFitHeight(540);
    bgImage.setLayoutX(50);
    centerPane.getChildren().add(bgImage);

    // Cake box
    boxView = new ImageView(load("box.png"));
    boxView.setLayoutX(650);
    boxView.setLayoutY(240);
    boxView.setFitWidth(220);
    boxView.setFitHeight(220);
    centerPane.getChildren().add(boxView);

    // Initial cake
    currentCake = "vanilla".equals(flavor) ? "naked_vanilla.png" : "naked_chocolate.png";
    cakeView = new ImageView(load(currentCake));
    cakeView.setLayoutX(450);
    cakeView.setLayoutY(240);
    cakeView.setFitWidth(200);
    cakeView.setFitHeight(200);
    centerPane.getChildren().add(cakeView);

    // Decorations
    for (int i = 0; i < decorations.length; i++) {
        String name = decorations[i];
        ImageView item = new ImageView(load(name));
        item.setFitWidth(125);
        item.setFitHeight(125);
        double startX = 100 + (i * 80);
        double startY = 280;
        item.setLayoutX(startX);
        item.setLayoutY(startY);

        setupDrag(item, name, startX, startY, centerPane);
        centerPane.getChildren().add(item);
    }

    // Draggable cake
    setupDrag(cakeView, currentCake, cakeView.getLayoutX(), cakeView.getLayoutY(), centerPane);

    // Map Selection button in a top pane
    javafx.scene.control.Button mapButton = new javafx.scene.control.Button("Map Selection");
    mapButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #D4BFA0;");
    mapButton.setOnAction(e -> {
        // Replace with your actual map selection logic
        stage.close();
    });

    Pane topPane = new Pane(mapButton);
    topPane.setPrefHeight(100);
    mapButton.setLayoutX(50);
    mapButton.setLayoutY(30);
    topPane.setStyle("-fx-background-color: #5D6C72;");

    // Use a BorderPane to stack top and center panes
    javafx.scene.layout.BorderPane root = new javafx.scene.layout.BorderPane();
    root.setTop(topPane);
    root.setCenter(centerPane);

    Scene scene = new Scene(root, 1000, 600);
    stage.setTitle("Decorating Mechanism");
    stage.setScene(scene);
    stage.show();
}

    private void setupDrag(ImageView item, String name, double originalX, double originalY, Pane root) {
        final double[] offsetX = new double[1];
        final double[] offsetY = new double[1];

        item.setOnMousePressed(e -> {
            offsetX[0] = e.getSceneX() - item.getLayoutX();
            offsetY[0] = e.getSceneY() - item.getLayoutY();
        });

        item.setOnMouseDragged(e -> {
            item.setLayoutX(e.getSceneX() - offsetX[0]);
            item.setLayoutY(e.getSceneY() - offsetY[0]);
        });

        item.setOnMouseReleased(e -> {
            boolean overCake = item.getBoundsInParent().intersects(cakeView.getBoundsInParent());
            boolean overBox = item.getBoundsInParent().intersects(boxView.getBoundsInParent());

            if (item != cakeView && overCake) {
                String nextCake = getNextCakeImage(currentCake, name);
                if (nextCake != null) {
                    currentCake = nextCake;
                    cakeView.setImage(load(currentCake));
                    item.setVisible(false);
                    // refresh drag logic
                    setupDrag(cakeView, currentCake, cakeView.getLayoutX(), cakeView.getLayoutY(), root);
                } else {
                    item.setLayoutX(originalX);
                    item.setLayoutY(originalY);
                }
            } else if (item == cakeView && overBox) {
                String boxImage = getBoxReplacement(currentCake);
                if (boxImage != null) {
                    boxView.setImage(load(boxImage));
                    cakeView.setVisible(false);
                } else {
                    item.setLayoutX(originalX);
                    item.setLayoutY(originalY);
                }
            } else {
                item.setLayoutX(originalX);
                item.setLayoutY(originalY);
            }
        });
    }

    private Image load(String name) {
        return new Image(getClass().getResource("/" + name).toExternalForm());
    }

    private String getNextCakeImage(String current, String dropped) {
        switch (current) {
            case "naked_vanilla.png":
            case "naked_chocolate.png":
                if (dropped.equals("icing_strawberry.png")) return "decorate_strawberry.png";
                if (dropped.equals("icing_chocolate.png")) return "decorated_chocolate.png";
                break;
            case "decorate_strawberry.png":
                if (dropped.equals("strawberry.png")) return "decorate_strawberry_strawberry.png";
                if (dropped.equals("sprinkles.png")) return "decorate_strawberry_sprinkles.png";
                break;
            case "decorated_chocolate.png":
                if (dropped.equals("strawberry.png")) return "decorate_chocolate_strawberry.png";
                if (dropped.equals("sprinkles.png")) return "decorated_chocolate_sprinkles.png";
                break;
        }
        return null;
    }

    private String getBoxReplacement(String decoratedCake) {
        switch (decoratedCake) {
            case "decorate_chocolate_strawberry.png": return "box_chocolate_strawberry.png";
            case "decorated_chocolate_sprinkles.png": return "box_chocolate_sprinkles.png";
            case "decorate_strawberry_strawberry.png": return "bow_starwberry_strawberry.png";
            case "decorate_strawberry_sprinkles.png": return "box_strawberry_sprinkles.png";
        }
        return null;
    }

    public static void main(String[] args) {
        if (!launchedFromBaking) {
            flavor = "vanilla"; // test fallback
            launch(args);
        }
    }
}

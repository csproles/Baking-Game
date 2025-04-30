package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
        Pane root = new Pane();

        // background
        ImageView background = new ImageView(load("decorate.png"));
        background.setFitWidth(1000);
        background.setFitHeight(600);
        root.getChildren().add(background);

                // add box placeholder
        boxView = new ImageView(load("box.png"));
        boxView.setLayoutX(700);
        boxView.setLayoutY(260);
        boxView.setFitWidth(240);
        boxView.setFitHeight(240);
        root.getChildren().add(boxView);

        // initial cake
        currentCake = "vanilla".equals(flavor) ? "naked_vanilla.png" : "naked_chocolate.png";
        cakeView = new ImageView(load(currentCake));
        cakeView.setLayoutX(500);
        cakeView.setLayoutY(275);
        cakeView.setFitWidth(200);
        cakeView.setFitHeight(200);
        root.getChildren().add(cakeView);

        // decorations
        for (int i = 0; i < decorations.length; i++) {
            String name = decorations[i];
            ImageView item = new ImageView(load(name));
            item.setFitWidth(150);
            item.setFitHeight(150);
            double startX = 50 + (i * 100);
            double startY = 300;
            item.setLayoutX(startX);
            item.setLayoutY(startY);

            setupDrag(item, name, startX, startY, root);
            root.getChildren().add(item);
        }

        // make cake draggable (for final drop into box)
        setupDrag(cakeView, currentCake, cakeView.getLayoutX(), cakeView.getLayoutY(), root);

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
            case "decorate_strawberry_strawberry.png": return "box_strawberry_strawberry.png";
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

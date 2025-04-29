package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DecoratingMechanism extends Application {

    private static String flavor = "cocoa";
    private static boolean launchedFromBaking = false;

    private ImageView cakeView;
    private String currentCake;

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
        Platform.runLater(() -> {
            try {
                new DecoratingMechanism().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();

        ImageView background = new ImageView(load("decorate.png"));
        background.setFitWidth(1000);
        background.setFitHeight(600);
        root.getChildren().add(background);

        currentCake = "vanilla".equals(flavor) ? "naked_vanilla.png" : "naked_chocolate.png";
        cakeView = new ImageView(load(currentCake));
        cakeView.setLayoutX(600);
        cakeView.setLayoutY(275);
        cakeView.setFitWidth(200);
        cakeView.setFitHeight(200);
        root.getChildren().add(cakeView);

        for (int i = 0; i < decorations.length; i++) {
            String name = decorations[i];
            ImageView item = new ImageView(load(name));
            item.setFitWidth(150);
            item.setFitHeight(150);
            double startX = 50 + (i * 100);
            double startY = 300;
            item.setLayoutX(startX);
            item.setLayoutY(startY);

            setupDrag(item, name, startX, startY);
            root.getChildren().add(item);
        }

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Decorating Mechanism");
        stage.setScene(scene);
        stage.show();
    }

    private void setupDrag(ImageView item, String name, double originalX, double originalY) {
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

            if (overCake) {
                String nextCake = getNextCakeImage(currentCake, name);
                if (nextCake != null) {
                    currentCake = nextCake;
                    cakeView.setImage(load(currentCake));
                    item.setVisible(false);
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

    public static void main(String[] args) {
        if (!launchedFromBaking) {
            flavor = "vanilla";
            launch(args);
        }
    }
}

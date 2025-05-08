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

public class DecoratingMechanism extends Application {

    private static boolean launchedFromBaking = false;

    private ImageView cakeView;
    private String currentCake;
    private ImageView boxView;
    private Stage primaryStage;

    private final String[] decorations = {
        "icing_strawberry.png",
        "icing_chocolate.png",
        "strawberry.png",
        "sprinkles.png"
    };

    public static void startFromBaking() {
        launchedFromBaking = true;
        new Thread(() -> Application.launch(DecoratingMechanism.class)).start();
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        Pane root = new Pane();

        // ── Background color split ──
        Rectangle topPane = new Rectangle(Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        topPane.setFill(Color.web("#677830"));
        Rectangle bottomPane = new Rectangle(Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        bottomPane.setFill(Color.web("#B1B371"));
        bottomPane.setLayoutY(100);
        root.getChildren().addAll(topPane, bottomPane);

        // ── Menu button ──
        File menuFile = new File("hproject/src/main/resources/menu.png");
        ImageView menuButton = new ImageView(new Image(menuFile.toURI().toString()));
        menuButton.setFitWidth(100);
        menuButton.setFitHeight(100);
        menuButton.setLayoutX(20);
        menuButton.setLayoutY(0);
        menuButton.setOnMouseClicked(e -> {
            try {
                new LS().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        menuButton.setOnMouseEntered(e -> menuButton.setStyle("-fx-cursor: hand;"));
        root.getChildren().add(menuButton);

        // ── Background image ──
        ImageView bgImage = new ImageView(load("decorate.png"));
        bgImage.setFitWidth(800);
        bgImage.setFitHeight(500);
        bgImage.setLayoutX(400);
        bgImage.setLayoutY(100);
        root.getChildren().add(bgImage);

        // ── Cake box ──
        boxView = new ImageView(load("box.png"));
        boxView.setLayoutX(950);
        boxView.setLayoutY(320);
        boxView.setFitWidth(200);
        boxView.setFitHeight(200);
        root.getChildren().add(boxView);

        // ── Determine cake flavor from Constants ──
        String flavor = Constants.CAKE_TYPE_VANILLA ? "vanilla" : "cocoa";
        currentCake = "vanilla".equals(flavor) ? "naked_vanilla.png" : "naked_chocolate.png";
        cakeView = new ImageView(load(currentCake));
        cakeView.setLayoutX(800);
        cakeView.setLayoutY(350);
        cakeView.setFitWidth(150);
        cakeView.setFitHeight(150);
        root.getChildren().add(cakeView);

        // ── Decorations ──
        for (int i = 0; i < decorations.length; i++) {
            String name = decorations[i];
            ImageView item = new ImageView(load(name));
            item.setFitWidth(100);
            item.setFitHeight(100);
            double startX = 450 + (i * 80);
            double startY = 380;
            item.setLayoutX(startX);
            item.setLayoutY(startY);
            setupDrag(item, name, startX, startY, root);
            root.getChildren().add(item);
        }

        // ── Order Note ──
        File noteFile = new File("hproject/src/main/resources/note.png");
        ImageView noteImageView = new ImageView(new Image(noteFile.toURI().toString()));
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

        // ── Enable drag on initial cake ──
        setupDrag(cakeView, currentCake, cakeView.getLayoutX(), cakeView.getLayoutY(), root);

        Scene scene = new Scene(root, Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
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
                    Constants.CAKE_DECORATED = true;
                    String expected = Constants.CAKE_OPTIONS[Constants.CURRENT_ORDER_INDEX];
                    Constants.CORRECT_CAKE = expected.contains(currentCake.replace(".png", ""));

                    // Delay closing after box image update
                    PauseTransition delay = new PauseTransition(Duration.seconds(1.2));
                    delay.setOnFinished(evt -> primaryStage.close());
                    delay.play();
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
            launch(args);
        }
    }
}

package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BakingMechanism extends Application {

    private static String flavor; // must be set externally before launch
    private ImageView cakePan;
    private ImageView oven;
    private ProgressBar progressBar;
    private Timeline bakingTimeline;

    public static void setFlavor(String selectedFlavor) {
        flavor = selectedFlavor;
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // --- add background ---
        ImageView background = new ImageView(loadImage("bake.png"));
        background.setFitWidth(1000);
        background.setFitHeight(600);
        root.getChildren().add(background);

        // --- load oven ---
        oven = new ImageView(loadImage("oven.png"));
        oven.setFitWidth(700);
        oven.setFitHeight(500);
        oven.setLayoutX(152);
        oven.setLayoutY(130);
        root.getChildren().add(oven);

        // --- load cake pan based on flavor ---
        if ("vanilla".equals(flavor)) {
            cakePan = new ImageView(loadImage("pan_full_vanilla.png"));
        } else if ("cocoa".equals(flavor)) {
            cakePan = new ImageView(loadImage("cake_pan_full.png"));
        } else {
            System.out.println("⚠️ Flavor not set properly. Defaulting to cocoa.");
            cakePan = new ImageView(loadImage("cake_pan_full.png"));
        }
        cakePan.setFitWidth(100);
        cakePan.setFitHeight(100);
        cakePan.setLayoutX(100);
        cakePan.setLayoutY(300);
        root.getChildren().add(cakePan);

        setupDrag(cakePan, root);

        // --- progress bar ---
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(350);
        progressBar.setLayoutX(325);
        progressBar.setLayoutY(525);
        progressBar.setVisible(false);
        progressBar.setStyle(
    "-fx-accent: saddlebrown;" +                    // fill color (progress)
    "-fx-control-inner-background: black;"    // background track color
);
        root.getChildren().add(progressBar);

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Baking Mechanism");
        primaryStage.show();
    }

    private void setupDrag(ImageView item, Pane root) {
        final double[] offsetX = new double[1];
        final double[] offsetY = new double[1];

        item.setOnMousePressed(event -> {
            offsetX[0] = event.getSceneX() - item.getLayoutX();
            offsetY[0] = event.getSceneY() - item.getLayoutY();
        });

        item.setOnMouseDragged(event -> {
            item.setLayoutX(event.getSceneX() - offsetX[0]);
            item.setLayoutY(event.getSceneY() - offsetY[0]);
        });

        item.setOnMouseReleased(event -> {
            if (item.getBoundsInParent().intersects(oven.getBoundsInParent())) {
                startBaking(root);
            }
        });
    }

    private void startBaking(Pane root) {
        cakePan.setVisible(false);
        progressBar.setVisible(true);

        bakingTimeline = new Timeline(
            new KeyFrame(Duration.seconds(0.1), event -> {
                progressBar.setProgress(progressBar.getProgress() + 0.01);
                if (progressBar.getProgress() >= 1.0) {
                    finishBaking(root);
                    bakingTimeline.stop();
                }
            })
        );
        bakingTimeline.setCycleCount(Timeline.INDEFINITE);
        bakingTimeline.play();
    }

    private void finishBaking(Pane root) {
        // --- show baked cake ---
        ImageView bakedCake;
        if ("vanilla".equals(flavor)) {
            bakedCake = new ImageView(loadImage("pan_vanilla_baked.png"));
        } else {
            bakedCake = new ImageView(loadImage("pan_chocolate_baked.png"));
        }
        bakedCake.setFitWidth(120);
        bakedCake.setFitHeight(120);
        bakedCake.setLayoutX(750);
        bakedCake.setLayoutY(250);
        root.getChildren().add(bakedCake);
    }

    private Image loadImage(String filename) {
        return new Image(getClass().getResource("/" + filename).toExternalForm());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

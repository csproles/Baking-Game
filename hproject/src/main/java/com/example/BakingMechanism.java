package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BakingMechanism extends Application {

    private static String flavor = "cocoa"; // default
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

        ImageView background = new ImageView(loadImage("bake.png"));
        background.setFitWidth(1000);
        background.setFitHeight(600);
        root.getChildren().add(background);

        oven = new ImageView(loadImage("oven.png"));
        oven.setFitWidth(700);
        oven.setFitHeight(500);
        oven.setLayoutX(152);
        oven.setLayoutY(130);
        root.getChildren().add(oven);

        cakePan = new ImageView(loadImage(
            "vanilla".equals(flavor) ? "pan_full_vanilla.png" : "cake_pan_full.png"
        ));
        cakePan.setFitWidth(100);
        cakePan.setFitHeight(100);
        cakePan.setLayoutX(100);
        cakePan.setLayoutY(300);
        root.getChildren().add(cakePan);

        setupDrag(cakePan);

        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(350);
        progressBar.setLayoutX(325);
        progressBar.setLayoutY(525);
        progressBar.setVisible(false);
        progressBar.setStyle("-fx-accent: saddlebrown; -fx-control-inner-background: black;");
        root.getChildren().add(progressBar);

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Baking Mechanism");
        primaryStage.show();
    }

    private void setupDrag(ImageView item) {
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
            if (item.getBoundsInParent().intersects(oven.getBoundsInParent())) {
                startBaking();
            }
        });
    }

    private void startBaking() {
        cakePan.setVisible(false);
        progressBar.setVisible(true);

        bakingTimeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            progressBar.setProgress(progressBar.getProgress() + 0.01);
            if (progressBar.getProgress() >= 1.0) {
                bakingTimeline.stop();
                finishBaking();
            }
        }));
        bakingTimeline.setCycleCount(Timeline.INDEFINITE);
        bakingTimeline.play();
    }

    private void finishBaking() {
        Pane root = (Pane) progressBar.getParent();

        ImageView bakedCake = new ImageView(loadImage(
            "vanilla".equals(flavor) ? "pan_vanilla_baked.png" : "pan_chocolate_baked.png"
        ));
        bakedCake.setFitWidth(120);
        bakedCake.setFitHeight(120);
        bakedCake.setLayoutX(750);
        bakedCake.setLayoutY(300);
        root.getChildren().add(bakedCake);

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> {
            DecoratingMechanism.setFlavor(flavor);
            DecoratingMechanism.startFromBaking();
            ((Stage) progressBar.getScene().getWindow()).close();
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

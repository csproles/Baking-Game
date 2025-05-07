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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class BakingMechanism extends Application {

    private static String flavor = "cocoa"; // default
    private ImageView cakePan;
    private ImageView oven;
    private ProgressBar progressBar;
    private Timeline bakingTimeline;
    private Pane root;
    private Stage primaryStage;

    public static void setFlavor(String selectedFlavor) {
        flavor = selectedFlavor;
    }

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
        File menuFile = new File("hproject/src/main/resources/menu.png");
        Image menuImg = new Image(menuFile.toURI().toString());
        ImageView menuButton = new ImageView(menuImg);
        menuButton.setFitWidth(100);
        menuButton.setFitHeight(100);
        menuButton.setLayoutX(20);
        menuButton.setLayoutY(0);
        menuButton.setOnMouseClicked(e -> {
            try {
                new LS().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        menuButton.setOnMouseEntered(e -> menuButton.setStyle("-fx-cursor: hand;"));
        root.getChildren().add(menuButton);

        // ── Station background ──
        ImageView bgImage = new ImageView(loadImage("bake.png"));
        bgImage.setFitWidth(800);
        bgImage.setFitHeight(500);
        bgImage.setLayoutX(400);
        bgImage.setLayoutY(100);
        root.getChildren().add(bgImage);

        // ── Oven ──
        oven = new ImageView(loadImage("oven.png"));
        oven.setFitWidth(600);
        oven.setFitHeight(450);
        oven.setLayoutX(500);
        oven.setLayoutY(200);
        root.getChildren().add(oven);

        // ── Cake Pan ──
        cakePan = new ImageView(loadImage(
                "vanilla".equals(flavor) ? "pan_full_vanilla.png" : "cake_pan_full.png"
        ));
        cakePan.setFitWidth(100);
        cakePan.setFitHeight(100);
        cakePan.setLayoutX(500);
        cakePan.setLayoutY(340);
        setupDrag(cakePan);
        root.getChildren().add(cakePan);

        // ── Progress Bar ──
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(300);
        progressBar.setLayoutX(650);
        progressBar.setLayoutY(555);
        progressBar.setVisible(false);
        progressBar.setStyle("-fx-accent: saddlebrown; -fx-control-inner-background: black;");
        root.getChildren().add(progressBar);

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

        bakingTimeline = new Timeline(new KeyFrame(Duration.seconds(0.01), e -> {
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
        ImageView bakedCake = new ImageView(loadImage(
                "vanilla".equals(flavor) ? "pan_vanilla_baked.png" : "pan_chocolate_baked.png"
        ));
        bakedCake.setFitWidth(100);
        bakedCake.setFitHeight(100);
        bakedCake.setLayoutX(1000);
        bakedCake.setLayoutY(350);
        root.getChildren().add(bakedCake);

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> {
            System.out.println("✅ Baking complete.");
            Constants.CAKE_BAKED = true;
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

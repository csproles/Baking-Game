package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

public class OrderStation extends Application {

    @Override
    public void start(Stage stage) {
        // ── Top Panel ───────────────────────────────
        Pane topPanel = new Pane();
        topPanel.setPrefHeight(150);
        topPanel.setPrefWidth(1000);
        topPanel.setBackground(new Background(new BackgroundFill(Color.web("#556565"), null, null)));

        Button mapButton = new Button("Map Selection");
        mapButton.setLayoutX(20);
        mapButton.setLayoutY(20);
        mapButton.setPrefWidth(200);
        mapButton.setPrefHeight(50);
        mapButton.setStyle(
            "-fx-background-color: #CBB4A0;" +
            "-fx-border-color: #3E575C;" +
            "-fx-border-width: 2px;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #1E1E1E;"
        );
        mapButton.setOnAction(e -> {
            try {
                new LS().start(new Stage()); // launch LS.java in a new window
                stage.close();               // optionally close current window
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        topPanel.getChildren().add(mapButton);

        // ── Original Content Pane ───────────────────
        Pane mainPane = new Pane();

        // Base background color for fallback
        Rectangle bgColor = new Rectangle(1000, 600);
        bgColor.setFill(Color.web("#7C8A91"));
        mainPane.getChildren().add(bgColor);

        // Background image
        ImageView bgImage = new ImageView(load("order_bg.png"));
        bgImage.setFitWidth(1000);
        bgImage.setFitHeight(600);
        mainPane.getChildren().add(bgImage);

        // Cake image
        Constants.CURRENT_ORDER_INDEX = new Random().nextInt(Constants.CAKE_OPTIONS.length);
        ImageView cake = new ImageView(load(Constants.CAKE_OPTIONS[Constants.CURRENT_ORDER_INDEX]));
        cake.setFitWidth(200);
        cake.setFitHeight(200);
        cake.setLayoutX(400);
        cake.setLayoutY(350);
        mainPane.getChildren().add(cake);

        // GOT IT button
        Button closeButton = new Button("GOT IT !");
        closeButton.setLayoutX(850);
        closeButton.setLayoutY(500);
        closeButton.setPrefWidth(120);
        closeButton.setPrefHeight(40);
        closeButton.setStyle(
            "-fx-background-color: #CBB4A0;" +
            "-fx-border-color: #3E575C;" +
            "-fx-border-width: 2px;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #1E1E1E;"
        );
        closeButton.setOnAction(e -> stage.close());
        mainPane.getChildren().add(closeButton);

        // ── Combine in VBox ─────────────────────────
        VBox root = new VBox();
        root.getChildren().addAll(topPanel, mainPane);

        Scene scene = new Scene(root, 1000, 750); // 150 + 600 = 750
        stage.setTitle("Order Station");
        stage.setScene(scene);
        stage.show();
    }

    private Image load(String name) {
        return new Image(getClass().getResource("/" + name).toExternalForm());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

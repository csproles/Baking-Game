package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;

public class OrderStation extends Application {

    private Pane root;

    @Override
    public void start(Stage stage) {
        root = new Pane();

        // ── Background color split ──
        Rectangle topPane = new Rectangle(Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        topPane.setFill(Color.web("#677830"));
        Rectangle bottomPane = new Rectangle(Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        bottomPane.setFill(Color.web("#B1B371"));
        bottomPane.setLayoutY(100);
        root.getChildren().addAll(topPane, bottomPane);

        // ── Menu icon (clickable) ──
        File menuFile = new File("hproject\\src\\main\\resources\\menu.png");
        Image menuImg = new Image(menuFile.toURI().toString());
        ImageView menuButton = new ImageView(menuImg);
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
        ImageView bgImage = new ImageView(load("order_bg2.png"));
        bgImage.setFitWidth(800);
        bgImage.setFitHeight(500);
        bgImage.setLayoutY(120);
        bgImage.setLayoutX(200);
        root.getChildren().add(bgImage);

        // ── Cake image ──
        Constants.CURRENT_ORDER_INDEX = new Random().nextInt(Constants.CAKE_OPTIONS.length - 1) + 1;
        ImageView cake = new ImageView(load(Constants.CAKE_OPTIONS[Constants.CURRENT_ORDER_INDEX]));
        cake.setFitWidth(200);
        cake.setFitHeight(200);
        cake.setLayoutX(500);
        cake.setLayoutY(390);
        root.getChildren().add(cake);

        // ── GOT IT button ──
        File gotItFile = new File("hproject/src/main/resources/gotit.png");
        Image gotItImg = new Image(gotItFile.toURI().toString());
        ImageView gotItButton = new ImageView(gotItImg);
        
        // Set size and position
        gotItButton.setFitWidth(120);
        gotItButton.setFitHeight(120);
        gotItButton.setLayoutX(1100);
        gotItButton.setLayoutY(520);
        
        // Make it clickable
        gotItButton.setOnMouseClicked(e -> {Constants.HAS_ORDERED = true; stage.close();});
        gotItButton.setOnMouseEntered(e -> gotItButton.setStyle("-fx-cursor: hand;"));
        
        root.getChildren().add(gotItButton);
        // ── Scene setup ──
        Scene scene = new Scene(root, Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
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

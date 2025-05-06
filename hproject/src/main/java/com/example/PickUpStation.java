package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;

public class PickUpStation extends Application {

    private ImageView customerView;
    private ImageView boxView;
    private Button nextOrderButton;
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
            System.out.println("MENU clicked.");
            stage.close(); // or open LS.java if desired
        });
        menuButton.setOnMouseEntered(e -> menuButton.setStyle("-fx-cursor: hand;"));
        root.getChildren().add(menuButton);

        // ── Pickup station background ──
        ImageView pickupBackground = new ImageView(load("PickUpStation.png"));
        pickupBackground.setFitWidth(1000);
        pickupBackground.setFitHeight(500);
        pickupBackground.setLayoutY(100);
        pickupBackground.setLayoutX(100);
        root.getChildren().add(pickupBackground);

        // ── Customer image ──
        customerView = new ImageView(load("customer_1.png"));
        customerView.setFitWidth(300);
        customerView.setFitHeight(250);
        customerView.setLayoutX(600);
        customerView.setLayoutY(158);
        root.getChildren().add(customerView);

        // ── Draggable cake box ──
        boxView = new ImageView(load("box_closed.png"));
        boxView.setFitWidth(120);
        boxView.setFitHeight(120);
        boxView.setLayoutX(400);
        boxView.setLayoutY(340);
        setupDrag(boxView);
        root.getChildren().add(boxView);

        // ── NEXT ORDER button (appears after delivery) ──
        nextOrderButton = new Button("NEXT ORDER");
        nextOrderButton.setLayoutX(1100);
        nextOrderButton.setLayoutY(550);
        nextOrderButton.setPrefWidth(120);
        nextOrderButton.setPrefHeight(40);
        nextOrderButton.setStyle(
            "-fx-background-color: #CBB4A0;" +
            "-fx-border-color: #3E575C;" +
            "-fx-border-width: 2px;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #1E1E1E;"
        );
        nextOrderButton.setOnAction(e -> {
            try {
                new Level1().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // ── Final scene setup ──
        Scene scene = new Scene(root, Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        stage.setTitle("Pick Up Station");
        stage.setScene(scene);
        stage.show();
    }

    private void setupDrag(ImageView item) {
        final double[] offsetX = new double[1];
        final double[] offsetY = new double[1];

        item.setOnMousePressed((MouseEvent e) -> {
            offsetX[0] = e.getSceneX() - item.getLayoutX();
            offsetY[0] = e.getSceneY() - item.getLayoutY();
        });

        item.setOnMouseDragged((MouseEvent e) -> {
            item.setLayoutX(e.getSceneX() - offsetX[0]);
            item.setLayoutY(e.getSceneY() - offsetY[0]);
        });

        item.setOnMouseReleased((MouseEvent e) -> {
            if (item.getBoundsInParent().intersects(customerView.getBoundsInParent())) {
                customerView.setImage(load("customer_2.png"));
                item.setVisible(false);
                if (!root.getChildren().contains(nextOrderButton)) {
                    root.getChildren().add(nextOrderButton);
                }
            }
        });
    }

    private Image load(String name) {
        return new Image(getClass().getResource("/" + name).toExternalForm());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

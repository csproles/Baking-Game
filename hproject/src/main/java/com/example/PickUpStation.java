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

public class PickUpStation extends Application {

    private ImageView customerView;
    private ImageView boxView;
    private Button nextOrderButton;
    private Pane root;

    @Override
    public void start(Stage stage) {
        root = new Pane();

        // Full scene background
        Rectangle background = new Rectangle(1000, 600);
        background.setFill(Color.web("#7C8A91"));  // same grey-blue background
        root.getChildren().add(background);

        // Top pane (header bar)
        Rectangle topPane = new Rectangle(1000, 150);
        topPane.setFill(Color.web("#556565"));  // same top panel color
        root.getChildren().add(topPane);

        // "Map Selection" button in top-left
        Button mapButton = new Button("Map Selection");
        mapButton.setLayoutX(25);
        mapButton.setLayoutY(30);
        mapButton.setPrefWidth(210);
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
            System.out.println("Map selection clicked!");
            // insert logic to open map selector scene
        });
        root.getChildren().add(mapButton);

        // Add pickup background under the top panel
        ImageView pickupBackground = new ImageView(load("PickUpStation.png"));
        pickupBackground.setFitWidth(1000);
        pickupBackground.setFitHeight(450);
        pickupBackground.setLayoutY(150);
        root.getChildren().add(pickupBackground);

        // Customer image (initial state)
        customerView = new ImageView(load("customer_1.png"));
        customerView.setFitWidth(250);
        customerView.setFitHeight(250);
        customerView.setLayoutX(600);
        customerView.setLayoutY(178);
        root.getChildren().add(customerView);

        // Box image (draggable)
        boxView = new ImageView(load("box_closed.png"));
        boxView.setFitWidth(120);
        boxView.setFitHeight(120);
        boxView.setLayoutX(200);
        boxView.setLayoutY(360);
        setupDrag(boxView);
        root.getChildren().add(boxView);

        // NEXT ORDER button (hidden at first)
        nextOrderButton = new Button("NEXT ORDER");
        nextOrderButton.setLayoutX(850);
        nextOrderButton.setLayoutY(540);
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
        nextOrderButton.setOnAction(e -> stage.close());

        Scene scene = new Scene(root, 1000, 600);
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
                    root.getChildren().add(nextOrderButton);  // Show only after successful drop
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

package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;

public class GameOverScreen extends Application {

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();

        // ── Background color (only one pane) ──
        Rectangle background = new Rectangle(Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        background.setFill(Color.web("#B1B371"));
        root.getChildren().add(background);

        // ── Menu Button ──
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

        // ── Play Again Button ──
        File playAgainFile = new File("hproject/src/main/resources/play_again.png");
        ImageView playAgainButton = new ImageView(new Image(playAgainFile.toURI().toString()));
        playAgainButton.setFitWidth(200);
        playAgainButton.setFitHeight(150);
        playAgainButton.setLayoutX(525);
        playAgainButton.setLayoutY(450);
        playAgainButton.setOnMouseClicked(e -> {
            try {
                new Level1().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        playAgainButton.setOnMouseEntered(e -> playAgainButton.setStyle("-fx-cursor: hand;"));
        root.getChildren().add(playAgainButton);

        // ── Game Over Image ──
        root.getChildren().add(centeredImage("game_over.png", 800, 300, 220, 50));

        // ── Your Score Image ──
        root.getChildren().add(centeredImage("your_score.png", 300, 125, 300, 350));

        // ── Empty Score Display ──
        root.getChildren().add(centeredImage("empty_score.png", 300, 125, 650, 350));

        // ── Scene Setup ──
        Scene scene = new Scene(root, Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Game Over");
        stage.show();
    }

    private ImageView centeredImage(String fileName, double width, double height, double x, double y) {
        File file = new File("hproject/src/main/resources/" + fileName);
        ImageView view = new ImageView(new Image(file.toURI().toString()));
        view.setFitWidth(width);
        view.setFitHeight(height);
        view.setLayoutX(x);
        view.setLayoutY(y);
        return view;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

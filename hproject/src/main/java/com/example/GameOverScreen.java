package com.example;

import com.example.util.ScoreTracker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;

public class GameOverScreen extends Application {
    
    // Add fields to store the score
    private int finalScore = 0;
    
    // Constructor that accepts a score parameter
    public GameOverScreen() {
        // Default constructor
    }
    
    // Constructor with score parameter
    public GameOverScreen(int score) {
        this.finalScore = score;
    }

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();

        // Background 
        Rectangle background = new Rectangle(Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        background.setFill(Color.web("#B1B371"));
        root.getChildren().add(background);

        // Menu Button
        File menuFile = new File("hproject/src/main/resources/menu.png");
        ImageView menuButton = new ImageView(new Image(menuFile.toURI().toString()));
        menuButton.setFitWidth(100);
        menuButton.setFitHeight(100);
        menuButton.setLayoutX(20);
        menuButton.setLayoutY(0);
        menuButton.setOnMouseEntered(e -> menuButton.setStyle("-fx-cursor: hand;"));
        menuButton.setOnMouseClicked(e -> {
            try {
                new LS().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        root.getChildren().add(menuButton);

        // Play Again Button
        ImageView playAgainButton = new ImageView(new Image("file:hproject/src/main/resources/play_again.png"));
        playAgainButton.setFitWidth(200);
        playAgainButton.setFitHeight(150);
        playAgainButton.setLayoutX(530);
        playAgainButton.setLayoutY(450);
        playAgainButton.setPickOnBounds(true); // makes transparent parts clickable

        playAgainButton.setOnMouseClicked(e -> {
            System.out.println("Play Again button clicked!");

            try {
                // ✅ Reset all game state
                Constants.HAS_ORDERED = false;
                Constants.CAKE_MIXED = false;
                Constants.CAKE_BAKED = false;
                Constants.CAKE_DECORATED = false;
                Constants.CAKE_TYPE_VANILLA = false;
                Constants.CAKE_TYPE_CHOCOLATE = false;
                Constants.PLAYER_HAS_CAKE = false;
                Constants.PLAYER_CAKE_INDEX = 0;
                Constants.CURRENT_ORDER_INDEX = 0;

                // ✅ Reset score
                com.example.util.ScoreTracker.getInstance().resetScore();

                // ✅ Create and assign a new timer
                com.example.util.GameTimer freshTimer = new com.example.util.GameTimer(true);
                freshTimer.initialize(150); // 2 minutes 30 seconds

                // ✅ Launch new Level1 with fresh timer
                Level1 newLevel = new Level1();
                newLevel.setGameTimer(freshTimer);
                newLevel.start(new Stage());

                // ✅ Close game over screen
                stage.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root.getChildren().add(playAgainButton);



        // Game Over Image 
        root.getChildren().add(centeredImage("game_over.png", 800, 300, 220, 50));

        // Score Image 
        root.getChildren().add(centeredImage("your_score.png", 300, 125, 300, 350));

        // Empty Score Display
        root.getChildren().add(centeredImage("empty_score.png", 300, 125, 650, 350));
        
        // New: Display the score
        // If finalScore is 0, try to get from ScoreTracker
        if (finalScore == 0) {
            finalScore = ScoreTracker.getInstance().getScore();
        }
        
        // Add score text
        Label scoreLabel = new Label("" + finalScore);
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        scoreLabel.setLayoutX(750);
        scoreLabel.setLayoutY(387);
        scoreLabel.setTextFill(Color.BLACK);
        root.getChildren().add(scoreLabel);
        
        // Output score to console for debugging
        System.out.println("GameOverScreen displaying score: " + finalScore);

        //Scene Setup 
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

package com.example;

import java.io.File;

import com.example.Constants;
import com.example.util.GameTimer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Creates A Level Selection Screen For The User To Chose What Level They Want To Play
 */
public class LS extends Application {

    public static Stage primaryStage;
    private static GameTimer gameTimer; // Add the GameTimer as a static variable

    /**
     * Method to set the game timer
     * @param timer The timer to use for the game
     */
    public void setGameTimer(GameTimer timer) {
        gameTimer = timer;
    }

    /**
     * Method to start Level 1 with a 2:30 timer
     */
    public void startLevel1() {
        // Hide the level selection screen
        primaryStage.hide(); // Use primaryStage instead of level
        
        // Create a new GameTimer for Level1 (2 minutes and 30 seconds)
        gameTimer = new GameTimer(true); // true means countdown
        gameTimer.initialize(150); // 2:30 = 150 seconds
        
        // Set timeout action
        gameTimer.setOnTimeout(() -> {
            // This will be executed when time runs out
            System.out.println("Time's up!");
            // You can add additional logic for game over here
        });
        
        // Create and start Level1
        Level1 level1 = new Level1();
        level1.setGameTimer(gameTimer); // Pass the timer to Level1
        
        Stage level1Stage = new Stage();
        level1.start(level1Stage);
        
        // Start the timer when the level begins
        gameTimer.start();
    }

    @Override
    public void start(Stage primaryStage){
        
        //Assigns the stage variable to the global variable
        this.primaryStage = primaryStage;

        File level1File = new File("hproject\\src\\main\\resources\\level1.png");
        File level2File = new File("hproject\\src\\main\\resources\\level2.png");
        File level3File = new File("hproject\\src\\main\\resources\\level3.png");
        File level2FileCS = new File("hproject\\src\\main\\resources\\comingsoon.png");
        File level3FileCS = new File("hproject\\src\\main\\resources\\comingsoon.png");
        File titleFile = new File("hproject\\src\\main\\resources\\bakinggame.png");

        Image level1Image = new Image(level1File.toURI().toString());
        Image level2Image = new Image(level2File.toURI().toString());
        Image level3Image = new Image(level3File.toURI().toString());
        Image level2ImageCS = new Image(level2FileCS.toURI().toString());
        Image level3ImageCS = new Image(level3FileCS.toURI().toString());
        Image titleImage = new Image(titleFile.toURI().toString());

        ImageView level1ImageView = new ImageView(level1Image);
        ImageView level2ImageView = new ImageView(level2Image);
        ImageView level3ImageView = new ImageView(level3Image);
        ImageView level2ImageViewCS = new ImageView(level2ImageCS);
        ImageView level3ImageViewCS = new ImageView(level3ImageCS);
        ImageView titleImageView = new ImageView(titleImage);

        level1ImageView.setFitWidth(Constants.LS_BUTTON_SIZE);
        level1ImageView.setFitHeight(Constants.LS_BUTTON_SIZE);
        level1ImageView.setPreserveRatio(true);

        level2ImageView.setFitWidth(Constants.LS_BUTTON_SIZE);
        level2ImageView.setFitHeight(Constants.LS_BUTTON_SIZE);
        level2ImageView.setPreserveRatio(true);

        level3ImageView.setFitWidth(Constants.LS_BUTTON_SIZE);
        level3ImageView.setFitHeight(Constants.LS_BUTTON_SIZE);
        level3ImageView.setPreserveRatio(true);

        level2ImageViewCS.setFitWidth(250);
        level2ImageViewCS.setFitHeight(250);
        level2ImageViewCS.setPreserveRatio(true);

        level3ImageViewCS.setFitWidth(250);
        level3ImageViewCS.setFitHeight(250);
        level3ImageViewCS.setPreserveRatio(true);

        titleImageView.setFitWidth(950);
        titleImageView.setFitHeight(550);

        level1ImageView.setTranslateX(-Constants.LS_BUTTON_SIZE - Constants.LS_BUTTON_SIZE/3);
        level3ImageView.setTranslateX(Constants.LS_BUTTON_SIZE + Constants.LS_BUTTON_SIZE/3);
        level3ImageViewCS.setTranslateX(Constants.LS_BUTTON_SIZE + Constants.LS_BUTTON_SIZE/3);

        level1ImageView.setTranslateY(175);
        level2ImageView.setTranslateY(175);
        level3ImageView.setTranslateY(175);
        level2ImageViewCS.setTranslateY(175);
        level3ImageViewCS.setTranslateY(175);
        titleImageView.setTranslateY(-115);

        level1ImageView.setOnMouseClicked(event -> {
            System.out.println("Button 1 clicked");
            
            // Set up the timer for level 1
            if (gameTimer != null) {
                // Initialize with appropriate time limit for level 1 (e.g., 5 minutes)
                gameTimer.initialize(300); // 300 seconds = 5 minutes
                
                // Set timeout action
                gameTimer.setOnTimeout(() -> {
                    System.out.println("Time's up!");
                    // Handle time's up scenario - could show game over screen
                });
            }

            LS.primaryStage.hide();
            Stage level1Stage = new Stage();
            Level1 level1 = new Level1();
            
            // Pass the timer to Level1 if it has been modified to accept it
            if (gameTimer != null) {
                level1.setGameTimer(gameTimer);
                
                // Start the timer when the level starts
                gameTimer.start();
            }
            
            level1.start(level1Stage);
        });

        //Creates a pane
        StackPane levelSelectionPane = new StackPane();

        //Assigns the buttons to the pane and sets style of the pane
        levelSelectionPane.getChildren().addAll(titleImageView, level1ImageView, level2ImageView, level2ImageViewCS, level3ImageView, level3ImageViewCS);
        levelSelectionPane.setStyle(Constants.LS_PANE_STYLE);
        
        //Creates a scene for level selection
        Scene scene = new Scene(levelSelectionPane, Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        
        //Names the stage, and assigns the scene to the stage
        primaryStage.setTitle("Level Selection");
        primaryStage.setScene(scene);

        System.out.println("Current Order b4: " + Constants.CAKE_OPTIONS[Constants.CURRENT_ORDER_INDEX]);

        //shows the stage
        primaryStage.show();
    }

    public void run() {
        launch();
    }
}
package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.text.Font;


import com.example.Constants;
import com.example.util.GameTimer;
import com.example.util.ScoreManager;
import com.example.util.ScoreTracker;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Level1 extends Application {

    public static Stage level1;
    public static StackPane level1Pane;
    public static File selectedCakeFile;
    
    // Add game timer variables
    private static GameTimer gameTimer;
    private Label timerLabel;
    
    // Add score variables
    private ScoreTracker scoreTracker;
    private Label scoreLabel;
    private boolean cakeDelivered = false;
    
    // Set timer from outside (optional)
    public void setGameTimer(GameTimer timer) {
        gameTimer = timer;
        setupTimerLabelListener();
    }

    private void setupTimerLabelListener() {
        if (timerLabel != null && gameTimer != null) {
            updateTimerDisplay();
            gameTimer.secondsProperty().addListener((obs, oldVal, newVal) -> updateTimerDisplay());
        }
    }

    private void updateTimerDisplay() {
        if (gameTimer != null && timerLabel != null) {
            timerLabel.setText("TIME: " + gameTimer.getFormattedTime());
            timerLabel.setFont(Constants.getArcadeFont(18));
            timerLabel.setStyle(
                gameTimer.getSeconds() < 30
                ? "-fx-font-weight: bold; -fx-text-fill: red;"
                : "-fx-text-fill: black;"
            );
        }
    }
    
    
    @Override
    public void start(Stage level1) {
        this.level1 = level1;
        
        // Initialize score tracker
        scoreTracker = ScoreTracker.getInstance();
        scoreTracker.resetScore(); // Reset score at the start of the level

        level1Pane = new StackPane();
        Pane playablePane = new Pane();

        level1.setTitle("Level 1");

        Rectangle wall = new Rectangle(Constants.PLAYABLE_PANE_WIDTH, 100);
        wall.setFill(Color.web("#B1B371"));
        wall.setStroke(Color.web("#677830"));

        File playerFile = new File(Constants.PLAYER_FRONT_IMAGEPATH);
        Image playerImage = new Image(playerFile.toURI().toString());
        ImageView playerImageView = new ImageView(playerImage);

        File mixerFile = new File(Constants.MIXER_IMAGEPATH);
        Image mixerImage = new Image(mixerFile.toURI().toString());
        ImageView mixerImageView = new ImageView(mixerImage);

        File ovenFile = new File(Constants.OVEN_IMAGEPATH);
        Image ovenImage = new Image(ovenFile.toURI().toString());
        ImageView ovenImageView = new ImageView(ovenImage);

        File decorationStationFile = new File(Constants.DECORATIONSTATION_IMAGEPATH);
        Image decorationStationImage = new Image(decorationStationFile.toURI().toString());
        ImageView decorationStationImageView = new ImageView(decorationStationImage);
        
        File menuFile = new File("hproject\\src\\main\\resources\\menu.png");
        Image menuImage = new Image(menuFile.toURI().toString());
        ImageView menuImageView = new ImageView(menuImage);

        File orderFile = new File("hproject\\src\\main\\resources\\order.png");
        Image orderImage = new Image(orderFile.toURI().toString());
        ImageView orderImageView = new ImageView(orderImage);

        File pickupFile = new File("hproject\\src\\main\\resources\\pickup.png");
        Image pickupImage = new Image(pickupFile.toURI().toString());
        ImageView pickupImageView = new ImageView(pickupImage);

        File noteFile = new File("hproject\\src\\main\\resources\\note.png");
        Image noteImage = new Image(noteFile.toURI().toString());
        ImageView noteImageView = new ImageView(noteImage);

        File selectedCakeFile = new File("hproject\\src\\main\\resources\\" + Constants.CAKE_OPTIONS[Constants.CURRENT_ORDER_INDEX]);
        Image selectedCakeImage = new Image(selectedCakeFile.toURI().toString());
        ImageView selectedCakeImageView = new ImageView(selectedCakeImage);

        File assetBoxScoreFile = new File("hproject\\src\\main\\resources\\assetbox.png");
        Image assetBoxScoreImage = new Image(assetBoxScoreFile.toURI().toString());
        ImageView assetBoxScoreImageView = new ImageView(assetBoxScoreImage);

        File assetBoxTimeFile = new File("hproject\\src\\main\\resources\\assetbox.png");
        Image assetBoxTimeImage = new Image(assetBoxTimeFile.toURI().toString());
        ImageView assetBoxTimeImageView = new ImageView(assetBoxTimeImage);

        playerImageView.setFitWidth(100);
        playerImageView.setFitHeight(100);
        playerImageView.setPreserveRatio(true);

        mixerImageView.setFitWidth(200);
        mixerImageView.setFitHeight(200);
        mixerImageView.setPreserveRatio(true);

        ovenImageView.setFitWidth(200);
        ovenImageView.setFitHeight(200);
        ovenImageView.setPreserveRatio(true);

        decorationStationImageView.setFitWidth(200);
        decorationStationImageView.setFitHeight(200);
        decorationStationImageView.setPreserveRatio(true);

        menuImageView.setFitWidth(100);
        menuImageView.setFitHeight(100);
        menuImageView.setPreserveRatio(true);

        orderImageView.setFitWidth(200);
        orderImageView.setFitHeight(200);
        orderImageView.setPreserveRatio(true);

        pickupImageView.setFitWidth(200);
        pickupImageView.setFitHeight(200);
        pickupImageView.setPreserveRatio(true);

        noteImageView.setFitWidth(200);
        noteImageView.setFitHeight(175);
        
        selectedCakeImageView.setFitWidth(125);
        selectedCakeImageView.setFitHeight(125);

        assetBoxScoreImageView.setFitWidth(200);
        assetBoxScoreImageView.setFitHeight(175);

        assetBoxTimeImageView.setFitWidth(250);
        assetBoxTimeImageView.setFitHeight(125);

        playerImageView.setLayoutX(Constants.PANE_WIDTH/2 - 50);
        playerImageView.setLayoutY(Constants.PANE_HEIGHT/2 + 50);
        
        mixerImageView.setLayoutX(Constants.MIXER_LEVEL1_X);
        mixerImageView.setLayoutY(Constants.MIXER_LEVEL1_Y);
        decorationStationImageView.setLayoutX(Constants.DECORATIONSTATION_LEVEL1_X);
        decorationStationImageView.setLayoutY(Constants.DECORATIONSTATION_LEVEL1_Y);
        ovenImageView.setLayoutX(Constants.OVEN_LEVEL1_X);
        ovenImageView.setLayoutY(Constants.OVEN_LEVEL1_Y);

        menuImageView.setTranslateX(Constants.MAP_BUTTON_XOFFSET);
        menuImageView.setTranslateY(Constants.MAP_BUTTON_YOFFSET);

        orderImageView.setTranslateX(-10);
        orderImageView.setTranslateY(Constants.PLAYABLE_PANE_HEIGHT/2 + 25);

        pickupImageView.setTranslateX(Constants.PLAYABLE_PANE_WIDTH - 190);//1100);
        pickupImageView.setTranslateY(Constants.PLAYABLE_PANE_HEIGHT/2 + 25);

        noteImageView.setTranslateX(350);
        noteImageView.setTranslateY(-245);

        selectedCakeImageView.setTranslateX(350);
        selectedCakeImageView.setTranslateY(-240);

        assetBoxScoreImageView.setTranslateX(520);
        assetBoxScoreImageView.setTranslateY(-240);

        assetBoxTimeImageView.setTranslateX(-510);
        assetBoxTimeImageView.setTranslateY(-218);

         // Load arcade font
        Font arcadeFont = Constants.getArcadeFont(18);
        
        // Create the timer label
        timerLabel = new Label("TIME: 00:00");
        timerLabel.setFont(arcadeFont);
        timerLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        timerLabel.setTranslateX(-510);
        timerLabel.setTranslateY(-218);

       // Create the score label with custom font
        scoreLabel = new Label("SCORE: 0");
        scoreLabel.setFont(arcadeFont); // <- set custom font here
        scoreLabel.setStyle("-fx-text-fill: black;"); // optional: only color
        scoreLabel.setTranslateX(520);
        scoreLabel.setTranslateY(-240);
        
        // Bind the score label to the score property
        scoreTracker.scoreProperty().addListener((obs, oldVal, newVal) -> {
            scoreLabel.setText("SCORE: " + newVal);
        });
        
        // ✅ Timer Setup — Force timer to 2:30 (150s)
        if (gameTimer == null) {
            gameTimer = new GameTimer(true);  // Ensure new instance
            gameTimer.initialize(150);        // Set to 2 min 30 sec
        }
        
        setupTimerLabelListener();            // Set listener
        gameTimer.start();                    // Start it
        
        // Set gameTimer timeout handler
        gameTimer.setOnTimeout(() -> {
            Platform.runLater(() -> endGame("Time's up!"));
        });

        level1Pane.setStyle(Constants.MAP_PANE_STYLE);

        playablePane.setMinHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setMaxHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setTranslateY(Constants.PLAYABLE_PANE_YOFFSET);

        playablePane.setMinWidth(Constants.PLAYABLE_PANE_WIDTH);
        playablePane.setMaxWidth(Constants.PLAYABLE_PANE_WIDTH);

        playablePane.setTranslateY(Constants.PLAYABLE_PANE_YOFFSET);
        
        playablePane.setStyle(Constants.PLAYABLE_PANE_STYLE);

        menuImageView.setOnMouseClicked(event -> {
            System.out.println("LS Button clicked");

            // Pause the timer when exiting to menu
            if (gameTimer != null) {
                gameTimer.pause();
            }

            Level1.level1.hide();
            Stage lsStage = new Stage();
            LS ls = new LS();
            ls.start(lsStage);
        });
        
        // Add click handler for pickup station to check cake delivery
        pickupImageView.setOnMouseClicked(event -> {
            // Check if player is close enough to the pickup station
            double playerX = playerImageView.getLayoutX() + playerImageView.getFitWidth()/2;
            double playerY = playerImageView.getLayoutY() + playerImageView.getFitHeight()/2;
            double pickupX = pickupImageView.getTranslateX() + pickupImageView.getFitWidth()/2;
            double pickupY = pickupImageView.getTranslateY() + pickupImageView.getFitHeight()/2;
            
            System.out.println("Player position: " + playerX + ", " + playerY);
            System.out.println("Pickup position: " + pickupX + ", " + pickupY);
            
            // Calculate distance between player and pickup
            double distance = Math.sqrt(Math.pow(playerX - pickupX, 2) + Math.pow(playerY - pickupY, 2));
            System.out.println("Distance to pickup: " + distance);
            
            // If player is close enough and carrying a cake that hasn't been delivered
            if (distance < 150 && !cakeDelivered && Constants.PLAYER_HAS_CAKE) {
                System.out.println("Player is close enough to delivery point and has cake. Evaluating...");
                evaluateCakeDelivery();
            } else {
                System.out.println("Cannot deliver: Distance=" + distance + ", HasCake=" + Constants.PLAYER_HAS_CAKE + ", AlreadyDelivered=" + cakeDelivered);
            }
        });

        playablePane.getChildren().addAll(wall, mixerImageView, ovenImageView, decorationStationImageView, playerImageView, orderImageView, pickupImageView);

        // Add score label to the UI
        level1Pane.getChildren().addAll(playablePane, menuImageView, assetBoxTimeImageView, assetBoxScoreImageView, 
                                        noteImageView, selectedCakeImageView, timerLabel, scoreLabel);

        Scene level1Scene = new Scene(level1Pane, Constants.PANE_WIDTH, Constants.PANE_HEIGHT); 
        level1.setScene(level1Scene);

        MovementController movementController = new MovementController();
        movementController.makeMoveable(playerImageView, level1Scene);

        level1.show();
        level1.centerOnScreen();
    }
    
    // FIXED METHOD - Method to evaluate cake delivery
    private void evaluateCakeDelivery() {
        System.out.println("===== CAKE DELIVERY EVALUATION =====");
        System.out.println("Player cake index: " + Constants.PLAYER_CAKE_INDEX);
        System.out.println("Current order index: " + Constants.CURRENT_ORDER_INDEX);
        
        // Check if the cake matches the current order
        if (Constants.PLAYER_CAKE_INDEX == Constants.CURRENT_ORDER_INDEX) {
            // Correct cake delivered
            System.out.println("CORRECT CAKE! Adding 5 points");
            
            // FIXED: Direct score manipulation to ensure it works
            int currentScore = scoreTracker.getScore();
            scoreTracker.setScore(currentScore + 5);
            
            System.out.println("Score before: " + currentScore + ", after: " + scoreTracker.getScore());
            
            showFeedback("Correct! +5 points");
        } else {
            // Wrong cake delivered
            System.out.println("WRONG CAKE! No points added");
            showFeedback("Wrong cake! No points");
        }
        
        // Verify the new score in console
        System.out.println("Current score is now: " + scoreTracker.getScore());
        
        // Mark cake as delivered and generate new order
        cakeDelivered = true;
        Constants.PLAYER_HAS_CAKE = false;
        
        // Generate a new order after a short delay
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                Platform.runLater(() -> {
                    generateNewOrder();
                    cakeDelivered = false;
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    // Method to generate a new order
    private void generateNewOrder() {
        // Randomly select a new cake order
        int previousOrder = Constants.CURRENT_ORDER_INDEX;
        int numCakeOptions = Constants.CAKE_OPTIONS.length;
        
        // Make sure we don't get the same order twice in a row
        do {
            Constants.CURRENT_ORDER_INDEX = (int)(Math.random() * numCakeOptions);
        } while (Constants.CURRENT_ORDER_INDEX == previousOrder && numCakeOptions > 1);
        
        // Update the order display
        updateOrderDisplay();
    }
    
    // Method to update the cake order display
    private void updateOrderDisplay() {
        // Update the cake image in the UI
        File newCakeFile = new File("hproject\\src\\main\\resources\\" + Constants.CAKE_OPTIONS[Constants.CURRENT_ORDER_INDEX]);
        Image newCakeImage = new Image(newCakeFile.toURI().toString());
        
        // Find the cake image view in the scene
        for (javafx.scene.Node node : level1Pane.getChildren()) {
            if (node instanceof ImageView && ((ImageView)node).getFitWidth() == 125 && ((ImageView)node).getFitHeight() == 125) {
                ((ImageView)node).setImage(newCakeImage);
                break;
            }
        }
    }
    
    // Method to show feedback to the player
    private void showFeedback(String message) {
        Label feedbackLabel = new Label(message);
        feedbackLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-background-color: white; -fx-padding: 10px;");
        feedbackLabel.setAlignment(Pos.CENTER);
        
        // Position the feedback in the center of the screen
        feedbackLabel.setLayoutX(Constants.PANE_WIDTH/2 - 150);
        feedbackLabel.setLayoutY(Constants.PANE_HEIGHT/2 - 50);
        
        // Add to the scene
        level1Pane.getChildren().add(feedbackLabel);
        
        // Remove after delay
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                Platform.runLater(() -> level1Pane.getChildren().remove(feedbackLabel));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    // Method to end the game and save score
    private void endGame(String message) {
        // Pause the timer
        if (gameTimer != null) {
            gameTimer.pause();
        }
        
        int finalScore = scoreTracker.getScore();
        
        // Show game over dialog
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(message);
        alert.setContentText("Your score: " + finalScore);
        alert.showAndWait();
        
        // Prompt for player name to save high score
        TextInputDialog dialog = new TextInputDialog("Player");
        dialog.setTitle("Save High Score");
        dialog.setHeaderText("Enter your name:");
        dialog.setContentText("Name:");
        
        dialog.showAndWait().ifPresent(name -> {
            // Save the score
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);
            
            ScoreManager.getInstance().addScore(name, finalScore, "Level 1", date);
            
            // Return to level selection
            Level1.level1.hide();
            Stage lsStage = new Stage();
            LS ls = new LS();
            ls.start(lsStage);
        });
    }
    
    // Method to pause the timer when transitioning between stations
    public void goToNextStation() {
        // Pause the timer during transition
        if (gameTimer != null) {
            gameTimer.pause();
        }
        
        // ... code to go to next station ...
        
        // Resume the timer after transition
        if (gameTimer != null) {
            gameTimer.start();
        }
    
    // Get the final score from ScoreTracker
    int finalScore = scoreTracker.getScore();
    System.out.println("Game ending with score: " + finalScore);
    
    // Show game over dialog
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Game Over");
    alert.setHeaderText(message);
    alert.setContentText("Your score: " + finalScore);
    alert.showAndWait();
    
    // Prompt for player name to save high score
    TextInputDialog dialog = new TextInputDialog("Player");
    dialog.setTitle("Save High Score");
    dialog.setHeaderText("Enter your name:");
    dialog.setContentText("Name:");
    
    dialog.showAndWait().ifPresent(name -> {
        // Save the score
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        
        ScoreManager.getInstance().addScore(name, finalScore, "Level 1", date);
        
        // Close Level1 and show GameOverScreen with the finalScore
        Level1.level1.hide();
        try {
            // Pass the final score to the GameOverScreen
            new GameOverScreen(finalScore).start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback to level selection if there's an error
            Stage lsStage = new Stage();
            LS ls = new LS();
            ls.start(lsStage);
        }
    });
}
    }

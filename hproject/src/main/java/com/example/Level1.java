package com.example;

import java.io.File;

import com.example.Constants;
import com.example.util.GameTimer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Level1 extends Application{

    public static Stage level1;
    public static StackPane level1Pane;
    // public static ImageView selectedCakeImageView;
    public static File selectedCakeFile;
    
    // Add game timer variables
    static GameTimer gameTimer;
    private Label timerLabel;
    
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
        timerLabel.setText("Time: " + gameTimer.getFormattedTime());
        timerLabel.setStyle(
            gameTimer.getSeconds() < 30
            ? "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;"
            : "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: black;"
        );
    }
}
    
    @Override
    public void start(Stage level1){
        this.level1 = level1;

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

        // Create the timer label
        timerLabel = new Label("Time: 00:00");
        timerLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        timerLabel.setTranslateX(-510);
        timerLabel.setTranslateY(-218);
        
         // ✅ Timer Setup — Force timer to 2:30 (150s)
         gameTimer = new GameTimer(true);  // Ensure new instance
         gameTimer.initialize(150);        // Set to 2 min 30 sec
         setupTimerLabelListener();        // Set listener
         gameTimer.start();                // Start it

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

            File exitFile = new File("hproject\\src\\main\\resources\\exitbutton.png");
            Image exitImage = new Image(exitFile.toURI().toString());
            ImageView exitImageView = new ImageView(exitImage);

            File backFile = new File("hproject\\src\\main\\resources\\backbutton.png");
            Image backImage = new Image(backFile.toURI().toString());
            ImageView backImageView = new ImageView(backImage);

            exitImageView.setFitWidth(250);
            exitImageView.setFitHeight(250);
            exitImageView.setPreserveRatio(true);

            backImageView.setFitWidth(250);
            backImageView.setFitHeight(250);
            backImageView.setPreserveRatio(true);

            Rectangle menuWall = new Rectangle(330, 480);
            menuWall.setFill(Color.web("#B1B371"));
            menuWall.setStroke(Color.web("#677830"));

            Stage menuStage = new Stage();
            Pane menuPane = new Pane();
            Scene menuScene = new Scene(menuPane, 350, 500);

            menuWall.setTranslateX(10);
            menuWall.setTranslateY(10);

            exitImageView.setTranslateX((350/2) - (250/2));
            exitImageView.setTranslateY(250);

            backImageView.setTranslateX((350/2) - (250/2));
            backImageView.setTranslateY(50);

            menuPane.setStyle(Constants.MAP_PANE_STYLE);

            menuPane.getChildren().addAll(menuWall, backImageView, exitImageView);

            menuStage.setScene(menuScene);

            exitImageView.setOnMouseClicked(eventA -> {
                LS.primaryStage.close();
                Level1.level1.close();
                menuStage.close();
            });

            backImageView.setOnMouseClicked(eventB -> {
                menuStage.close();
                gameTimer.start();
            });

            menuStage.show();
        });

        playablePane.getChildren().addAll(wall, mixerImageView, ovenImageView, decorationStationImageView, playerImageView, orderImageView, pickupImageView);

        // Add timer label on top of the asset box for time
        level1Pane.getChildren().addAll(playablePane, menuImageView, assetBoxTimeImageView, assetBoxScoreImageView, noteImageView, selectedCakeImageView, timerLabel);

        Scene level1Scene = new Scene(level1Pane, Constants.PANE_WIDTH, Constants.PANE_HEIGHT); 
        level1.setScene(level1Scene);

        MovementController movementController = new MovementController();
        movementController.makeMoveable(playerImageView, level1Scene);

        level1.show();
        level1.centerOnScreen();
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
    }
}
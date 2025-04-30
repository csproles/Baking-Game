package com.example;

import java.io.File;

import com.example.Constants;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Level1 extends Application{

    public static Stage level1;
    
    @Override
    public void start(Stage level1){
        this.level1 = level1;

        StackPane level1Pane = new StackPane();
        Pane playablePane = new Pane();

        level1.setTitle("Level 1");

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

        playerImageView.setLayoutX(Constants.PANE_WIDTH/2 - 50);
        playerImageView.setLayoutY(Constants.PANE_HEIGHT/2 + 50);
        
        mixerImageView.setLayoutX(Constants.MIXER_LEVEL1_X);
        mixerImageView.setLayoutY(Constants.MIXER_LEVEL1_Y);
        decorationStationImageView.setLayoutX(Constants.DECORATIONSTATION_LEVEL1_X);
        decorationStationImageView.setLayoutY(Constants.DECORATIONSTATION_LEVEL1_Y);
        ovenImageView.setLayoutX(Constants.OVEN_LEVEL1_X);
        ovenImageView.setLayoutY(Constants.OVEN_LEVEL1_Y);

        Button menuButton = new Button(Constants.LS_BUTTON_TEXT);
        menuButton.setMinHeight(Constants.MAP_BUTTON_HEIGHT);
        menuButton.setMaxHeight(Constants.MAP_BUTTON_HEIGHT);

        menuButton.setMinWidth(Constants.MAP_BUTTON_WIDTH);
        menuButton.setMaxWidth(Constants.MAP_BUTTON_WIDTH);

        menuButton.setTranslateX(Constants.MAP_BUTTON_XOFFSET);
        menuButton.setTranslateY(Constants.MAP_BUTTON_YOFFSET);

        menuButton.setStyle(Constants.LS_BUTTON_STYLE);
        menuButton.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR , Constants.MAP_BUTTON_TEXT_SIZE));;

        level1Pane.setStyle(Constants.MAP_PANE_STYLE);

        playablePane.setMinHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setMaxHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setTranslateY(Constants.PLAYABLE_PANE_YOFFSET);

        playablePane.setMinWidth(Constants.PLAYABLE_PANE_WIDTH);
        playablePane.setMaxWidth(Constants.PLAYABLE_PANE_WIDTH);

        playablePane.setTranslateY(Constants.PLAYABLE_PANE_YOFFSET);
        
        playablePane.setStyle(Constants.PLAYABLE_PANE_STYLE);

        playablePane.getChildren().addAll(mixerImageView, ovenImageView, decorationStationImageView, playerImageView);
        level1Pane.getChildren().addAll(playablePane, menuButton);//playablePane, menuButton);

        Scene level1Scene = new Scene(level1Pane, Constants.PANE_WIDTH, Constants.PANE_HEIGHT); 
        level1.setScene(level1Scene);

        MovementController movementController = new MovementController();
        movementController.makeMoveable(playerImageView, level1Scene);

        HandleL1ToLSButton handleLSButton = new HandleL1ToLSButton();
        
        menuButton.setOnAction(handleLSButton);

        level1.show();
        level1.centerOnScreen();
    }  
}


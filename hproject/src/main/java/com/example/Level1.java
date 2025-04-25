package com.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

        Button menuButton = new Button(Constants.LS_BUTTON_TEXT);
        menuButton.setMinHeight(Constants.MAP_BUTTON_HEIGHT);
        menuButton.setMaxHeight(Constants.MAP_BUTTON_HEIGHT);

        menuButton.setMinWidth(Constants.MAP_BUTTON_WIDTH);
        menuButton.setMaxWidth(Constants.MAP_BUTTON_WIDTH);

        menuButton.setTranslateX(Constants.MAP_BUTTON_XOFFSET);
        menuButton.setTranslateY(Constants.MAP_BUTTON_YOFFSET);

        menuButton.setStyle(Constants.LS_BUTTON_STYLE);
        menuButton.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR , Constants.MAP_BUTTON_TEXT_SIZE));;

        level1Pane.getChildren().addAll(playablePane, menuButton);
        level1Pane.setStyle(Constants.MAP_PANE_STYLE);

        playablePane.setMinHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setMaxHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setTranslateY(Constants.PLAYABLE_PANE_YOFFSET);

        playablePane.setMinWidth(Constants.PLAYABLE_PANE_WIDTH);
        playablePane.setMaxWidth(Constants.PLAYABLE_PANE_WIDTH);
        
        playablePane.setStyle(Constants.PLAYABLE_PANE_STYLE);

        Scene level1Scene = new Scene(level1Pane, Constants.PANE_WIDTH, Constants.PANE_HEIGHT); 
        level1.setScene(level1Scene);

        HandleL1ToLSButton handleLSButton = new HandleL1ToLSButton();
        
        menuButton.setOnAction(handleLSButton);

        level1.show();
        level1.centerOnScreen();
    }  
}


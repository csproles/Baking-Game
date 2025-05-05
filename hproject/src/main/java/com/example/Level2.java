package com.example;

import com.example.Constants;

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

public class Level2 extends Application{

    public static Stage level2;
    
    @Override
    public void start(Stage level2){
        this.level2 = level2;

        StackPane level2Pane = new StackPane();
        Pane playablePane = new Pane();

        level2.setTitle("Level 2");

        Button menuButton = new Button(Constants.LS_BUTTON_TEXT);
        menuButton.setMinHeight(Constants.MAP_BUTTON_HEIGHT);
        menuButton.setMaxHeight(Constants.MAP_BUTTON_HEIGHT);

        menuButton.setMinWidth(Constants.MAP_BUTTON_WIDTH);
        menuButton.setMaxWidth(Constants.MAP_BUTTON_WIDTH);
        
        menuButton.setTranslateX(Constants.MAP_BUTTON_XOFFSET);
        menuButton.setTranslateY(Constants.MAP_BUTTON_YOFFSET);

        menuButton.setStyle(Constants.LS_BUTTON_STYLE);
        menuButton.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR , Constants.MAP_BUTTON_TEXT_SIZE));;

        level2Pane.getChildren().addAll(playablePane, menuButton);
        level2Pane.setStyle(Constants.MAP_PANE_STYLE);

        playablePane.setMinHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setMaxHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setTranslateY(Constants.PLAYABLE_PANE_YOFFSET);

        playablePane.setMinWidth(Constants.PLAYABLE_PANE_WIDTH);
        playablePane.setMaxWidth(Constants.PLAYABLE_PANE_WIDTH);

        playablePane.setTranslateY(Constants.PLAYABLE_PANE_YOFFSET);
        
        playablePane.setStyle(Constants.PLAYABLE_PANE_STYLE);

        Scene level2Scene = new Scene(level2Pane, Constants.PANE_WIDTH, Constants.PANE_HEIGHT); 
        level2.setScene(level2Scene);

        HandleL2ToLSButton handleLSButton = new HandleL2ToLSButton();
        
        menuButton.setOnAction(handleLSButton);

        level2.show();
        level2.centerOnScreen();
    }  
}


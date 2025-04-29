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

public class Level3 extends Application{

    public static Stage level3;
    
    @Override
    public void start(Stage level3){
        this.level3 = level3;

        StackPane level3Pane = new StackPane();
        Pane playablePane = new Pane();

        level3.setTitle("Level 3");

        Button menuButton = new Button(Constants.LS_BUTTON_TEXT);
        menuButton.setMinHeight(Constants.MAP_BUTTON_HEIGHT);
        menuButton.setMaxHeight(Constants.MAP_BUTTON_HEIGHT);

        menuButton.setMinWidth(Constants.MAP_BUTTON_WIDTH);
        menuButton.setMaxWidth(Constants.MAP_BUTTON_WIDTH);
        
        menuButton.setTranslateX(Constants.MAP_BUTTON_XOFFSET);
        menuButton.setTranslateY(Constants.MAP_BUTTON_YOFFSET);

        menuButton.setStyle(Constants.LS_BUTTON_STYLE);
        menuButton.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR , Constants.MAP_BUTTON_TEXT_SIZE));;

        level3Pane.getChildren().addAll(playablePane, menuButton);
        level3Pane.setStyle(Constants.MAP_PANE_STYLE);

        playablePane.setMinHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setMaxHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setTranslateY(Constants.PLAYABLE_PANE_YOFFSET);

        playablePane.setMinWidth(Constants.PLAYABLE_PANE_WIDTH);
        playablePane.setMaxWidth(Constants.PLAYABLE_PANE_WIDTH);

        playablePane.setTranslateY(Constants.PLAYABLE_PANE_YOFFSET);
        
        playablePane.setStyle(Constants.PLAYABLE_PANE_STYLE);

        Scene level3Scene = new Scene(level3Pane, Constants.PANE_WIDTH, Constants.PANE_HEIGHT); 
        level3.setScene(level3Scene);

        HandleL3ToLSButton handleLSButton = new HandleL3ToLSButton();
        
        menuButton.setOnAction(handleLSButton);

        level3.show();
        level3.centerOnScreen();
    }  
}


package com.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        StackPane playablePane = new StackPane();

        Button lsButton = new Button(Constants.LS_BUTTON_TEXT);
        lsButton.setMinHeight(Constants.MAP_BUTTON_HEIGHT);
        lsButton.setMaxHeight(Constants.MAP_BUTTON_HEIGHT);

        lsButton.setMinWidth(Constants.MAP_BUTTON_WIDTH);
        lsButton.setMaxWidth(Constants.MAP_BUTTON_WIDTH);
        
        lsButton.setTranslateX(Constants.MAP_BUTTON_XOFFSET);
        lsButton.setTranslateY(Constants.MAP_BUTTON_YOFFSET);

        lsButton.setStyle(Constants.LS_BUTTON_STYLE);
        lsButton.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR , Constants.MAP_BUTTON_TEXT_SIZE));;

        level3Pane.getChildren().addAll(playablePane, lsButton);
        level3Pane.setStyle(Constants.MAP_PANE_STYLE);

        playablePane.setMinHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setMaxHeight(Constants.PLAYABLE_PANE_HEIGHT);
        playablePane.setTranslateY(Constants.PLAYABLE_PANE_YOFFSET);

        playablePane.setMinWidth(Constants.PLAYABLE_PANE_WIDTH);
        playablePane.setMaxWidth(Constants.PLAYABLE_PANE_WIDTH);
        
        playablePane.setStyle(Constants.LS_BUTTON_STYLE);

        Scene level3Scene = new Scene(level3Pane, Constants.PANE_WIDTH, Constants.PANE_HEIGHT); 
        level3.setScene(level3Scene);

        HandleL3ToLSButton handleLSButton = new HandleL3ToLSButton();
        
        lsButton.setOnAction(handleLSButton);

        level3.show();
        level3.centerOnScreen();
    }  
}


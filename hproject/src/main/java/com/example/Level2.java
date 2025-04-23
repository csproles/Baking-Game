package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Level2 extends Application{
    
    @Override
    public void start(Stage level2){
        StackPane level2Pane = new StackPane();
        level2Pane.getChildren();
        Scene level2Scene = new Scene(level2Pane); 
        level2.setScene(level2Scene);
        level2.show();
        level2.centerOnScreen();
    }
    
}

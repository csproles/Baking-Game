package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Level1 extends Application{
    
    @Override
    public void start(Stage level1){
        StackPane level1Pane = new StackPane();
        level1Pane.getChildren();
        Scene level1Scene = new Scene(level1Pane); 
        level1.setScene(level1Scene);
        level1.show();
        level1.centerOnScreen();
    }
    
}

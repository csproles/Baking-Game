package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Level3 extends Application{
    
    @Override
    public void start(Stage level3){
        StackPane level3Pane = new StackPane();
        level3Pane.getChildren();
        Scene level3Scene = new Scene(level3Pane); 
        level3.setScene(level3Scene);
        level3.show();
        level3.centerOnScreen();
    }
    
}

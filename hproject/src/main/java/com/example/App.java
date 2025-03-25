package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage){
        StackPane pane = new StackPane();
        pane.getChildren().add(new Button("Button"));
        Scene scene1 = new Scene(pane, 500, 500);
        primaryStage.setTitle("Show Line");
        primaryStage.setScene(scene1);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}